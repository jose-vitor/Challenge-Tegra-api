package com.tegra.flightsapi.application.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import com.tegra.flightsapi.domain.models.Flight;
import com.tegra.flightsapi.domain.services.IAirportService;
import com.tegra.flightsapi.domain.services.IFlightService;

@RestController
public class FlightController {

	@Autowired
	private IFlightService service;

	@Autowired
	private IAirportService airportService;

	@GetMapping
	public String findFlights() {

		return "Flights Service API is running";
	}

	@PostMapping("flights")
	public List<Flight> findByDate(@RequestBody Flight flight) {
		try {
			return service.findFlightsByDate(flight.getDeparture(), flight.getArrive(), flight.getDate());
		} catch (Exception e) {
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("airports")
	public String findAirports() {
		try {
			return airportService.getAll();
		} catch (Exception e) {
			throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
		}
	}

}
