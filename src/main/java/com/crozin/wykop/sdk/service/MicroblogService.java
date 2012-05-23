package com.crozin.wykop.sdk.service;

import com.crozin.wykop.sdk.AuthenticatedSession;
import com.crozin.wykop.sdk.Command;
import com.crozin.wykop.sdk.Credential;

public class MicroblogService extends AbstractService {
	public MicroblogService(AuthenticatedSession session) {
		super(session);
	}

	public Integer addEntry(String content) {
		checkCredentials(Credential.MICROBLOG);
		
		Command cmd = new Command("entries", "add");
		cmd.addPostParameter("body", content);

		return Integer.valueOf(session.getMapResult(cmd).get("id"));
	}
	
	public Integer editEntry(Integer id, String content) {
		checkCredentials(Credential.MICROBLOG);
		
		Command cmd = new Command("entries", "edit", id);
		cmd.addPostParameter("body", content);

		return Integer.valueOf(session.getMapResult(cmd).get("id"));
	}
	
	public Integer deleteEntry(Integer id) {
		checkCredentials(Credential.MICROBLOG);
		
		Command cmd = new Command("entries", "delete", id);
		return Integer.valueOf(session.getMapResult(cmd).get("id"));
	}
	
	public Integer addEntryComment(Integer entryId, String content) {
		checkCredentials(Credential.MICROBLOG);
		
		Command cmd = new Command("entries", "addComment", entryId);
		cmd.addPostParameter("body", content);

		return Integer.valueOf(session.getMapResult(cmd).get("id"));
	}
	
	public Integer editEntryComment(Integer entryId, Integer commentId, String content) {
		checkCredentials(Credential.MICROBLOG);
		
		Command cmd = new Command("entries", "addComment");
		cmd.addArgument(entryId);
		cmd.addArgument(commentId);
		cmd.addPostParameter("body", content);

		return Integer.valueOf(session.getMapResult(cmd).get("id"));
	}
	
	public Integer deleteEntryComment(Integer entryId, Integer commentId) {
		checkCredentials(Credential.MICROBLOG);
		
		Command cmd = new Command("entries", "addComment");
		cmd.addArgument(entryId);
		cmd.addArgument(commentId);

		return Integer.valueOf(session.getMapResult(cmd).get("id"));
	}
}
