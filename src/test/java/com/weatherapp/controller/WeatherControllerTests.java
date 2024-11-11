package com.weatherapp.controller;

import com.weatherapp.model.WeatherData;
import com.weatherapp.service.WeatherService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class WeatherControllerTests {

    @Mock
    private WeatherService weatherService;

    @InjectMocks
    private WeatherController weatherController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetWeather() {
        String pincode = "700016";
        String forDate = "2024-11-08";
        WeatherData mockWeatherData = new WeatherData(pincode, LocalDate.parse(forDate), 22.8436, 88.2293, 30.97, "clear sky",1024, 800);

        when(weatherService.getWeatherData(pincode, forDate)).thenReturn(mockWeatherData);

     
        WeatherData response = weatherController.getWeather(pincode, forDate);


        assertNotNull(response);
        assertEquals(30.97, response.getTemperature());
        assertEquals("clear sky", response.getWeatherDescription());
        assertEquals(pincode, response.getPincode());
        assertEquals(LocalDate.parse(forDate), response.getDate());
    }
}
