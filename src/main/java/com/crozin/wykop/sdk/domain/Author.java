package com.crozin.wykop.sdk.domain;

import java.net.URL;

import com.crozin.wykop.sdk.AvatarQuality;
import com.crozin.wykop.sdk.domain.Profile.Group;

public class Author {
	public enum Gender {
		MALE, FEMALE;
	}
	
	private String username;
	
	private Integer group;
	
	private String gender;
	
	private URL defaultAvatar;
	
	private URL lowQualityAvatar;
	
	private URL mediumQualityAvatar;
	
	private URL highQualityAvatar;
	
	public String getUsername() {
		return username;
	}

	public Group getGroup() {
		return Group.valueOf(group);
	}
	
	public Gender getGender() {
		return Gender.valueOf(gender.toUpperCase());
	}

	public URL getAvatar() {
		return getAvatar(AvatarQuality.DEFAULT);
	}
	
	public URL getAvatar(AvatarQuality avatarQuality) {
		switch (avatarQuality) {
		case LOW:
			return lowQualityAvatar;
		case MEDIUM:
			return mediumQualityAvatar;
		case HIGH:
			return highQualityAvatar;
		default:
			return defaultAvatar;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		
		if (obj == null) {
			return false;
		}
		
		if (getClass() != obj.getClass()) {
			return false;
		}
		
		Author other = (Author) obj;
		if (username == null) {
			if (other.username != null) {
				return false;
			}
		} else if (!username.equals(other.username)) {
			return false;
		}
		
		return true;
	}

	@Override
	public String toString() {
		return getUsername();
	}
}
