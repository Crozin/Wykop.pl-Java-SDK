package com.crozin.wykop.sdk.service;

import com.crozin.wykop.sdk.AuthenticatedSession;
import com.crozin.wykop.sdk.Command;
import com.crozin.wykop.sdk.Credential;
import com.crozin.wykop.sdk.domain.VoteResult;

public class LinkService extends AbstractDomainService<Integer> {
	public LinkService(AuthenticatedSession session, Integer id) {
		super(session, id);
	}

	public VoteResult dig() {
		checkCredentials(Credential.VOTE);
		
		return session.getSingleResult(new Command("link", "dig", id), VoteResult.class);
	}
	
	public VoteResult bury() {
		checkCredentials(Credential.VOTE);
		
		return session.getSingleResult(new Command("link", "bury", id), VoteResult.class);
	}
	
	public VoteResult cancelVote() {
		checkCredentials(Credential.VOTE);
		
		return session.getSingleResult(new Command("link", "cancel", id), VoteResult.class);
	}
	
	public Boolean observe() {
		checkCredentials(Credential.PROFILE);
		
		Boolean[] result = session.getSingleResult(new Command("link", "observe", id), Boolean[].class);
		return result.length == 1 && result[0];
	}
	
	public Boolean addToFavorites() {
		checkCredentials(Credential.PROFILE);
		
		Boolean[] result = session.getSingleResult(new Command("link", "favorite", id), Boolean[].class);
		return result.length == 1 && result[0];
	}
	
	public Integer addComment(String content) {
		return addComment(content, 0);
	}
	
	public Integer addComment(String content, Integer parent) {
		checkCredentials(Credential.COMMENTS);
		
		Command cmd = new Command("comments", "add", new String[] { id.toString(), parent.toString() });
		cmd.addPostParameter("body", content);

		return Integer.valueOf(session.getMapResult(cmd).get("id"));
	}
	
	public Integer digComment(Integer id) {
		checkCredentials(Credential.VOTE);
		
		Command cmd = new Command("comments", "plus", new String[] { this.id.toString(), id.toString() });
		return Integer.valueOf(session.getMapResult(cmd).get("vote"));
	}
	
	public Integer buryComment(Integer id) {
		checkCredentials(Credential.VOTE);
		
		Command cmd = new Command("comments", "minus", new String[] { this.id.toString(), id.toString() });
		return Integer.valueOf(session.getMapResult(cmd).get("vote"));
	}
	
	public Integer digRelated(Integer id) {
		checkCredentials(Credential.VOTE);
		
		Command cmd = new Command("related", "plus", new String[] { this.id.toString(), id.toString() });
		return Integer.valueOf(session.getMapResult(cmd).get("vote"));
	}
	
	public Integer buryRelated(Integer id) {
		checkCredentials(Credential.VOTE);
		
		Command cmd = new Command("related", "minus", new String[] { this.id.toString(), id.toString() });
		return Integer.valueOf(session.getMapResult(cmd).get("vote"));
	}
}
