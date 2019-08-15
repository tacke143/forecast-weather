package com.linh.forecastweather.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@ConfigurationProperties("application.darksky")
@Configuration
@Data
public class DarkSkyConfigProperties {

	private String url;
	private String secretKey;
	private String excludeData;
	private Long housekeepForecastDayNum;

}
