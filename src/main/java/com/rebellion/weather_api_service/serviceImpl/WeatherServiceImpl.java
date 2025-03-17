package com.rebellion.weather_api_service.serviceImpl;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rebellion.weather_api_service.entity.Weather;
import com.rebellion.weather_api_service.service.WeatherService;

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
    public ResponseEntity<?> getWeatherByLocation(String location) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        if(redisServiceImpl.get("weather_of_" + location, Weather.class) != null) {
            status = HttpStatus.ALREADY_REPORTED;
            return new ResponseEntity<>(redisServiceImpl.get("weather_of_" + location, Weather.class), status);
        } else {
            String baseUrl = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline";
            try {
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(String.format("%s/%s?key=%s&contentType=json", baseUrl, location, key)))
                        .method("GET", HttpRequest.BodyPublishers.noBody()).build();
                HttpResponse<String> response = HttpClient.newHttpClient()
                        .send(request, HttpResponse.BodyHandlers.ofString());
                Weather weather = mapper.readValue(response.body(), Weather.class);
                status = HttpStatus.FOUND;
                if(weather != null) {
                    redisServiceImpl.set("weather_of_" + location, weather, 300L);
                }
                return new ResponseEntity<>(weather, status);
            } catch (Exception e) {
                System.out.println("Exception: visualcrossing API --> getWeatherByLocation(String location)");
                status = HttpStatus.EXPECTATION_FAILED;
                return new ResponseEntity<>(new Weather(), status);
            }
        }
    }

    @Override
    public ResponseEntity<?> getWeatherByLocationStartDate(String location, String startdate) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        if(redisServiceImpl.get("weather_of_" + location + "_" + startdate, Weather.class) != null) {
            status = HttpStatus.ALREADY_REPORTED;
            return new ResponseEntity<>(redisServiceImpl.get("weather_of_" + location + "_" + startdate, Weather.class), status);
        } else {
            String baseUrl = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline";
            try {
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(String.format("%s/%s/%s?key=%s&contentType=json", baseUrl, location, startdate, key)))
                        .method("GET", HttpRequest.BodyPublishers.noBody()).build();
                HttpResponse<String> response = HttpClient.newHttpClient()
                        .send(request, HttpResponse.BodyHandlers.ofString());
                Weather weather = mapper.readValue(response.body(), Weather.class);
                status = HttpStatus.FOUND;
                if(weather != null) {
                    redisServiceImpl.set("weather_of_" + location + "_" + startdate, weather, 300L);
                }
                return new ResponseEntity<>(weather, status);
            } catch (Exception e) {
                System.out.println("Exception: visualcrossing API --> getWeatherByLocationStartDate(String location, String startdate)");
                status = HttpStatus.EXPECTATION_FAILED;
                return new ResponseEntity<>(new Weather(), status);
            }
        }
    }

    @Override
    public ResponseEntity<?> getWeatherByLocationStartDateEndDate(String location, String startdate, String enddate) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        if(redisServiceImpl.get("weather_of_" + location + "_" + startdate + "_" + enddate, Weather.class) != null) {
            status = HttpStatus.ALREADY_REPORTED;
            return new ResponseEntity<>(redisServiceImpl.get("weather_of_" + location + "_" + startdate + "_" + enddate, Weather.class), status);
        } else {
            String baseUrl = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline";
            try {
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(String.format("%s/%s/%s/%s?key=%s&contentType=json", baseUrl, location, startdate, enddate, key)))
                        .method("GET", HttpRequest.BodyPublishers.noBody()).build();
                HttpResponse<String> response = HttpClient.newHttpClient()
                        .send(request, HttpResponse.BodyHandlers.ofString());
                Weather weather = mapper.readValue(response.body(), Weather.class);
                status = HttpStatus.FOUND;
                if(weather != null) {
                    redisServiceImpl.set("weather_of_" + location + "_" + startdate + "_" + enddate, weather, 300L);
                }
                return new ResponseEntity<>(weather, status);
            } catch (Exception e) {
                System.out.println("Exception: visualcrossing API --> getWeatherByLocationStartDateEndDate(String location, String startdate, String enddate)");
                status = HttpStatus.EXPECTATION_FAILED;
                return new ResponseEntity<>(new Weather(), status);
            }
        }
    }

}
