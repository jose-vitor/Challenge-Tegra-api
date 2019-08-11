package com.tegra.flightsapi.domain.models;

import java.beans.Transient;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;

import org.codehaus.jackson.annotate.JsonProperty;

public class Flight {

	@JsonProperty("voo")
	private String code;

	@JsonProperty("data_saida")
	private String date;

	@JsonProperty("origem")
	private String departure;

	@JsonProperty("destino")
	private String arrive;

	@JsonProperty("saida")
	private String departureTime;

	@JsonProperty("chegada")
	private String arriveTime;

	@JsonProperty("valor")
	private Double price;

	private String provider;

	private List<Scale> scales;

	public static Flight checkScales(Flight flight, List<Flight> flights) {
		if (Scale.hasScale(flight))
			flight.setScales(Scale.getScales(flights, flight));
		return flight;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDeparture() {
		return departure;
	}

	public void setDeparture(String departure) {
		this.departure = departure;
	}

	public String getArrive() {
		return arrive;
	}

	public void setArrive(String arrive) {
		this.arrive = arrive;
	}

	@Transient
	public Date getDepartureTimeFormat() {
		try {
			return new SimpleDateFormat("hh:mm").parse(this.departureTime);
		} catch (ParseException e) {
			return null;
		}
	}

	public String getDepartureTime() {

		return this.departureTime;
	}

	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}

	@Transient
	public Date getArriveTimeFormat() {
		try {
			return new SimpleDateFormat("hh:mm").parse(this.arriveTime);
		} catch (ParseException e) {
			return null;
		}
	}

	public String getArriveTime() {
		return this.arriveTime;
	}

	public void setArriveTime(String arriveTime) {
		this.arriveTime = arriveTime;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getProvider() {
		return provider;
	}

	public void setProvider(String provider) {
		this.provider = provider;
	}

	public List<Scale> getScales() {
		return scales;
	}

	public void setScales(List<Scale> scales) {
		this.scales = scales;
	}

}
