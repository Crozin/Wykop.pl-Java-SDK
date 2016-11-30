package com.crozin.wykop.sdk.domain;

import java.net.URL;
import java.util.Date;

import com.crozin.wykop.sdk.json.DateDeserializer;
import com.crozin.wykop.sdk.util.DeserializationUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class Bury {
	public enum Reason {
		DUPLICATE (1),
		SPAM (2),
		FALSE_INFORMATION (3),
		INAPPROPRIATE_CONTENT (4), 
		POOR_CONTENT (5),
		INAPPROPRIATE_CONTENT_GROUP (6);
		
		private final Integer id;
		
		Reason(Integer id) {
			this.id = id;
		}
		
		public Integer getId() {
			return id;
		}
		
		public static Reason valueOf(Integer id) {
			for (Reason reason : values()) {
				if (reason.getId().equals(id)) {
					return reason;
				}
			}
			
			throw new IllegalArgumentException("Invalid bury reason ID");
		}
	}
	
	@JsonIgnore
	private Author _author;
	
	private Integer reason;
	
	@JsonDeserialize(using = DateDeserializer.class)
	private Date date;
	
	@JsonProperty
	private String author, author_sex;
	
	@JsonProperty
	private Integer author_group;
	
	@JsonProperty
	private URL author_avatar, author_avatar_med, author_avatar_lo, author_avatar_big;
	
	public Author getAuthor() {
		if (_author == null) {
			_author = DeserializationUtils.readAuthor(this);
		}
		
		return _author;
	}

	public Reason getReason() {
		return Reason.valueOf(reason);
	}
	
	public Date getDate() {
		return date;
	}
}
