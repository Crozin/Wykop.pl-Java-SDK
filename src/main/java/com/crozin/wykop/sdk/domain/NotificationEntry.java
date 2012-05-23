package com.crozin.wykop.sdk.domain;

import java.net.URL;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NotificationEntry {
	private Integer id;
	
	@JsonProperty("body")
	private String content;
	
	private URL url;
	
	public Integer getId() {
		return id;
	}
	
	public String getContent() {
		return content;
	}

	public URL getUrl() {
		return url;
	}
}
