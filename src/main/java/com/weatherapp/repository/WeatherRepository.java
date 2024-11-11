package com.weatherapp.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.weatherapp.model.WeatherData;

import java.time.LocalDate;

@Repository
public interface WeatherRepository extends JpaRepository<WeatherData, Long> {
    WeatherData findByPincodeAndDate(String pincode, LocalDate date);
}
