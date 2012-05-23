package com.crozin.wykop.sdk.domain;

import java.net.URL;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.crozin.wykop.sdk.json.DateDeserializer;
import com.crozin.wykop.sdk.util.DeserializationUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class Notification {
	private Integer id;
	
	@JsonIgnore
	private Author _author;
	
	@JsonProperty("body")
	private String content;
	
	@JsonDeserialize(using = DateDeserializer.class)
	private Date date;
	
	@JsonProperty
	private String type;
	
	@JsonProperty
	private Link link;
	
	@JsonProperty
	private NotificationEntry entry;
	
	@JsonProperty
	private Group group;
	
	@JsonProperty("comment")
	private Map<String, String> attributes = new HashMap<String, String>();
	
	@JsonProperty("new")
	private Boolean unreaded;
	
	@JsonProperty 
	@SuppressWarnings("unused")
	private String author, author_sex;
	
	@JsonProperty 
	@SuppressWarnings("unused")
	private Integer author_group;
	
	@JsonProperty 
	@SuppressWarnings("unused")
	private URL author_avatar, author_avatar_med, author_avatar_lo;
	
	public Integer getId() {
		return id;
	}
	
	public Author getAuthor() {
		if (_author == null) {
			_author = DeserializationUtils.readAuthor(this);
		}
		
		return _author;
	}
	
	public String getContent() {
		return content;
	}
	
	public Date getDate() {
		return date;
	}
	
	public String getType() {
		return type;
	}
	
	public Link getLink() {
		return link;
	}
	
	public NotificationEntry getEntry() {
		return entry;
	}
	
	public Group getGroup() {
		return group;
	}
	
	public Map<String, String> getAttributes() {
		return attributes;
	}
	
	public Integer getRelatedCommentId() {
		return Integer.valueOf(attributes.get("id"));
	}
	
	public Boolean isUnreaded() {
		return unreaded;
	}
}
