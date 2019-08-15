package com.linh.forecastweather.utils;

/*
* Copyright (c) Demo Application for Forecast Weather
* DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
*
* This is Utils class to deserialization a localdate from long Epoch time
*
*/
import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class LocalDateFromEpochDeserializer extends StdDeserializer<LocalDate> {
	private static final long serialVersionUID = 1L;

	protected LocalDateFromEpochDeserializer() {
		super(LocalDate.class);
	}

	@Override
	public LocalDate deserialize(JsonParser jp, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		return Instant.ofEpochSecond(jp.readValueAs(Long.class)).atZone(ZoneId.systemDefault()).toLocalDate();
	}
}
