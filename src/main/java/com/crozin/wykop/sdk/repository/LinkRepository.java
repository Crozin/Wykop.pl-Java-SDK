package com.crozin.wykop.sdk.repository;

import java.util.List;

import com.crozin.wykop.sdk.Command;
import com.crozin.wykop.sdk.Session;
import com.crozin.wykop.sdk.domain.Bury;
import com.crozin.wykop.sdk.domain.Comment;
import com.crozin.wykop.sdk.domain.Dig;
import com.crozin.wykop.sdk.domain.RelatedLink;

public class LinkRepository extends AbstractDomainRepository<Integer> {
	public LinkRepository(Session session, Integer id) {
		super(session, id);
	}

	public List<Dig> getDigs() {
		return session.getResultList(new Command("link", "digs", id), Dig.class);
	}
	
	public List<Bury> getBuries() {
		return session.getResultList(new Command("link", "reports", id), Bury.class);
	}
	
	public List<Comment> getComments() {
		return session.getResultList(new Command("link", "comments", id), Comment.class);
	}
	
	public List<RelatedLink> getRelatedLinks() {
		return session.getResultList(new Command("link", "related", id), RelatedLink.class);
	}
}
