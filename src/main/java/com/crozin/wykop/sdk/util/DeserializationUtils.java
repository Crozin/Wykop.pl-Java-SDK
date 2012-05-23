package com.crozin.wykop.sdk.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import com.crozin.wykop.sdk.domain.Author;

public class DeserializationUtils {
	private static Map<String, String> authorFields = new HashMap<String, String>();
	
	static {
		authorFields.put("", "username");
		authorFields.put("_avatar", "defaultAvatar");
		authorFields.put("_avatar_lo", "lowQualityAvatar");
		authorFields.put("_avatar_med", "mediumQualityAvatar");
		authorFields.put("_group", "group");
		authorFields.put("_sex", "gender");
	}
	
	public static Author readAuthor(Object o) {
		return readAuthor(o, "author");
	}
	
	public static Author readAuthor(Object object, String fieldNamePrefix) {
		try {
			Author author = new Author();
			
			for (String fieldName : authorFields.keySet()) {
				Field objectField = object.getClass().getDeclaredField(fieldNamePrefix + fieldName);
				Field authorField = author.getClass().getDeclaredField(authorFields.get(fieldName));
				
				objectField.setAccessible(true);
				authorField.setAccessible(true);
				
				authorField.set(author, objectField.get(object));
			}
			
			return author;
		} catch (NoSuchFieldException nsfe) {
			throw new RuntimeException(nsfe);
		} catch (IllegalArgumentException iae) {
			throw new RuntimeException(iae);
		} catch (IllegalAccessException iae) {
			throw new RuntimeException(iae);
		}
	}
}
