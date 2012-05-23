package com.crozin.wykop.sdk;

import com.crozin.wykop.sdk.domain.Comment;
import com.crozin.wykop.sdk.domain.Link;
import com.crozin.wykop.sdk.service.LinkService;
import com.crozin.wykop.sdk.service.ClientService;
import com.crozin.wykop.sdk.service.CommentService;
import com.crozin.wykop.sdk.service.MicroblogService;

public class AuthenticatedSession extends Session {
	AuthenticatedSession(Application app, String authenticationKey) {
		super(app);
		
		apiParameters.put("userkey", authenticationKey);
	}
	
	public ClientService getClientService() {
		return new ClientService(this);
	}
	
	public CommentService getCommentService(Comment comment) {
		return new CommentService(this, comment.getId());
	}
	
	public CommentService getCommentService(Integer id) {
		return new CommentService(this, id);
	}
	
	public LinkService getLinkService(Link link) {
		return new LinkService(this, link.getId());
	}
	
	public LinkService getLinkService(Integer id) {
		return new LinkService(this, id);
	}
	
	public MicroblogService getMicroblogService() {
		return new MicroblogService(this);
	}
}