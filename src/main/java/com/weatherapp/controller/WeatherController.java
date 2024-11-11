package com.weatherapp.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.weatherapp.model.WeatherData;
import com.weatherapp.service.WeatherService;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping
    public WeatherData getWeather(@RequestParam String pincode, @RequestParam String for_date) {
        return weatherService.getWeatherData(pincode, for_date);
    }
}
