package com.crozin.wykop.sdk.service;

import com.crozin.wykop.sdk.Credential;
import com.crozin.wykop.sdk.Session;

abstract class AbstractService {
	protected final Session session;
	
	public AbstractService(Session session) {
		this.session = session;
	}
	
	protected void checkCredentials(Credential credentialType) {
		//TODO: Check credentials on the client-side.
	}
}
