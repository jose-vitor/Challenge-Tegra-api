package com.tegra.flightsapi.domain.services;

import java.util.List;

import com.tegra.flightsapi.domain.vo.AirportVO;

public interface IAirportService {

	public List<AirportVO> getAll();
}
