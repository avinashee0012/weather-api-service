package com.rebellion.weather_api_service.service;

import org.springframework.http.ResponseEntity;

public interface WeatherService {
    ResponseEntity<?> getWeatherByLocation(String location);
    ResponseEntity<?> getWeatherByLocationStartDate(String location, String startdate);
    ResponseEntity<?> getWeatherByLocationStartDateEndDate(String location, String startdate, String enddate);
}
