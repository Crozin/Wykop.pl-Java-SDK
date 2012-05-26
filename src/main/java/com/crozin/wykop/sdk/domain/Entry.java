package com.crozin.wykop.sdk.domain;

import java.net.URL;
import java.util.Date;
import java.util.List;

import com.crozin.wykop.sdk.json.DateDeserializer;
import com.crozin.wykop.sdk.util.DeserializationUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class Entry extends MyWykop {
	private Integer id;
	
	@JsonIgnore
	private Author _author;
	
	@JsonIgnore
	private Author _receiver;
	
	@JsonProperty("body")
	private String content;
	
	private URL url;
	
	@JsonDeserialize(using = DateDeserializer.class)
	private Date date;
	
	private Boolean blocked;
	
	private List<EntryComment> comments;
	
	@JsonProperty 
	@SuppressWarnings("unused")
	private String author, author_sex;
	
	@JsonProperty 
	@SuppressWarnings("unused")
	private Integer author_group;
	
	@JsonProperty 
	@SuppressWarnings("unused")
	private URL author_avatar, author_avatar_med, author_avatar_lo, author_avatar_big;
	
	@JsonProperty 
	@SuppressWarnings("unused")
	private String receiver, receiver_sex;
	
	@JsonProperty 
	@SuppressWarnings("unused")
	private Integer receiver_group;
	
	@JsonProperty 
	@SuppressWarnings("unused")
	private URL receiver_avatar, receiver_avatar_med, receiver_avatar_lo, receiver_avatar_big;
	
	public Integer getId() {
		return id;
	}

	public Author getAuthor() {
		if (_author == null) {
			_author = DeserializationUtils.readAuthor(this);
		}
		
		return _author;
	}
	
	public Author getReceiver() {
		if (_receiver == null) {
			_receiver = DeserializationUtils.readAuthor(this, "receiver");
		}
		
		return _receiver;
	}

	public String getContent() {
		return content;
	}

	public URL getUrl() {
		return url;
	}

	public Date getDate() {
		return date;
	}
	
	public Boolean isBlocked() {
		return blocked;
	}
	
	public List<EntryComment> getComments() {
		return comments;
	}
}
