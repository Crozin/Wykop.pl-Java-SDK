package com.crozin.wykop.sdk.repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.crozin.wykop.sdk.Command;
import com.crozin.wykop.sdk.Session;
import com.crozin.wykop.sdk.domain.Link;
import com.fasterxml.jackson.databind.type.MapType;
import com.fasterxml.jackson.databind.type.TypeFactory;

public class LinksRepository extends AbstractRepository {
	public enum DateRange {
		DAY   ("day"),
		WEEK  ("week"),
		MONTH ("month");
		
		private final String dateRange;
		
		DateRange(String dateRange) {
			this.dateRange = dateRange;
		}
		
		public String getDateRange() {
			return dateRange;
		}
		
		@Override
		public String toString() {
			return dateRange;
		}
	}
	
	private final MapType linksYearType;
	
	public LinksRepository(Session session) {
		super(session);
		
		TypeFactory tf = session.getObjectMapper().getTypeFactory();
		linksYearType = tf.constructMapType(LinkedHashMap.class, tf.constructType(Integer.class), tf.constructParametricType(ArrayList.class, Link.class));
	}

	public List<Link> getPromoted() {
		return doGet(new Command("links", "promoted"));
	}
	
	public List<Link> getPromoted(DateRange dateRange, Integer chunk) {
		return doGet(new Command("links", "promoted"), dateRange, chunk);
	}
	
	public List<Link> getUpcoming() {
		return doGet(new Command("links", "upcoming"));
	}
	
	public List<Link> getUpcoming(DateRange dateRange, Integer chunk) {
		return doGet(new Command("links", "upcoming"), dateRange, chunk);
	}
	
	public List<Link> getPopularPromoted() {
		return doGet(new Command("popular", "promoted"));
	}
	
	public List<Link> getPopularPromoted(DateRange dateRange, Integer chunk) {
		return doGet(new Command("popular", "promoted"), dateRange, chunk);
	}
	
	public List<Link> getPopularUpcoming() {
		return doGet(new Command("popular", "upcoming"));
	}
	
	public List<Link> getPopularUpcoming(DateRange dateRange, Integer chunk) {
		return doGet(new Command("popular", "upcoming"), dateRange, chunk);
	}
	
	public Map<Integer, List<Link>> getHits(Integer year) {
		Command cmd = new Command("top", "index", year);
		
		try {
			 return session.getObjectMapper().readValue(session.execute(cmd), linksYearType);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	public List<Link> getHits(Integer year, Integer month, Integer chunk) {
		Command cmd = new Command("top", "date");
		cmd.addArgument(year);
		cmd.addArgument(month);
		cmd.addPostParameter("page", chunk);
		
		return session.getResultList(cmd, Link.class);
	}
	
	private List<Link> doGet(Command cmd) {
		return session.getResultList(cmd, Link.class);
	}
	
	private List<Link> doGet(Command cmd, DateRange dateRange, Integer chunk) {
		cmd.addParameter("page", chunk.toString());
		cmd.addParameter("sort", dateRange.getDateRange());
		
		return session.getResultList(cmd, Link.class);
	}
}