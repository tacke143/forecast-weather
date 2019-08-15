package com.linh.forecastweather.domain;

import java.time.LocalDate;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.linh.forecastweather.utils.LocalDateFromEpochDeserializer;

import lombok.Data;

@JsonInclude(JsonInclude.Include.ALWAYS)
@Document(collection = "weatherRecord")
@Data
public class WeatherRecord {

	private ObjectId _id;

	@JsonProperty("time")
	@JsonDeserialize(using = LocalDateFromEpochDeserializer.class)
	private LocalDate time;
	@JsonProperty("summary")
	private String summary;
	@JsonProperty("icon")
	private String icon;
	@JsonProperty("sunriseTime")
	private Long sunriseTime;
	@JsonProperty("sunsetTime")
	private Long sunsetTime;
	@JsonProperty("moonPhase")
	private Double moonPhase;
	@JsonProperty("precipIntensity")
	private Double precipIntensity;
	@JsonProperty("precipIntensityMax")
	private Double precipIntensityMax;
	@JsonProperty("precipIntensityMaxTime")
	private Long precipIntensityMaxTime;
	@JsonProperty("precipProbability")
	private Double precipProbability;
	@JsonProperty("precipType")
	private String precipType;
	@JsonProperty("temperatureHigh")
	private String temperatureHigh;
	@JsonProperty("temperatureHighTime")
	private String temperatureHighTime;
	@JsonProperty("temperatureLow")
	private String temperatureLow;
	@JsonProperty("temperatureLowTime")
	private String temperatureLowTime;
	@JsonProperty("apparentTemperatureHigh")
	private String apparentTemperatureHigh;
	@JsonProperty("apparentTemperatureHighTime")
	private String apparentTemperatureHighTime;
	@JsonProperty("apparentTemperatureLow")
	private String apparentTemperatureLow;
	@JsonProperty("apparentTemperatureLowTime")
	private String apparentTemperatureLowTime;
	@JsonProperty("dewPoint")
	private String dewPoint;
	@JsonProperty("humidity")
	private String humidity;
	@JsonProperty("pressure")
	private String pressure;
	@JsonProperty("windSpeed")
	private String windSpeed;
	@JsonProperty("windGust")
	private String windGust;
	@JsonProperty("windGustTime")
	private String windGustTime;
	@JsonProperty("windBearing")
	private String windBearing;
	@JsonProperty("cloudCover")
	private String cloudCover;
	@JsonProperty("uvIndex")
	private String uvIndex;
	@JsonProperty("uvIndexTime")
	private String uvIndexTime;
	@JsonProperty("visibility")
	private String visibility;
	@JsonProperty("ozone")
	private String ozone;
	@JsonProperty("temperatureMin")
	private String temperatureMin;
	@JsonProperty("temperatureMinTime")
	private String temperatureMinTime;
	@JsonProperty("temperatureMax")
	private String temperatureMax;
	
	@JsonProperty("parameter_id")
	private ObjectId parameterId;

}
