package com.crozin.wykop.sdk.domain;

import java.net.URL;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.crozin.wykop.sdk.json.DateDeserializer;
import com.crozin.wykop.sdk.json.TagsDeserializer;
import com.crozin.wykop.sdk.util.DeserializationUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class Link {
	public enum Type {
		NEWS, PICTURE, VIDEO
	}
	
	public enum Status {
		PROMOTED, UPCOMING, OUTDATED, DUPLICATE, REJECTED
	}
	
	public static class ClientStatus {
		private final Integer vote;
		private final Boolean favorite;
		private final Boolean observed;
		private final List<Integer> associatedLists;
		
		ClientStatus(String vote, Boolean favorite, Boolean observed, Integer[] associatedLists) {
			if (vote.equals("dig")) {
				this.vote = 1;
			} else if (vote.equals("bury")) {
				this.vote = -1;
			} else {
				this.vote = 0;
			}
			
			this.favorite = favorite;
			this.observed = observed;
			this.associatedLists = Arrays.asList(associatedLists);
		}

		public Integer getVote() {
			return vote;
		}

		public Boolean isFavorite() {
			return favorite;
		}

		public Boolean isObserved() {
			return observed;
		}

		public List<Integer> getAssociatedLists() {
			return associatedLists;
		}
	}
	
	private Integer id;
	
	private String title;
	private String description;
	
	@JsonDeserialize(using = TagsDeserializer.class)
	private List<String> tags;
	
	@JsonProperty
	private String category;

	@JsonProperty("category_name")
	private String categorySlug;
	
	@JsonIgnore
	private Author _author;
	
	@JsonIgnore
	private ClientStatus clientStatus;
	
	private URL url;
	
	@JsonProperty("source_url")
	private URL sourceUrl;
	
	@JsonDeserialize(using = DateDeserializer.class)
	private Date date;
	
	private Integer group;
	
	@JsonProperty("preview")
	private URL thumbnail;
	
	private String type;
	
	private String status;
	
	@JsonProperty("is_hot")
	private Boolean hot;
	
	@JsonProperty("vote_count")
	private Integer votesCount;
	
	@JsonProperty("comment_count")
	private Integer commentsCount;
	
	@JsonProperty("report_count")
	private Integer reportsCount;
	
	@JsonProperty("related_count")
	private Integer relatedLinksCount;
	
	@JsonProperty("plus18")
	private Boolean adultOnly;
	
	@JsonProperty("can_vote")
	private Boolean votable;
	
	@JsonProperty("has_own_content")
	private Boolean hasOwnContent;
	
	@JsonProperty("own_content")
	private String ownContent;
	
	@JsonProperty
	private String user_vote;
	
	@JsonProperty
	private Boolean user_favorite, user_observe;
	
	@JsonProperty
	private Integer[] user_lists;
	
	@JsonProperty
	@SuppressWarnings("unused")
	private String author, author_sex;
	
	@JsonProperty
	@SuppressWarnings("unused")
	private Integer author_group;
	
	@JsonProperty
	@SuppressWarnings("unused")
	private URL author_avatar, author_avatar_med, author_avatar_lo;
	
	public Integer getId() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public List<String> getTags() {
		return tags;
	}
	
	public String getCategory() {
		return category;
	}

	public String getCategorySlug() {
		return categorySlug;
	}

	public Author getAuthor() {
		if (_author == null) {
			_author = DeserializationUtils.readAuthor(this);
		}
		
		return _author;
	}
	
	public URL getUrl() {
		return url;
	}
	
	public URL getSourceUrl() {
		return sourceUrl;
	}
	
	public Date getDate() {
		return date;
	}
	
	public Integer getGroup() {
		return group;
	}
	
	public URL getThumbnail() {
		return thumbnail;
	}
	
	public Type getType() {
		return Type.valueOf(type.toUpperCase());
	}
	
	public Status getStatus() {
		return Status.valueOf(status.toUpperCase());
	}
	
	public Boolean isHot() {
		return hot;
	}
	
	public Integer getVotesCount() {
		return votesCount;
	}
	
	public Integer getCommentsCount() {
		return commentsCount;
	}
	
	public Integer getReportsCount() {
		return reportsCount;
	}
	
	public Integer getRelatedLinksCount() {
		return relatedLinksCount;
	}
	
	public Boolean isAdultOnly() {
		return adultOnly;
	}
	
	public Boolean isVotable() {
		return votable;
	}
	
	public Boolean hasOwnContent() {
		return hasOwnContent;
	}
	
	public String getOwnContent() {
		return ownContent;
	}
	
	public ClientStatus getClientStatus() {
		if (clientStatus == null) {
			clientStatus = new ClientStatus(user_vote, user_favorite, user_observe, user_lists);
		}
		
		return clientStatus;
	}
}
