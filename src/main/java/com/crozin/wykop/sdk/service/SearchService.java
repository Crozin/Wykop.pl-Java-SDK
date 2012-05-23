package com.crozin.wykop.sdk.service;

import java.util.Date;
import java.util.List;

import com.crozin.wykop.sdk.Command;
import com.crozin.wykop.sdk.Session;
import com.crozin.wykop.sdk.domain.Entry;
import com.crozin.wykop.sdk.domain.Link;
import com.crozin.wykop.sdk.domain.Profile;
import com.crozin.wykop.sdk.util.Range;

public class SearchService extends AbstractService {
	public static class SearchCriteria {
		public enum SearchType {
			ALL, PROMOTED, ARCHIVE, DUPLICATES
		}
		
		public enum SortType {
			BEST, DIGGS, COMMENTS, DATE
		}
		
		public enum FilterType {
			ALL, TODAY, YESTERDAY, WEEK, MONTH
		}
		
		private SearchType searchType = SearchType.ALL;
		private SortType sortType = SortType.BEST;
		private Integer minimalVotes = 0;
		
		private FilterType filterType = FilterType.ALL;
		private Range<Date> filterRange;
		
		public SearchType getSearchType() {
			return searchType;
		}
		
		public void setSearchType(SearchType searchType) {
			this.searchType = searchType;
		}
		
		public SortType getSortType() {
			return sortType;
		}
		
		public void setSortType(SortType sortType) {
			this.sortType = sortType;
		}
		
		public Integer getMinimalVotes() {
			return minimalVotes;
		}
		
		public void setMinimalVotes(Integer minimalVotes) {
			this.minimalVotes = minimalVotes;
		}
		
		public FilterType getFilterType() {
			return filterType;
		}

		public void setFilterType(FilterType filterType) {
			this.filterType = filterType;
			this.filterRange = null;
		}
		
		public boolean hasFilterType() {
			return filterType != null;
		}

		public Range<Date> getFilterRange() {
			return filterRange;
		}

		public void setFilterRange(Range<Date> filterRange) {
			this.filterRange = filterRange;
			this.filterType = null;
		}
		
		public boolean hasFilterRange() {
			return filterRange != null;
		}
	}
	
	public SearchService(Session session) {
		super(session);
	}
	
	public List<Link> findLinks(String query, Integer chunk) {
		return findLinks(query, chunk, new SearchCriteria());
	}

	public List<Link> findLinks(String query, Integer chunk, SearchCriteria searchCriteria) {
		Command cmd = new Command("search", "index");
		cmd.addParameter("page", chunk);
		cmd.addPostParameter("q", query);
		
		cmd.addPostParameter("what", searchCriteria.getSearchType().toString().toLowerCase());
		cmd.addPostParameter("sort", searchCriteria.getSortType().toString().toLowerCase());
		cmd.addPostParameter("votes", searchCriteria.getMinimalVotes());
		
		if (searchCriteria.hasFilterType()) {
			cmd.addPostParameter("when", searchCriteria.getFilterType().toString().toLowerCase());
		} else if (searchCriteria.hasFilterRange()) {
			Range<Date> filterRange = searchCriteria.getFilterRange();
			
			cmd.addPostParameter("when", "range");
			cmd.addPostParameter("from", filterRange.getLowerBound());
			cmd.addPostParameter("to", filterRange.getUpperBound());
		}
		
		return session.getResultList(cmd, Link.class);
	}
	
	public List<Entry> findEntries(String query, Integer chunk) {
		Command cmd = new Command("search", "entries");
		cmd.addParameter("page", chunk);
		cmd.addPostParameter("q", query);
		
		return session.getResultList(cmd, Entry.class);
	}
	
	public List<Profile> findProfiles(String query, Integer chunk) {
		Command cmd = new Command("search", "profiles");
		cmd.addParameter("page", chunk);
		cmd.addPostParameter("q", query);
		
		return session.getResultList(cmd, Profile.class);
	}
}
