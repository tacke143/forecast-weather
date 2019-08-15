package com.linh.forecastweather.controller;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.linh.forecastweather.dto.WeatherRecordDto;
import com.linh.forecastweather.service.ForecastService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
public class ForecastWeatherControllerTest {

	private MockMvc mockMvc;

	@InjectMocks
    private ForecastController forecastController;
	
	@Mock
	private ForecastService forecastService;

	@Before
	public void setUp() {
		 // Process mock annotations
        MockitoAnnotations.initMocks(this);
 
        // Setup Spring test in standalone mode
        this.mockMvc = MockMvcBuilders.standaloneSetup(forecastController).build();
	}
	
	@Test
	public void contextLoads() {
		Assertions.assertThat(forecastService).isNotNull();
	}

	@Test
	public void getWeatherData_ShouldAddWeatherDataToModelAndRenderWeatherDataView() throws Exception {
		LocalDate today = LocalDate.now();

		when(forecastService.getWeatherData(today)).thenReturn(prepareData(today.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM))));

		mockMvc.perform(get("/")).andExpect(status().isOk()).andExpect(view().name("home"))
				.andExpect(forwardedUrl("home")).andExpect(model().attribute("data", hasSize(6)))
				.andExpect(model().attribute("data", hasItem(allOf(hasProperty("location", is("Campbell, CA")),
						hasProperty("temperatureLow", is("60")), hasProperty("temperatureHigh", is("80"))))));

		verify(forecastService, times(1)).getWeatherData(today);
		verifyNoMoreInteractions(forecastService);
	}

	private List<WeatherRecordDto> prepareData(String today) {
		List<WeatherRecordDto> todayData = new ArrayList<>();
		WeatherRecordDto dto1 = new WeatherRecordDto();
		dto1.setTime(today);
		dto1.setLocation("Campbell, CA");
		dto1.setTemperatureLow("60");
		dto1.setTemperatureHigh("80");
		todayData.add(dto1);
		dto1 = new WeatherRecordDto();
		dto1.setTime(today);
		dto1.setLocation("Omaha, NE");
		dto1.setTemperatureLow("50");
		dto1.setTemperatureHigh("40");
		todayData.add(dto1);
		dto1 = new WeatherRecordDto();
		dto1.setTime(today);
		dto1.setLocation("Austin, TX");
		dto1.setTemperatureLow("50");
		dto1.setTemperatureHigh("89");
		todayData.add(dto1);
		dto1 = new WeatherRecordDto();
		dto1.setTime(today);
		dto1.setLocation("Niseko, Japan");
		dto1.setTemperatureLow("50");
		dto1.setTemperatureHigh("67");
		todayData.add(dto1);
		dto1 = new WeatherRecordDto();
		dto1.setTime(today);
		dto1.setLocation("Nara, Japan");
		dto1.setTemperatureLow("50");
		dto1.setTemperatureHigh("45");
		todayData.add(dto1);
		dto1 = new WeatherRecordDto();
		dto1.setTime(today);
		dto1.setLocation("Jakarta, Indonesia");
		dto1.setTemperatureLow("50");
		dto1.setTemperatureHigh("90");
		todayData.add(dto1);
		return todayData;
	}
}
