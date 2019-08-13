package com.tegra.flightsapi.domain.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.tegra.flightsapi.domain.vo.AirportVO;
import com.tegra.flightsapi.providers.HttpProvider;
import com.tegra.flightsapi.providers.JsonProvider;

@Service
public class AirportService implements IAirportService {

	@Value("${url.airports}")
	private String url_airports;

	public List<AirportVO> getAll() {

		return JsonProvider.convertJsonToList((String) HttpProvider.get(url_airports, String.class), AirportVO.class);
	}

}
