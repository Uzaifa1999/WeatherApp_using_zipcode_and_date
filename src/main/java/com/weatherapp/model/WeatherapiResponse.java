package com.weatherapp.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class WeatherapiResponse {
    
    private Main main;
    private List<Weather> weather;

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public static class Main {
        private double temp;
        
        @JsonProperty("feels_like")
        private double feelsLike;
        
        private double pressure;
        
        private double humidity;


        public double getTemp() {
            return temp;
        }

        public void setTemp(double temp) {
            this.temp = temp;
        }

        public double getFeelsLike() {
            return feelsLike;
        }

        public void setFeelsLike(double feelsLike) {
            this.feelsLike = feelsLike;
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

    public static class Weather {
        private String description;


        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
        
    }
}
