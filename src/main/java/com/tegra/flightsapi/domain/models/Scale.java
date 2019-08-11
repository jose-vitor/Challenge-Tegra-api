package com.tegra.flightsapi.domain.models;

import java.util.List;
import java.util.stream.Collectors;

public class Scale {

	private String code;

	private String date;

	private String departure, arrive;

	private String departureTime, arriveTime;

	private Double price;

	private String provider;

	public Scale() {
	}

	public Scale(Flight flight) {
		this.code = flight.getCode();
		this.date = flight.getDate();
		this.departure = flight.getDeparture();
		this.arrive = flight.getArrive();
		this.departureTime = flight.getDepartureTime();
		this.arriveTime = flight.getArriveTime();
		this.price = flight.getPrice();
		this.provider = flight.getProvider();
	}

	public static Boolean hasScale(Flight flight) {
		try {
			long diff = flight.getArriveTimeFormat().getTime() - flight.getDepartureTimeFormat().getTime();
			long diffHours = diff / (60 * 60 * 1000) % 24;
			return (diffHours < 12);
		} catch (Exception e) {
			return false;
		}
	}

	public static List<Scale> getScales(List<Flight> flights, Flight flight) {

		return flights.stream()
				.filter(scale -> scale.getDate().equals(flight.getDate()) && Scale.hasScale(scale)
						&& ((scale.getDeparture().equals(flight.getDeparture())
								&& !scale.getArrive().equals(flight.getArrive()))
								|| (!scale.getDeparture().equals(flight.getDeparture())
										&& scale.getArrive().equals(flight.getArrive()))))
				.map(Scale::new).collect(Collectors.toList());
	}

	public String getCode() {
		return code;
	}

	public String getDate() {
		return date;
	}

	public String getDeparture() {
		return departure;
	}

	public String getArrive() {
		return arrive;
	}

	public String getDepartureTime() {
		return departureTime;
	}

	public String getArriveTime() {
		return arriveTime;
	}

	public Double getPrice() {
		return price;
	}

	public String getProvider() {
		return provider;
	}

}
