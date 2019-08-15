package com.linh.forecastweather.controller;
/*
* Copyright (c) Demo Application for Forecast Weather
* DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
*
* This is Controller to handle all weather data request
*
*/
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.linh.forecastweather.dto.WeatherRecordDto;
import com.linh.forecastweather.service.ForecastService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class ForecastController {

	@Autowired
	private ForecastService forecastService;
	
	/**
	 * <p>This is method to get weather data for current day
	 * <a href="/">Forecast Weather</a>
	 * </p>
	 * @param model Model
	 * @return home page
	 * @since 1.0
	 */
    @GetMapping("/")
    public String getWeatherData(Model model) {
    	log.debug(">>>Request to get weather data for one day.");
    	List<WeatherRecordDto> data = forecastService.getWeatherData(LocalDate.now());
        model.addAttribute("data", data);
        return "home";
    }
}
