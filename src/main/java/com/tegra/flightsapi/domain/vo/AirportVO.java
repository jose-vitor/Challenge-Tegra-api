package com.tegra.flightsapi.domain.vo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AirportVO {

	@JsonProperty("nome")
	private String name;

	@JsonProperty("aeroporto")
	private String code;

	@JsonProperty("cidade")
	private String city;
}
