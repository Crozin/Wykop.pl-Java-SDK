package com.crozin.wykop.sdk.domain;

import java.net.URL;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RelatedLink {
	private Integer id;
	private URL url;
	private String title;
	
	@JsonProperty("vote_count")
	private Integer votesCount;
	
	@JsonProperty("entry_count")
	private Integer entriesCount;

	protected Integer user_vote;
	
	public Integer getId() {
		return id;
	}

	public URL getUrl() {
		return url;
	}

	public String getTitle() {
		return title;
	}

	public Integer getVotesCount() {
		return votesCount;
	}

	public Integer getEntriesCount() {
		return entriesCount;
	}
}
