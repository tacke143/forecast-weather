package com.linh.forecastweather.domain;

import java.util.List;

import lombok.Data;

@Data
public class DailyData {

	private String summary;
	
	private String icon;
	
	private List<WeatherRecord> data;
}
