package com.linh.forecastweather.dto;

import org.bson.types.ObjectId;

import lombok.Data;

@Data
public class WeatherRecordDto {

	private String time;
	private String summary;
	private String icon;
	private Long sunriseTime;
	private Long sunsetTime;
	private Double moonPhase;
	private Double precipIntensity;
	private Double precipIntensityMax;
	private Long precipIntensityMaxTime;
	private Double precipProbability;
	private String precipType;
	private String temperatureHigh;
	private String temperatureHighTime;
	private String temperatureLow;
	private String temperatureLowTime;
	private String apparentTemperatureHigh;
	private String apparentTemperatureHighTime;
	private String apparentTemperatureLow;
	private String apparentTemperatureLowTime;
	private String dewPoint;
	private String humidity;
	private String pressure;
	private String windSpeed;
	private String windGust;
	private String windGustTime;
	private String windBearing;
	private String cloudCover;
	private String uvIndex;
	private String uvIndexTime;
	private String visibility;
	private String ozone;
	private String temperatureMin;
	private String temperatureMinTime;
	private String temperatureMax;
	private ObjectId parameterId;
	private String location;
	private String background;

}
