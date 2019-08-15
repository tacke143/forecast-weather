package com.linh.forecastweather.repo;
/*
* Copyright (c) Demo Application for Forecast Weather
* DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
*
* This is Repository for retrieving location parameters
*
*/
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.linh.forecastweather.domain.Parameter;

@Repository
public interface ParameterRepository extends MongoRepository<Parameter, ObjectId>{

}
