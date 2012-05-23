package com.crozin.wykop.sdk.domain;

import java.net.URL;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Group {
	public static class ClientStatus {
		private final Boolean isMember;
		
		ClientStatus(Boolean isMember) {
			this.isMember = isMember;
		}
		
		public Boolean isMember() {
			return isMember;
		}
	}
	
	private String id;
	
	@JsonIgnore
	private ClientStatus clientStatus;
	
	@JsonProperty("link")
	private URL url;
	
	private String name;
	private String description;
	
	private URL logo;
	
	@JsonProperty("members_count")
	private Integer membersCount;

	@JsonProperty
	private Boolean is_member;
	
	public String getId() {
		return id;
	}

	public URL getUrl() {
		return url;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public URL getLogo() {
		return logo;
	}

	public Integer getMembersCount() {
		return membersCount;
	}
	
	public ClientStatus getClientStatus() {
		if (clientStatus == null) {
			clientStatus = new ClientStatus(is_member);
		}
		
		return clientStatus;
	}
}
