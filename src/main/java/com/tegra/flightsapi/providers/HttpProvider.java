package com.tegra.flightsapi.providers;

import org.springframework.web.client.RestTemplate;

public class HttpProvider {

	private static RestTemplate rest;

	static {
		rest = new RestTemplate();
	}

	public static Object get(String url, Class<?> responseType) {

		return rest.getForObject(url, responseType);
	}

}
