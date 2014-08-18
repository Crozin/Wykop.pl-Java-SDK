package com.crozin.wykop.sdk.util;

import static org.junit.Assert.assertEquals;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;

import com.crozin.wykop.sdk.AvatarQuality;
import com.crozin.wykop.sdk.domain.Author;
import com.crozin.wykop.sdk.domain.Profile;

public class TestDeserializationUtils {
	@SuppressWarnings("unused")
	private class SampleImage {
		private String author, author_sex;
		private URL author_avatar, author_avatar_lo, author_avatar_med, author_avatar_big;
		private Integer author_group; 

		public SampleImage(String author, String author_sex, URL author_avatar,
				URL author_avatar_lo, URL author_avatar_med,
				URL author_avatar_big, Integer author_group) {
			this.author = author;
			this.author_sex = author_sex;
			this.author_avatar = author_avatar;
			this.author_avatar_lo = author_avatar_lo;
			this.author_avatar_med = author_avatar_med;
			this.author_avatar_big = author_avatar_big;
			this.author_group = author_group;
		}
	}
	
	@Test
	public void extractAuthor() throws MalformedURLException {
		URL avatar = new URL("file:///tmp/avatar");
		URL avatarLow = new URL("file:///tmp/avatar-low");
		URL avatarMid = new URL("file:///tmp/avatar-mid");
		URL avatarBig = new URL("file:///tmp/avatar-big");
		SampleImage si = new SampleImage("Crozin", "male", avatar, avatarLow, avatarMid, avatarBig, new Integer(5));
		Author author = DeserializationUtils.readAuthor(si);
		
		assertEquals("Crozin", author.getUsername());
		assertEquals(Author.Gender.MALE, author.getGender());
		assertEquals(avatar, author.getAvatar());
		assertEquals(avatar, author.getAvatar(AvatarQuality.DEFAULT));
		assertEquals(avatarLow, author.getAvatar(AvatarQuality.LOW));
		assertEquals(avatarMid, author.getAvatar(AvatarQuality.MEDIUM));
		assertEquals(avatarBig, author.getAvatar(AvatarQuality.HIGH));
		assertEquals(Profile.Group.ADMINISTRATOR, author.getGroup());
	}
}