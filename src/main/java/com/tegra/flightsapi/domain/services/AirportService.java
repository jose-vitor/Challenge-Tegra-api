package com.tegra.flightsapi.domain.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.tegra.flightsapi.providers.HttpProvider;

@Service
public class AirportService implements IAirportService {

	@Value("${url.airports}")
	private String url_airports;

	public String getAll() {

		return (String) HttpProvider.get(url_airports, String.class);
	}

}
