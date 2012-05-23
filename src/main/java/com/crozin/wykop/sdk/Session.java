package com.crozin.wykop.sdk;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.crozin.wykop.sdk.domain.Link;
import com.crozin.wykop.sdk.domain.Profile;
import com.crozin.wykop.sdk.exception.ApiException;
import com.crozin.wykop.sdk.exception.ConnectionException;
import com.crozin.wykop.sdk.exception.CredentialsException;
import com.crozin.wykop.sdk.exception.InvalidParametersException;
import com.crozin.wykop.sdk.exception.InvalidResourceException;
import com.crozin.wykop.sdk.exception.InvalidResponseException;
import com.crozin.wykop.sdk.exception.RequestException;
import com.crozin.wykop.sdk.exception.SdkException;
import com.crozin.wykop.sdk.repository.LinkRepository;
import com.crozin.wykop.sdk.repository.LinksRepository;
import com.crozin.wykop.sdk.repository.ObservatoryRepository;
import com.crozin.wykop.sdk.repository.ProfileRepository;
import com.crozin.wykop.sdk.repository.ProfilesRepository;
import com.crozin.wykop.sdk.service.SearchService;
import com.crozin.wykop.sdk.util.CollectionsUtils;
import com.crozin.wykop.sdk.util.HashUtils;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

public class Session {
	private static final Logger logger = LoggerFactory.getLogger(Session.class);
	private static final String USER_AGENT_SIGNATURE = "com.crozin.wykop.sdk SDK/1.0.0-ALPHA";
	private static final String CHARSET = "UTF-8";
	
	protected Application sdk;
	protected Map<String, String> apiParameters = new HashMap<String, String>();
	
	protected Map<Class<?>, String> domainToResource = new HashMap<Class<?>, String>();
	
	Session(Application app) {
		this.sdk = app;
		
		apiParameters.put("appkey", app.publicKey);
		apiParameters.put("format", "json");
		
		domainToResource.put(Link.class, "link");
		domainToResource.put(Profile.class, "profile");
		domainToResource.put(Entry.class, "entry");
	}
	
	public Map<String, String> getMapResult(Command cmd) {
		try {
			String response = execute(cmd);
			
			return sdk.om.readValue(response, sdk.mapType);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public <T> T getSingleResult(Command cmd, Class<T> type) {
		try {
			String response = execute(cmd);
			
			return sdk.om.readValue(response, type);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public <T> List<T> getResultList(Command cmd, Class<T> type) {
		try {
			String response = execute(cmd);
			CollectionType collectionType = sdk.om.getTypeFactory().constructCollectionType(ArrayList.class, type);
			
			return sdk.om.readValue(response, collectionType);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public <T> T find(Integer id, Class<T> type) {
		String resource = domainToResource.get(type);
		
		if (resource == null) {
			throw new IllegalArgumentException("Unknow domain type");
		}
		
		return getSingleResult(new Command(resource, "index", id.toString()), type);
	}
	
	public String execute(Command cmd) {
		try {
			URL url = getUrl(cmd);
			logger.debug("Request URL: {}", url);
			
			HttpURLConnection conn = getConnection(cmd, url);
			
			StringBuilder responseBuilder = new StringBuilder();
			int c = 0;
			while ((c = conn.getInputStream().read()) != -1) {
				responseBuilder.append((char) c);
			}
			
			String response = responseBuilder.toString();
			logger.debug("Response: {}", response);
			
			checkForError(response);
			
			return response;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	protected HttpURLConnection getConnection(Command cmd, URL url) {
		try {
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestProperty("Accept", "application/json");
			conn.setRequestProperty("Accept-Charset", CHARSET);
			conn.setRequestProperty("Cache-Control", "no-cache");
			conn.setRequestProperty("User-Agent", USER_AGENT_SIGNATURE);
			
			StringBuilder hashInput = new StringBuilder();
			hashInput.append(sdk.privateKey).append(url.toString());
			hashInput.append(CollectionsUtils.join(cmd.getPostParameters().values(), ","));
			
			String hash = HashUtils.bytesToString(MessageDigest.getInstance("MD5").digest(hashInput.toString().getBytes(CHARSET)));

			conn.addRequestProperty("apisign", hash);
			logger.debug("Request Hash: {}", hash);

			if (cmd.hasPostParameters()) {
				conn.setDoOutput(true);
				conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=" + CHARSET);
				
				String postData = mapToHttpData(cmd.getPostParameters());
				
				conn.getOutputStream().write(postData.toString().getBytes(CHARSET));
				conn.getOutputStream().close();
				
				logger.debug("Request POST: {}", postData);
			}
			
			return conn;
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (NoSuchAlgorithmException nsae) {
			throw new RuntimeException(nsae);
		}
	}
	
	protected URL getUrl(Command cmd) {
		Map<String, String> parameters = new HashMap<String, String>(apiParameters);
		parameters.putAll(cmd.getParameters());
		
		if (cmd.getClear()) {
			parameters.put("output", "clear");
		}
		
		try {
			return new URL("http://a.wykop.pl/" + commandToHttpPath(cmd) + "/" + CollectionsUtils.join(parameters, ",", ","));
		} catch (MalformedURLException e) {
			throw new RuntimeException(e);
		}
	}
	
	protected void checkForError(String response) {
		try {
			JsonNode errorNode = sdk.om.readValue(response, JsonNode.class).findValue("error");
			
			if (errorNode != null) {
				JsonNode messageNode = errorNode.findValue("message");
				JsonNode codeNode = errorNode.findValue("code");
				
				if (messageNode != null && codeNode != null) {
					String message = messageNode.asText();
					int code = codeNode.asInt();
					
					switch (code) {
					case 1: case 5: case 13: case 14: case 17:
						throw new ConnectionException(message, code);
					case 999: case 1001: case 1002:
						throw new ApiException(message, code);
					case 6: case 11: case 12: case 15:
						throw new SdkException(message, code);
					case 2: case 3:
						throw new RequestException(message, code);
					case 4: case 7: case 22: case 31: case 33: case 34: case 42:
						throw new CredentialsException(message, code);
					case 21: case 71:
						throw new InvalidParametersException(message, code);
					case 32: case 41: case 61: case 81:
						throw new InvalidResourceException(message, code);
					}
					
					throw new InvalidResponseException("Invalid error code.");
				}
			}
		} catch (IOException e) { }
	}
	
	private String commandToHttpPath(Command cmd) {
		StringBuilder path = new StringBuilder();
		path.append(cmd.getResource()).append("/").append(cmd.getMethod());
		
		try {
			for (String argument : cmd.getArguments()) {
				path.append("/").append(URLEncoder.encode(argument, CHARSET));
			}
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
		
		return path.toString();
	}
	
	private String mapToHttpData(Map<String, String> data) throws UnsupportedEncodingException {
		StringBuilder postData = new StringBuilder();
		
		for (Entry<String, String> entry : data.entrySet()) {
			if (postData.length() > 0) {
				postData.append("&");
			}
			
			postData.append(URLEncoder.encode(entry.getKey(), CHARSET));
			postData.append("=");
			postData.append(URLEncoder.encode(entry.getValue().toString(), CHARSET));
		}
		
		return postData.toString();
	}
	
	public LinkRepository getLinkRepository(Link link) {
		return new LinkRepository(this, link.getId());
	}
	
	public LinkRepository getLinkRepository(Integer id) {
		return new LinkRepository(this, id);
	}
	
	public LinksRepository getLinksRepository() {
		return new LinksRepository(this);
	}
	
	public ProfileRepository getProfileRepository(Profile profile) {
		return new ProfileRepository(this, profile.getUsername());
	}
	
	public ProfileRepository getProfileRepository(String username) {
		return new ProfileRepository(this, username);
	}
	
	public ProfilesRepository getProfilesRepository() {
		return new ProfilesRepository(this);
	}
	
	public ObservatoryRepository getObservatoryRepository() {
		return new ObservatoryRepository(this);
	}
	
	public SearchService getSearchService() {
		return new SearchService(this);
	}
	
	public ObjectMapper getObjectMapper() {
		return sdk.om;
	}
}
