package com.tegra.flightsapi.domain.services;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.tegra.flightsapi.domain.models.Flight;
import com.tegra.flightsapi.providers.HttpProvider;
import com.tegra.flightsapi.providers.JsonProvider;

@Service
public class FlightService implements IFlightService {

	@Value("${url.uberair}")
	private String URL_UBER_AIR;

	@Value("${url.99planes}")
	private String URL_99PLANES;

	public List<Flight> findFlightsByDate(String departure, String arrive, String date) {

		List<Flight> flights = this.findFlights().stream().filter(item -> item.getDeparture().equals(departure)
				&& item.getArrive().equals(arrive) && item.getDate().equals(date)).collect(Collectors.toList());

		return flights.stream().map(flight -> Flight.checkScales(flight, flights)).collect(Collectors.toList());
	}

	private List<Flight> findFlights() {

		return Stream.of(getByNinetyNinePlanes(), getByUberAir()).flatMap(x -> x.stream())
				.sorted((c1, c2) -> c1.getDepartureTimeFormat().compareTo(c2.getDepartureTimeFormat()))
				.collect(Collectors.toList());
	}

	private List<Flight> getByNinetyNinePlanes() {

		return JsonProvider.convertJsonToList((String) HttpProvider.get(URL_99PLANES, String.class), Flight.class)
				.stream().map(Flight.class::cast).map(item -> {
					item.setProvider("99Planes");
					return item;
				}).collect(Collectors.toList());
	}

	private List<Flight> getByUberAir() {

		String data = (String) HttpProvider.get(URL_UBER_AIR, String.class);

		return Stream.of(Pattern.compile("\r").split(data)).skip(1).map(line -> {

			Flight flight = new Flight();
			String[] columns = line.split(",");

			flight.setCode(columns[0]);
			flight.setDeparture(columns[1]);
			flight.setArrive(columns[2]);
			flight.setDate(columns[3]);
			flight.setDepartureTime(columns[4]);
			flight.setArriveTime(columns[5]);
			flight.setPrice(Double.parseDouble(columns[6]));
			flight.setProvider("UberAir");

			return flight;

		}).collect(Collectors.toList());
	}

}
