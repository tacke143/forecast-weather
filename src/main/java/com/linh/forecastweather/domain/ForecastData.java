package com.linh.forecastweather.domain;

import lombok.Data;

@Data
public class ForecastData {
	private Double latitude;
	private Double longitude;
	private String timezone;
	private DailyData daily;
	private Integer offset;
}
