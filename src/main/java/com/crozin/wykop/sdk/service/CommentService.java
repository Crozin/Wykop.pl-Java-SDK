package com.crozin.wykop.sdk.service;

import com.crozin.wykop.sdk.AuthenticatedSession;
import com.crozin.wykop.sdk.Command;
import com.crozin.wykop.sdk.Credential;

public class CommentService extends AbstractDomainService<Integer> {
	public CommentService(AuthenticatedSession session, Integer id) {
		super(session, id);
	}
	
	public Integer edit(String content) {
		checkCredentials(Credential.COMMENTS);
		
		Command cmd = new Command("comments", "edit", id);
		cmd.addPostParameter("body", content);
		
		return Integer.valueOf(session.getMapResult(cmd).get("id"));
	}
	
	public Integer delete() {
		checkCredentials(Credential.COMMENTS);
		
		Command cmd = new Command("comments", "delete", id);
		return Integer.valueOf(session.getMapResult(cmd).get("id"));
	}
}
