package com.crozin.wykop.sdk.json;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class DateDeserializer extends JsonDeserializer<Date> {
	private final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
	
	@Override
	public Date deserialize(JsonParser jp, DeserializationContext ctxt) {
		try {
			return format.parse(jp.getText());
		} catch (ParseException pe) {
			throw new RuntimeException(pe);
		} catch (IOException ioe) {
			throw new RuntimeException(ioe);
		}
	}
}
