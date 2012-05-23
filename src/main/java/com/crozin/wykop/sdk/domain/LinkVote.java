package com.crozin.wykop.sdk.domain;

import java.util.Date;

import com.crozin.wykop.sdk.json.DateDeserializer;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class LinkVote {
	public enum Type {
		DIG, BURY;
	}
	
	@JsonProperty("link_id")
	private Integer id;
	
	private String type;
	
	@JsonDeserialize(using = DateDeserializer.class)
	private Date date;

	public Integer getId() {
		return id;
	}

	public Type getType() {
		return Type.valueOf(type.toUpperCase());
	}

	public Date getDate() {
		return date;
	}
}
