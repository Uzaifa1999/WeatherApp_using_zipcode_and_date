package com.weatherapp.model;


import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class WeatherData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String pincode;
    private LocalDate date;
    private double latitude;
    private double longitude;
    private double temperature;
    private String weatherDescription;
    private double pressure;
    private double humidity;
    
    
	public WeatherData(String pincode, LocalDate date, double latitude, double longitude, double temperature,
			String weatherDescription, double pressure, double humidity) {
		super();

		this.pincode = pincode;
		this.date = date;
		this.latitude = latitude;
		this.longitude = longitude;
		this.temperature = temperature;
		this.weatherDescription = weatherDescription;
		this.pressure = pressure;
		this.humidity = humidity;
	}



	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getPincode() {
		return pincode;
	}


	public void setPincode(String pincode) {
		this.pincode = pincode;
	}


	public LocalDate getDate() {
		return date;
	}


	public void setDate(LocalDate date) {
		this.date = date;
	}


	public double getLatitude() {
		return latitude;
	}


	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}


	public double getLongitude() {
		return longitude;
	}


	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}


	public double getTemperature() {
		return temperature;
	}


	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}


	public String getWeatherDescription() {
		return weatherDescription;
	}


	public void setWeatherDescription(String weatherDescription) {
		this.weatherDescription = weatherDescription;
	}



	public double getPressure() {
		return pressure;
	}



	public void setPressure(double pressure) {
		this.pressure = pressure;
	}



	public double getHumidity() {
		return humidity;
	}



	public void setHumidity(double humidity) {
		this.humidity = humidity;
	}
	

   
    
}

