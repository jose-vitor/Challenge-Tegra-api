package com.tegra.flightsapi.domain.services;

import java.util.List;

import com.tegra.flightsapi.domain.models.Flight;

public interface IFlightService {

	public List<Flight> findFlightsByDate(String departure, String arrive, String date);
}
