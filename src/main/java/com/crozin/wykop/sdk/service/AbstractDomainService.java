package com.crozin.wykop.sdk.service;

import com.crozin.wykop.sdk.Session;

abstract class AbstractDomainService<T> extends AbstractService {
	protected final T id;
	
	public AbstractDomainService(Session session, T id) {
		super(session);
		this.id = id;
	}
}
