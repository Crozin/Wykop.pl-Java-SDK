package com.crozin.wykop.sdk.domain;

import java.net.URL;
import java.util.Date;

import com.crozin.wykop.sdk.json.DateDeserializer;
import com.crozin.wykop.sdk.util.DeserializationUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonIgnoreProperties({ "entry_id" })
public class EntryComment {
	private Integer id;
	
	@JsonIgnore
	private Author _author;
	
	@JsonDeserialize(using = DateDeserializer.class)
	private Date date;
	
	@JsonProperty("body")
	private String content;

	private Boolean blocked;
	
	@JsonProperty 
	@SuppressWarnings("unused")
	private String author, author_sex;
	
	@JsonProperty 
	@SuppressWarnings("unused")
	private Integer author_group;
	
	@JsonProperty 
	@SuppressWarnings("unused")
	private URL author_avatar, author_avatar_med, author_avatar_lo, author_avatar_big;
	
	public Integer getId() {
		return id;
	}

	public Author getAuthor() {
		if (_author == null) {
			_author = DeserializationUtils.readAuthor(this);
		}
		
		return _author;
	}

	public Date getDate() {
		return date;
	}

	public String getContent() {
		return content;
	}
	
	public Boolean isBlocked() {
		return blocked;
	}
}
