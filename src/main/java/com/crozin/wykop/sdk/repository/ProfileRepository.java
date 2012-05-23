package com.crozin.wykop.sdk.repository;

import java.util.List;

import com.crozin.wykop.sdk.Command;
import com.crozin.wykop.sdk.Session;
import com.crozin.wykop.sdk.domain.Group;
import com.crozin.wykop.sdk.domain.Link;
import com.crozin.wykop.sdk.domain.Profile;

public class ProfileRepository extends AbstractDomainRepository<String> {
	public ProfileRepository(Session session, String id) {
		super(session, id);
	}
	
	public List<Link> getLinks() {
		return getLinks(0);
	}
	
	public List<Link> getLinks(Integer chunk) {
		return doGetLinks("added", chunk);
	}
	
	public List<Link> getPublishedLinks() {
		return getPublishedLinks(0);
	}
	
	public List<Link> getPublishedLinks(Integer chunk) {
		return doGetLinks("published", chunk);
	}
	
	public List<Link> getCommentedLinks() {
		return getCommentedLinks(0);
	}
	
	public List<Link> getCommentedLinks(Integer chunk) {
		return doGetLinks("commented", chunk);
	}
	
	public List<Link> getDiggedLinks() {
		return getDiggedLinks(0);
	}
	
	public List<Link> getDiggedLinks(Integer chunk) {
		return doGetLinks("digged", chunk);
	}
	
	public List<Link> getBuriedLinks() {
		return getBuriedLinks(0);
	}
	
	public List<Link> getBuriedLinks(Integer chunk) {
		return doGetLinks("buried", chunk);
	}
	
	public List<Link> getFavorites() {
		return getFavorites(0);
	}
	
	public List<Link> getFavorites(Integer chunk) {
		return doGetLinks("favorites", chunk);
	}
	
	public List<Group> getGroups() {
		return getGroups(0);
	}
	
	public List<Group> getGroups(Integer chunk) {
		Command cmd = new Command("profile", "groups", id);
		cmd.addParameter("page", chunk.toString());
		
		return session.getResultList(cmd, Group.class);
	}
	
	public List<Profile> getFollowers() {
		return getFollowers(0);
	}
	
	public List<Profile> getFollowers(Integer chunk) {
		Command cmd = new Command("profile", "followers", id);
		cmd.addParameter("page", chunk.toString());
		
		return session.getResultList(cmd, Profile.class);
	}
	
	public List<Profile> getFollowed() {
		return getFollowed(0);
	}
	
	public List<Profile> getFollowed(Integer chunk) {
		Command cmd = new Command("profile", "followed", id);
		cmd.addParameter("page", chunk.toString());
		
		return session.getResultList(cmd, Profile.class);
	}
	
	private List<Link> doGetLinks(String method, Integer chunk) {
		Command cmd = new Command("profile", method, id);
		cmd.addParameter("page", chunk.toString());
		
		return session.getResultList(cmd, Link.class);
	}
}