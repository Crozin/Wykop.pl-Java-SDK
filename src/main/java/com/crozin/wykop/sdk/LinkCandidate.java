package com.crozin.wykop.sdk;

import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import com.crozin.wykop.sdk.domain.Link.Type;

public class LinkCandidate {
	private URL url;
	private String title;
	private String description;
	private Set<String> tags = new HashSet<String>();
	private String group;
	
	private Type type = Type.NEWS;
	private Boolean adultOnly = false;
	
	public URL getUrl() {
		return url;
	}
	
	public void setUrl(URL url) {
		this.url = url;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public Set<String> getTags() {
		return tags;
	}
	
	public void setTags(Set<String> tags) {
		this.tags = tags;
	}
	
	public void addTag(String tag) {
		tags.add(tag);
	}
	
	public Type getType() {
		return type;
	}
	
	public void setType(Type type) {
		this.type = type;
	}
	
	public String getGroup() {
		return group;
	}
	
	public void setGroup(String group) {
		this.group = group;
	}
	
	public Boolean isAdultOnly() {
		return adultOnly;
	}
	
	public void setAdultOnly(Boolean adultOnly) {
		this.adultOnly = adultOnly;
	}
}
