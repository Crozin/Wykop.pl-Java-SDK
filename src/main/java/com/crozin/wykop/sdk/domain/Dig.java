package com.crozin.wykop.sdk.domain;

import java.net.URL;
import java.util.Date;

import com.crozin.wykop.sdk.json.DateDeserializer;
import com.crozin.wykop.sdk.util.DeserializationUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class Dig {
	@JsonIgnore
	private Author _author;
	
	@JsonDeserialize(using = DateDeserializer.class)
	private Date date;
	
	@JsonProperty 
	@SuppressWarnings("unused")
	private String author, author_sex;
	
	@JsonProperty 
	@SuppressWarnings("unused")
	private Integer author_group;
	
	@JsonProperty 
	@SuppressWarnings("unused")
	private URL author_avatar, author_avatar_med, author_avatar_lo, author_avatar_big;
	
	public Author getAuthor() {
		if (_author == null) {
			_author = DeserializationUtils.readAuthor(this);
		}
		
		return _author;
	}
	
	public Date getDate() {
		return date;
	}
}
