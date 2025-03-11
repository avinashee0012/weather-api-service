package com.rebellion.weather_api_service.controller;

import org.springframework.web.bind.annotation.RestController;

import com.rebellion.weather_api_service.entity.Weather;
import com.rebellion.weather_api_service.serviceImpl.WeatherServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api")
public class WeatherController {

    private WeatherServiceImpl weatherServiceImpl;

    public WeatherController(WeatherServiceImpl weatherServiceImpl) {
        this.weatherServiceImpl = weatherServiceImpl;
    }

    @GetMapping("")
	public String getHome() {
		return new String("Weather Service: API Home Page");
	}

    @GetMapping("weather/{location}")
    public Weather getLocationWeatherData(@PathVariable String location) {
        return weatherServiceImpl.getWeatherByLocation(location);
    }

    @GetMapping("weather/{location}/{startdate}")
    public Weather getLocationWeatherDataForDate(@PathVariable String location, @PathVariable String startdate) {
        return weatherServiceImpl.getWeatherByLocationStartDate(location, startdate);
    }
    
    @GetMapping("weather/{location}/{startdate}/{enddate}")
    public Weather getLocationWeatherDataForDatePeriod(@PathVariable String location, @PathVariable String startdate, @PathVariable String enddate) {
        return weatherServiceImpl.getWeatherByLocationStartDateEndDate(location, startdate, enddate);
    }
}
