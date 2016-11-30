package com.crozin.wykop.sdk;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.crozin.wykop.sdk.exception.ApiException;
import com.crozin.wykop.sdk.exception.ConnectionException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.DeserializationProblemHandler;
import com.fasterxml.jackson.databind.type.MapType;

public class Application {
	private static final Logger logger = LoggerFactory.getLogger(Application.class);
	
	final String publicKey;
	final String privateKey;
	String userKey;
	
	final ObjectMapper om;
	final MapType mapType;
	
	public Application(String publicKey, String privateKey) {
		this.publicKey = publicKey;
		this.privateKey = privateKey;
		
		om = new ObjectMapper();
		om.addHandler(new DeserializationProblemHandler() {
			@Override
			public boolean handleUnknownProperty(DeserializationContext ctxt, JsonParser jp, JsonDeserializer<?> deserializer, Object beanOrClass, String propertyName) throws IOException, JsonProcessingException {
				logger.warn("Unknow property \"{}\" during deserialization of {}", propertyName, beanOrClass.getClass().getCanonicalName());
				
				ctxt.getParser().skipChildren();
				
				return true;
			}
		});
		
		mapType = om.getTypeFactory().constructMapType(LinkedHashMap.class, String.class, String.class);
	}
	
	public Session openSession() throws ApiException {
		return new Session(this);
	}
	
	public AuthenticatedSession openSession(String accountKey) throws ApiException, ConnectionException {
		return doOpenSession(Collections.singletonMap("accountkey", accountKey));
	}
	
	public AuthenticatedSession openSession(String username, String password) throws ApiException, ConnectionException  {
		Map<String, String> postParameters = new HashMap<String, String>();
		postParameters.put("login", username);
		postParameters.put("password", password);
		
		return doOpenSession(postParameters);
	}
	
	private AuthenticatedSession doOpenSession(Map<String, String> postParameters) throws ApiException, ConnectionException {
		Command cmd = new Command("user", "login");
		cmd.setPostParameters(postParameters);
		
		Session session = openSession();
		String authenticationKey = session.getMapResult(cmd).get("userkey");

		return new AuthenticatedSession(this, authenticationKey);
	}
}
