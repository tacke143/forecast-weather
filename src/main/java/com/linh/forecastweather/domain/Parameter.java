package com.linh.forecastweather.domain;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@JsonInclude(JsonInclude.Include.ALWAYS)
@Document(collection = "parameter")
@Data
public class Parameter {

	private ObjectId _id;
	
	@JsonProperty("location")
	private String location;
	
	@JsonProperty("latitude")
	private String latitude;
	
	@JsonProperty("longitude")
	private String longitude;
	
	@JsonProperty("background")
	private String background;
	
	@JsonProperty("description")
	private String description;
	
}
