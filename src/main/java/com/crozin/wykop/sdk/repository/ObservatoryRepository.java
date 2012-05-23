package com.crozin.wykop.sdk.repository;

import java.util.List;

import com.crozin.wykop.sdk.Command;
import com.crozin.wykop.sdk.Session;
import com.crozin.wykop.sdk.domain.Comment;
import com.crozin.wykop.sdk.domain.Entry;
import com.crozin.wykop.sdk.domain.EntryComment;
import com.crozin.wykop.sdk.domain.LinkVote;

public class ObservatoryRepository extends AbstractRepository {
	public ObservatoryRepository(Session session) {
		super(session);
	}

	public List<LinkVote> getLatestLinkVotes() {
		return session.getResultList(new Command("observatory", "votes"), LinkVote.class);
	}
	
	public List<Comment> getLatestComments() {
		return session.getResultList(new Command("observatory", "comment"), Comment.class);
	}
	
	public List<Entry> getLatestEntries() {
		return session.getResultList(new Command("observatory", "entry"), Entry.class);
	}
	
	public List<EntryComment> getLatestEntriesComments() {
		return session.getResultList(new Command("observatory", "entriesComments"), EntryComment.class);
	}
}
