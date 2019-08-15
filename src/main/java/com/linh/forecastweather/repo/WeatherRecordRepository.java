package com.linh.forecastweather.repo;
/*
* Copyright (c) Demo Application for Forecast Weather
* DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
*
* This is Repository for retrieving weather data from database (source: DarkSky)
*
*/
import java.time.LocalDate;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.linh.forecastweather.domain.WeatherRecord;

@Repository
public interface WeatherRecordRepository extends MongoRepository<WeatherRecord, ObjectId>{

	WeatherRecord findByParameterIdAndTime(ObjectId parameterId, LocalDate date);
	
	void deleteByParameterIdAndTimeAfter(ObjectId parameterId, LocalDate date);
}
