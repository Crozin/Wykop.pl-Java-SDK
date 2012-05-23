package com.crozin.wykop.sdk.repository;

import com.crozin.wykop.sdk.Session;

public abstract class AbstractRepository {
	protected final Session session;
	
	public AbstractRepository(Session session) {
		this.session = session;
	}
}
