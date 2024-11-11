package com.weatherapp.service;


import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import com.weatherapp.model.GeolocationResponse;
import com.weatherapp.model.WeatherData;
import com.weatherapp.model.WeatherapiResponse;
import com.weatherapp.repository.WeatherRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;
import java.util.List;

public class WeatherServiceTests {

    @Mock
    private WeatherRepository weatherRepository;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private WeatherService weatherService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetWeatherData_WhenDataExistsInRepository() {
        String pincode = "700016";
        String forDate = "2024-11-08";
        LocalDate date = LocalDate.parse(forDate);
        WeatherData mockWeatherData = new WeatherData(pincode, date, 22.8436, 88.2293, 30.97, "clear sky", 1024, 800);

        when(weatherRepository.findByPincodeAndDate(pincode, date)).thenReturn(mockWeatherData);

        WeatherData result = weatherService.getWeatherData(pincode, forDate);
        assertNotNull(result);
        assertEquals(30.97, result.getTemperature());
        verify(weatherRepository, never()).save(any());
    }

    @Test
    public void testGetWeatherData_WhenDataNotInRepository() {
        String pincode = "700016";
        String forDate = "2024-11-08";
        LocalDate date = LocalDate.parse(forDate);

        when(weatherRepository.findByPincodeAndDate(pincode, date)).thenReturn(null);

        GeolocationResponse mockGeoResponse = new GeolocationResponse();
        mockGeoResponse.setLat(22.8436);
        mockGeoResponse.setLon(88.2293);
        when(restTemplate.getForObject(anyString(), eq(GeolocationResponse.class))).thenReturn(mockGeoResponse);

        WeatherapiResponse mockWeatherResponse = new WeatherapiResponse();
        WeatherapiResponse.Main main = new WeatherapiResponse.Main();
        main.setTemp(30.97);
        mockWeatherResponse.setMain(main);
        WeatherapiResponse.Weather weather = new WeatherapiResponse.Weather();
        weather.setDescription("clear sky");
        mockWeatherResponse.setWeather(List.of(weather));
        when(restTemplate.getForObject(anyString(), eq(WeatherapiResponse.class))).thenReturn(mockWeatherResponse);

        WeatherData result = weatherService.getWeatherData(pincode, forDate);
        assertNotNull(result);
        assertEquals(30.97, result.getTemperature());
        assertEquals("clear sky", result.getWeatherDescription());
        verify(weatherRepository).save(any(WeatherData.class));
    }
}
