package com.rebellion.weather_api_service.service;

import org.springframework.http.ResponseEntity;

public interface WeatherService {
    ResponseEntity<?> getWeatherByLocation(String location, String key);
    ResponseEntity<?> getWeatherByLocationStartDate(String location, String startdate, String key);
    ResponseEntity<?> getWeatherByLocationStartDateEndDate(String location, String startdate, String enddate, String key);
}
