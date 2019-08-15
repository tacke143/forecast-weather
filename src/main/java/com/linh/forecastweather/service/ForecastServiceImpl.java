package com.linh.forecastweather.service;
/*
* Copyright (c) Demo Application for Forecast Weather
* DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
*
* This is Service for retrieving weather data from database (source: DarkSky)
*
*/
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.linh.forecastweather.config.DarkSkyConfigProperties;
import com.linh.forecastweather.domain.ForecastData;
import com.linh.forecastweather.domain.Parameter;
import com.linh.forecastweather.domain.WeatherRecord;
import com.linh.forecastweather.dto.WeatherRecordDto;
import com.linh.forecastweather.repo.ParameterRepository;
import com.linh.forecastweather.repo.WeatherRecordRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class ForecastServiceImpl implements ForecastService {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private DarkSkyConfigProperties darkSkyConfigProperties;

	@Autowired
	private ParameterRepository parameterRepository;

	@Autowired
	private WeatherRecordRepository weatherRecordRepository;

	@Override
	public List<WeatherRecordDto> getWeatherData(LocalDate today) {
		
		log.debug(">>>START-Service to query weather data for: {}", today);
		List<WeatherRecordDto> todayData = new ArrayList<>();
		// Load all locations with their parameters
		List<Parameter> parameters = parameterRepository.findAll();
		
		// For each location, weather date for today date should be collected
		parameters.forEach(p -> {
			WeatherRecord todayRec = weatherRecordRepository.findByParameterIdAndTime(p.get_id(), today);
			
			// If weather data for today is not existing, a REST API call to Darksky should be triggered
			if (Objects.isNull(todayRec)) {
				log.debug(">>>Retrieving data from DarkSky in {} with Latitude: {} , Longitude: {}", p.getLocation(), p.getLatitude(), p.getLongitude());
				ResponseEntity<ForecastData> response = restTemplate.exchange(
						darkSkyConfigProperties.getUrl() + darkSkyConfigProperties.getSecretKey() + "/"
								+ p.getLatitude() + "," + p.getLongitude() + "?exclude="
								+ darkSkyConfigProperties.getExcludeData(),
						HttpMethod.GET, null, new ParameterizedTypeReference<ForecastData>() {});
				if (Objects.isNull(response.getBody()) || Objects.isNull(response.getBody().getDaily())) {
					log.error(">>>No data for location: {}, with Longitude: {} and Latitude: {}", p.getLocation(),
							p.getLongitude(), p.getLatitude());
				} else {
					//Housekeep forecast records that already 3 days old
					todayRec = getNewestweaterData(today, p, response);
				}
			}

			WeatherRecordDto outDtoRec = weatherRecordEntityToWeatherRecordDto.apply(todayRec);
			// Set extra information for every particular location
			outDtoRec.setLocation(p.getLocation());
			outDtoRec.setBackground(p.getBackground());
			todayData.add(outDtoRec);
		});
		log.debug(">>>END-Service to query weather data for: {}", today);
		return todayData;
	}

	private WeatherRecord getNewestweaterData(LocalDate today, Parameter p, ResponseEntity<ForecastData> response) {
		WeatherRecord todayRec;
		LocalDate lastDateForHousekeep = today.plusDays(darkSkyConfigProperties.getHousekeepForecastDayNum());
		List<WeatherRecord> newData = response.getBody().getDaily().getData().stream()
				.filter(d -> ((d.getTime().isEqual(today) || d.getTime().isAfter(today))
						&& d.getTime().isBefore(lastDateForHousekeep))).collect(Collectors.toList());
		newData.forEach(d -> {
			d.setParameterId(p.get_id());
		});
		
		// Clean up data before persist data onto database
		weatherRecordRepository.deleteByParameterIdAndTimeAfter(p.get_id(), today);
		// Persist newest weather data onto database 
		weatherRecordRepository.saveAll(newData);
		todayRec = newData.stream().filter(d -> d.getTime().isEqual(today)).findFirst().get();
		return todayRec;
	}

	private Function<WeatherRecord, WeatherRecordDto> weatherRecordEntityToWeatherRecordDto = wr -> {
		if(Objects.isNull(wr)) {
			return null;
		}
		WeatherRecordDto dto = new WeatherRecordDto();
		dto.setTime(wr.getTime().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)));
		dto.setSummary(wr.getSummary());
		dto.setIcon(wr.getIcon());
		dto.setSunriseTime(wr.getSunriseTime());
		dto.setSunsetTime(wr.getSunsetTime());
		dto.setMoonPhase(wr.getMoonPhase());
		dto.setPrecipIntensity(wr.getPrecipIntensity());
		dto.setPrecipIntensityMax(wr.getPrecipIntensityMax());
		dto.setPrecipIntensityMaxTime(wr.getPrecipIntensityMaxTime());
		dto.setPrecipProbability(wr.getPrecipProbability());
		dto.setPrecipType(wr.getPrecipType());
		dto.setTemperatureHigh(wr.getTemperatureHigh());
		dto.setTemperatureHighTime(wr.getTemperatureHighTime());
		dto.setTemperatureLow(wr.getTemperatureLow());
		dto.setTemperatureLowTime(wr.getTemperatureLowTime());
		dto.setApparentTemperatureHigh(wr.getApparentTemperatureHigh());
		dto.setApparentTemperatureHighTime(wr.getApparentTemperatureHighTime());
		dto.setApparentTemperatureLow(wr.getApparentTemperatureLow());
		dto.setApparentTemperatureLowTime(wr.getApparentTemperatureLowTime());
		dto.setDewPoint(wr.getDewPoint());
		dto.setHumidity(wr.getHumidity());
		dto.setPressure(wr.getPressure());
		dto.setWindSpeed(wr.getWindSpeed());
		dto.setWindGust(wr.getWindGust());
		dto.setWindGustTime(wr.getWindGustTime());
		dto.setWindBearing(wr.getWindBearing());
		dto.setCloudCover(wr.getCloudCover());
		dto.setUvIndex(wr.getUvIndex());
		dto.setUvIndexTime(wr.getUvIndexTime());
		dto.setVisibility(wr.getVisibility());
		dto.setOzone(wr.getOzone());
		dto.setTemperatureMin(wr.getTemperatureMin());
		dto.setTemperatureMinTime(wr.getTemperatureMinTime());
		dto.setTemperatureMax(wr.getTemperatureMax());
		dto.setParameterId(wr.getParameterId());
		return dto;
	};

}
