package com.crozin.wykop.sdk.service;

import java.util.List;

import com.crozin.wykop.sdk.AuthenticatedSession;
import com.crozin.wykop.sdk.Command;
import com.crozin.wykop.sdk.Credential;
import com.crozin.wykop.sdk.LinkCandidate;
import com.crozin.wykop.sdk.domain.Group;
import com.crozin.wykop.sdk.domain.Link;
import com.crozin.wykop.sdk.domain.MyWykop;
import com.crozin.wykop.sdk.domain.Notification;
import com.crozin.wykop.sdk.domain.Profile;
import com.crozin.wykop.sdk.util.CollectionsUtils;

public class ClientService extends AbstractService {
	public enum EntryFilter {
		ALL ("index"), OBSERVED ("observing"), OWN ("mine"), RECEIVED ("received");
		
		private final String method;
		
		EntryFilter(String method) {
			this.method = method;
		}
		
		public String getMethod() {
			return method;
		}
	}
	
	public ClientService(AuthenticatedSession session) {
		super(session);
	}

	public Link addLink(LinkCandidate link) {
		Command cmd = new Command("add", "index");
		cmd.addParameter("group", link.getGroup());
		cmd.addPostParameter("url", link.getUrl());
		cmd.addPostParameter("title", link.getTitle());
		cmd.addPostParameter("description", link.getDescription());
		cmd.addPostParameter("tags", CollectionsUtils.join(link.getTags(), " "));
		cmd.addPostParameter("type", link.getType().toString().toLowerCase());
		cmd.addPostParameter("plus18", link.isAdultOnly() ? 1 : 0);
		
		return session.getSingleResult(cmd, Link.class);
	}
	
	public Profile getProfile() {
		return session.find(0, Profile.class);
	}
	
	public Boolean observe(String username) {
		checkCredentials(Credential.PROFILE);
		
		Boolean[] result = session.getSingleResult(new Command("profile", "observe", username), Boolean[].class);
		return result.length == 1 && result[0];
	}
	
	public Boolean unobserve(String username) {
		checkCredentials(Credential.PROFILE);
		
		Boolean[] result = session.getSingleResult(new Command("profile", "unobserve", username), Boolean[].class);
		return result.length == 1 && result[0];
	}
	
	public List<Link> getFavorites() {
		return session.getResultList(new Command("user", "favorites"), Link.class);
	}
	
	public List<Link> getObserved() {
		return session.getResultList(new Command("user", "observed"), Link.class);
	}
	
	public List<Group> getGroups() {
		return session.getResultList(new Command("user", "groups"), Group.class);
	}
	
	public Integer getNotificationsCount() {
		Command cmd = new Command("mywykop", "notificationsCount");

		return Integer.valueOf(session.getMapResult(cmd).get("count"));
	}
	
	public List<Notification> getNotifications(Integer chunk) {
		Command cmd = new Command("mywykop", "notifications");
		cmd.addParameter("page", chunk);
		
		return session.getResultList(cmd, Notification.class);
	}
	
	public List<MyWykop> getMyWykopEntries(Integer chunk) {
		return getMyWykopEntries(chunk, EntryFilter.ALL);
	}
	
	public List<MyWykop> getMyWykopEntries(Integer chunk, EntryFilter entryFilter) {
		Command cmd = new Command("mywykop", entryFilter.getMethod());
		cmd.addParameter("page", chunk);
		
		return session.getResultList(cmd, MyWykop.class);
	}
}
