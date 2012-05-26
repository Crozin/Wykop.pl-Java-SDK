package com.crozin.wykop.sdk.domain;

import java.net.URL;
import java.util.Date;

import com.crozin.wykop.sdk.json.DateDeserializer;
import com.crozin.wykop.sdk.util.DeserializationUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonIgnoreProperties({ "link", "user_vote" })
public class Comment {
	public enum Status {
		OWN, NEW, READED
	}
	
	private Integer id;
	
	@JsonProperty("parent_id")
	private Integer parentId;
	
	@JsonProperty("body")
	private String content;
	
	@JsonDeserialize(using = DateDeserializer.class)
	private Date date;

	@JsonProperty("vote_count_plus")
	private Integer possitiveVotes;
	
	@JsonProperty("vote_count_minus")
	private Integer negativeVotes;
	
	@JsonProperty("can_vote")
	private Boolean voteAvailable;
	
	private Boolean blocked;
	
	@JsonIgnore
	private Author _author;
	
	@JsonProperty("vote_count")
	private Integer totalVotes;
	
	private String status;
	
	private Boolean deleted;

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

	public Date getDate() {
		return date;
	}

	public Integer getPossitiveVotes() {
		return possitiveVotes;
	}
	
	public Integer getNegativeVotes() {
		return negativeVotes;
	}
	
	public Boolean isVoteAvailable() {
		return voteAvailable;
	}
	
	public Boolean isBlocked() {
		return blocked;
	}
	
	public Author getAuthor() {
		if (_author == null) {
			_author = DeserializationUtils.readAuthor(this);
		}
		
		return _author;
	}
	
	public Integer getTotalVotes() {
		return totalVotes;
	}

	public String getContent() {
		return content;
	}

	public Integer getParentId() {
		return parentId;
	}

	public Status getStatus() {
		return Status.valueOf(status.toUpperCase());
	}
	
	public Boolean isDeleted() {
		return deleted;
	}
}
