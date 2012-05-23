package com.crozin.wykop.sdk.repository;

import com.crozin.wykop.sdk.Session;

abstract class AbstractDomainRepository<T> extends AbstractRepository {
	protected final T id;
	
	public AbstractDomainRepository(Session session, T id) {
		super(session);
		this.id = id;
	}
}
