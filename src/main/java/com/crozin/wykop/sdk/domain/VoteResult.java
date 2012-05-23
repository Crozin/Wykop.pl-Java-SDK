package com.crozin.wykop.sdk.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class VoteResult {
	private final Boolean approved;
	private final Integer totalVotes;
	
	@JsonCreator
	VoteResult (@JsonProperty("success") Boolean approved, @JsonProperty("vote") Integer totalVotes) {
		this.approved = approved;
		this.totalVotes = totalVotes;
	}

	public Boolean isApproved() {
		return approved;
	}

	public Integer getTotalVotes() {
		return totalVotes;
	}
}