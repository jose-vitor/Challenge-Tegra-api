package com.tegra.flightsapi.providers;

import java.util.Collections;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;

public class JsonProvider {

	private static ObjectMapper mapper;

	static {
		mapper = new ObjectMapper();
	}

	public static <T> List<T> convertJsonToList(String json, Class<?> elementClass) {
		try {
			return mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(List.class, elementClass));
		} catch (Exception e) {
			return Collections.emptyList();
		}
	}

}
