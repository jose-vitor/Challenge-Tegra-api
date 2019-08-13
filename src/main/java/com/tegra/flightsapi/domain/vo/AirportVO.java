package com.tegra.flightsapi.domain.vo;

import org.codehaus.jackson.annotate.JsonProperty;

public class AirportVO {

	@JsonProperty("nome")
	private String name;

	@JsonProperty("aeroporto")
	private String code;

	@JsonProperty("cidade")
	private String city;

	public String getName() {
		return name;
	}

	public String getCode() {
		return code;
	}

	public String getCity() {
		return city;
	}
}
