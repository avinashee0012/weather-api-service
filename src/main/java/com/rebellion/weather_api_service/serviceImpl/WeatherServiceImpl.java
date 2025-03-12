package com.rebellion.weather_api_service.serviceImpl;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rebellion.weather_api_service.entity.Weather;
import com.rebellion.weather_api_service.service.WeatherService;

// TODO: Handle response status codes
// https://www.visualcrossing.com/resources/documentation/weather-api/timeline-weather-api/#response-codes

@Service
public class WeatherServiceImpl implements WeatherService {

    private final ObjectMapper mapper;
    private RedisServiceImpl redisServiceImpl;
    
    private final String key = "your_visualcrossing_key";

    public WeatherServiceImpl(ObjectMapper mapper, RedisServiceImpl redisServiceImpl) {
        this.mapper = mapper;
        this.redisServiceImpl = redisServiceImpl;
    }

    @Override 
    public Weather getWeatherByLocation(String location) {
        if(redisServiceImpl.get("weather_of_" + location, Weather.class) != null) {
            return redisServiceImpl.get("weather_of_" + location, Weather.class);
        } else {
            String baseUrl = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline";
            try {
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(String.format("%s/%s?key=%s&contentType=json", baseUrl, location, key)))
                        .method("GET", HttpRequest.BodyPublishers.noBody()).build();
                HttpResponse<String> response = HttpClient.newHttpClient()
                        .send(request, HttpResponse.BodyHandlers.ofString());
                Weather weather = mapper.readValue(response.body(), Weather.class);
                if(weather != null) {
                    redisServiceImpl.set("weather_of_" + location, weather, 300L);
                }
                return weather;
            } catch (Exception e) {
                System.out.println("Exception: visualcrossing API --> getWeatherByLocation(String location)");
            }
        }
        return new Weather();
    }

    @Override
    public Weather getWeatherByLocationStartDate(String location, String startdate) {
        if(redisServiceImpl.get("weather_of_" + location + "_" + startdate, Weather.class) != null) {
            return redisServiceImpl.get("weather_of_" + location + "_" + startdate, Weather.class);
        } else {
            String baseUrl = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline";
            try {
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(String.format("%s/%s/%s?key=%s&contentType=json", baseUrl, location, startdate, key)))
                        .method("GET", HttpRequest.BodyPublishers.noBody()).build();
                HttpResponse<String> response = HttpClient.newHttpClient()
                        .send(request, HttpResponse.BodyHandlers.ofString());
                Weather weather = mapper.readValue(response.body(), Weather.class);
                if(weather != null) {
                    redisServiceImpl.set("weather_of_" + location + "_" + startdate, weather, 300L);
                }
                return weather;
            } catch (Exception e) {
                System.out.println("Exception: visualcrossing API --> getWeatherByLocationStartDate(String location, String startdate)");
            }
        }
        return new Weather();
    }

    @Override
    public Weather getWeatherByLocationStartDateEndDate(String location, String startdate, String enddate) {
        if(redisServiceImpl.get("weather_of_" + location + "_" + startdate + "_" + enddate, Weather.class) != null) {
            return redisServiceImpl.get("weather_of_" + location + "_" + startdate + "_" + enddate, Weather.class);
        } else {
            String baseUrl = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline";
            try {
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(String.format("%s/%s/%s/%s?key=%s&contentType=json", baseUrl, location, startdate, enddate, key)))
                        .method("GET", HttpRequest.BodyPublishers.noBody()).build();
                HttpResponse<String> response = HttpClient.newHttpClient()
                        .send(request, HttpResponse.BodyHandlers.ofString());
                Weather weather = mapper.readValue(response.body(), Weather.class);
                if(weather != null) {
                    redisServiceImpl.set("weather_of_" + location + "_" + startdate + "_" + enddate, weather, 300L);
                }
                return weather;
            } catch (Exception e) {
                System.out.println("Exception: visualcrossing API --> getWeatherByLocationStartDateEndDate(String location, String startdate, String enddate)");
            }
        }
        return new Weather();
    }

}
