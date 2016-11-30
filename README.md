Unofficial SDK for Wykop.pl API
===============================

SDK consists of a set of classes that hide a low-level architecture of the Wykop.pl API, and provides object-oriented, type-safe interface to access the API.
These classes could be divided into three main packages:

- `com.crozin.wykop.sdk.domain` — read-only domain objects, that represent a single entity, e.g. `Link`, `Profile` or `Comment`,
- `com.crozin.wykop.sdk.repository` — repositories are divided into two types. The first one contains methods to retrieve domain objects of a given type, e.g. `LinksRepository.getPromoted()` returns a `List` of `Link`s, and the other one associated with a certain entity, contains methods to return domain objects associated with that entity, e.g. `ProfileRepository.getFollowers()` returns a `List` of `Profile`s that given account follows.
- `com.crozin.wykop.sdk.service` — services provide interfaces to execute a certain operations on a given entity, e.g. `CommentService.delete()` deletes a comment, if the account is allowed to do that.

Services are available only if the SDK session is associated with some account. The only exception is `SearchService`, which is available for annonymous (read-only) session.

---

SDK is available through its own Maven repository. In order to use it, add following declaration to your `pom.xml` file:

    <repositories>
		<repository>
			<id>wykop.pl-java-sdk-mvn-repository</id>
			<url>https://raw.github.com/Crozin/Wykop.pl-Java-SDK/mvn-repo/</url>
			<snapshots>
				<enabled>true</enabled>
				<updatePolicy>always</updatePolicy>
			</snapshots>
		</repository>
	</repositories>
    
Now you can simply add the dependency:

    <dependencies>
        ...
    
		<dependency>
			<groupId>com.crozin</groupId>
			<artifactId>com.crozin.wykop.sdk</artifactId>
			<version>1.0.1</version>
		</dependency>
	</dependencies>

Setting up a new session (connection)
-------------------------------------

The first step is to create an `Application` object, that represents your application. `Application` is a factory class for `Session` objects.

    Application app = new Application("public-key", "private-key");

The next step is opening the actual session. There are two types of session: annonymous one, which provides read-only access to the API, and authenticated one, which is associated with a certain account and is capable of performing some operations on its behalf.

    // Annonymous session
    Session session = app.openSession();

    // Authenticated session
    AuthenticatedSession session = app.openSession("account-key");
    AuthenticatedSession session = app.openSession("username", "password");

> **Note:** To open an authenticated session using username and password the application has to have a special premission granted by Wykop.pl administration.

Examples
--------

### Get a link and all of its buries

    Link link = session.find(1147383, Link.class);
    List<Bury> buries = session.getLinkRepository(link).getBuries();

    System.out.format("Link: %s, by: %s%n", link.getTitle(), link.getAuthor().getUsername());
    for (Bury bury : buries) {
        System.out.println("\t- " + bury.getAuthor().getUsername());
    }

    // Link: Co wolno księdzu?, by: feniks79
    //     - wilkpiski
    //     - jacek-banaszkiewicz
    // ...
    //     - Wierzejski

### List promoted links:

    List<Link> links = session.getLinksRepository().getPromoted();

    for (Link link : links) {
        System.out.format("%s - %d%n", link.getUrl(), link.getVotesCount());
    }

    // http://www.wykop.pl/link/1147057/kapiel-relaks-dla-noworodka/ - 122
    // http://www.wykop.pl/link/1147165/pija-wodke-za-pieniadze-z-opieki-spolecznej/ - 116
    // ...
    // http://www.wykop.pl/link/1146403/rosyjska-incepcja/ - 445
    // http://www.wykop.pl/link/1146509/wypadek-ciezarowki/ - 445

### Open a new authenticated session and add a new link:

    AuthenticatedSession session = app.openSession("account-key");
        
    LinkCandidate link = new LinkCandidate();
    link.setUrl(new URL("http://example.org/"));
    link.setTitle("Foobar");
    link.setDescription("Lorem ipsum dolor");
    link.setGroup("foo");
    link.addTag("foo");
    link.addTag("bar");
    link.addTag("foobar");

    session.getClientService().addLink(link);

Custom commands
---------------

Although SDK supports all features of Wykop.pl API it is possible to execute a custom command using `Command` and `Session` objects.

    // Fetch first chunk (page) of hits of May 2011
    // see: http://www.wykop.pl/developers/docs/#info6_8_2

    Command cmd = new Command("top", "date");
    cmd.addArgument(2011);
    cmd.addArgument(5);
    cmd.addPostParameter("page", 0);

    List<Link> hitsOfMay2011 = session.getResultList(cmd, Link.class);