package com.weatherapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.weatherapp.model.GeolocationResponse;
import com.weatherapp.model.WeatherData;
import com.weatherapp.model.WeatherapiResponse;
import com.weatherapp.repository.WeatherRepository;

import java.time.LocalDate;

@Service
public class WeatherService {

    @Autowired
    private WeatherRepository weatherRepository;

    @Autowired
    private RestTemplate restTemplate;

    private static final String OPENWEATHER_API_KEY = "Enter Api key";
    private static final String GEOCODING_API_URL = "http://api.openweathermap.org/geo/1.0/zip?zip=%s,IN&appid=" + OPENWEATHER_API_KEY;
    private static final String WEATHER_API_URL = "https://api.openweathermap.org/data/2.5/weather?lat=%s&lon=%s&appid="+OPENWEATHER_API_KEY;
  

    public WeatherData getWeatherData(String pincode, String forDate) {
        LocalDate date = LocalDate.parse(forDate);
        WeatherData existingData = weatherRepository.findByPincodeAndDate(pincode, date);

        if (existingData != null) {
            return existingData;
        }

        String geoUrl = String.format(GEOCODING_API_URL, pincode);
        GeolocationResponse geoResponse = restTemplate.getForObject(geoUrl, GeolocationResponse.class);
        double lat = geoResponse.getLat();
        double lon = geoResponse.getLon();

        String weatherUrl = String.format(WEATHER_API_URL, lat, lon);
        WeatherapiResponse weatherResponse = restTemplate.getForObject(weatherUrl, WeatherapiResponse.class);

        if (weatherResponse == null || weatherResponse.getMain() == null || weatherResponse.getWeather() == null || weatherResponse.getWeather().isEmpty()) {
            throw new RuntimeException("Weather API response is null or missing required data");
        }

        double temperature = weatherResponse.getMain().getTemp();
        String weatherDescription = weatherResponse.getWeather().get(0).getDescription();
        double pressure = weatherResponse.getMain().getPressure();
        double humidity = weatherResponse.getMain().getHumidity();
        

        WeatherData newWeatherData = new WeatherData(pincode, date, lat, lon, temperature, weatherDescription,pressure,humidity);
        weatherRepository.save(newWeatherData);


        return newWeatherData;
    }

   
}
