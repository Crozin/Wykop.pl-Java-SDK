package com.crozin.wykop.sdk.json;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class TagsDeserializer extends JsonDeserializer<List<String>> {
	@Override
	public List<String> deserialize(JsonParser jp, DeserializationContext ctxt) {
		try {
			return Arrays.asList(jp.getText().split(" "));
		}catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
