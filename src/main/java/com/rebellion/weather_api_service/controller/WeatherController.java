package com.rebellion.weather_api_service.controller;

import org.springframework.web.bind.annotation.RestController;

import com.rebellion.weather_api_service.entity.Weather;
import com.rebellion.weather_api_service.serviceImpl.WeatherServiceImpl;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/")
public class WeatherController {

    private WeatherServiceImpl weatherServiceImpl;

    public WeatherController(WeatherServiceImpl weatherServiceImpl) {
        this.weatherServiceImpl = weatherServiceImpl;
    }

    @GetMapping("")
	public String getHome() {
		return new String("Weather Service: Home Page");
	}

    @GetMapping("{location}")
    public Weather getLocationWeatherData(@PathVariable String location) {
        return weatherServiceImpl.getWeatherByLocation(location);
    }

    @GetMapping("{location}/{startdate}")
    public String getLocationWeatherDataForDate(@PathVariable String location, @PathVariable String startdate) {
        return new String("getLocationWeatherDataForDate(" + location + ", " + startdate + ")");
    }
    
    @GetMapping("{location}/{startdate}/{enddate}")
    public String getLocationWeatherDataForDatePeriod(@PathVariable String location, @PathVariable String startdate, @PathVariable String enddate) {
        return new String("getLocationWeatherDataForDatePeriod(" + location + ", " + startdate + ", " + enddate + ")");
    }
}
