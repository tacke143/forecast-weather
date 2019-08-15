package com.linh.forecastweather.service;
/*
* Copyright (c) Demo Application for Forecast Weather
* DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
*
* This is Service for retrieving weather data from database (source: DarkSky)
*
*/
import java.time.LocalDate;
import java.util.List;

import com.linh.forecastweather.dto.WeatherRecordDto;

public interface ForecastService {

	/**
	 * <p>Service to query weather data
	 * </p>
	 * @param today LocalDate
	 * @return List of weather data for all location
	 * @since 1.0
	 */
	List<WeatherRecordDto> getWeatherData(LocalDate today);
}
