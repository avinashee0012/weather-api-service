package com.rebellion.weather_api_service.service;

import com.rebellion.weather_api_service.entity.Weather;

public interface WeatherService {
    Weather getWeatherByLocation(String location);
    Weather getWeatherByLocationStartDate(String location, String startdate);
    Weather getWeatherByLocationStartDateEndDate(String location, String startdate, String enddate);
}
