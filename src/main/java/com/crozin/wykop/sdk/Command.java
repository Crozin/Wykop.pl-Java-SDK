package com.crozin.wykop.sdk;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Command {
	private final String resource;
	private final String method;
	
	private List<String> arguments = new LinkedList<String>();
	private Map<String, String> parameters = new HashMap<String, String>();
	private Map<String, String> postParameters = new TreeMap<String, String>();
	
	private Boolean clear = false;

	public Command(String resource, String method) {
		this.resource = resource;
		this.method = method;
	}

	public Command(String resource, String method, Object argument) {
		this(resource, method);
		this.addArgument(argument);
	}

	public Command(String resource, String method, String[] arguments) {
		this(resource, method);
		this.arguments.addAll(Arrays.asList(arguments));
	}

	public String getResource() {
		return resource;
	}

	public String getMethod() {
		return method;
	}

	public List<String> getArguments() {
		return arguments;
	}
	
	public void setArguments(List<String> arguments) {
		this.arguments = arguments;
	}

	public void addArgument(Object argument) {
		arguments.add(argument.toString());
	}
	
	public boolean hasArguments() {
		return arguments.size() > 0;
	}
	
	public Map<String, String> getParameters() {
		return parameters;
	}

	public void setParameters(Map<String, String> parameters) {
		this.parameters = parameters;
	}
	
	public void addParameter(String key, Object value) {
		parameters.put(key, value.toString());
	}
	
	public boolean hasParameters() {
		return parameters.size() > 0;
	}
	
	public Map<String, String> getPostParameters() {
		return postParameters;
	}

	public void setPostParameters(Map<String, String> postParameters) {
		this.postParameters = postParameters;
	}
	
	public void addPostParameter(String key, Object value) {
		postParameters.put(key, value.toString());
	}
	
	public boolean hasPostParameters() {
		return postParameters.size() > 0;
	}

	public Boolean getClear() {
		return clear;
	}

	public void setClear(Boolean clear) {
		this.clear = clear;
	}
}
