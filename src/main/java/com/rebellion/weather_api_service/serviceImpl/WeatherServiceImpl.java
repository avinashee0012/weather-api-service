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

// TODO: Redis cache to be configured
@Service
public class WeatherServiceImpl implements WeatherService {

    private final ObjectMapper mapper;
    // private final String key = "your_visualcrossing_key";
    // TODO: Remove key
    private final String key = "MSM92WRGS5BRUMVSTANQ9KPM3";

    public WeatherServiceImpl(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override 
    public Weather getWeatherByLocation(String location) {
        Weather weather = new Weather();
        String baseUrl = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/";
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(String.format("%s%s?key=%s&contentType=json", baseUrl, location, key)))
                    .method("GET", HttpRequest.BodyPublishers.noBody()).build();
            HttpResponse<String> response = HttpClient.newHttpClient()
                    .send(request, HttpResponse.BodyHandlers.ofString());
                    weather = mapper.readValue(response.body(), Weather.class);
                    // weather.setFetchTime(LocalDateTime.now());
        } catch (Exception e) {
            System.out.println("Exception: visualcrossing API --> getWeatherByLocation(String location)");
        }
        return weather;
    }

    @Override
    public Weather getWeatherByLocationStartDate(String location, String startdate) {
        Weather weather = new Weather();
        String baseUrl = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline";
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(String.format("%s/%s/%s?key=%s&contentType=json", baseUrl, location, startdate, key)))
                    .method("GET", HttpRequest.BodyPublishers.noBody()).build();
            HttpResponse<String> response = HttpClient.newHttpClient()
                    .send(request, HttpResponse.BodyHandlers.ofString());
                    weather = mapper.readValue(response.body(), Weather.class);
        } catch (Exception e) {
            System.out.println("Exception: visualcrossing API --> getWeatherByLocationStartDate(String location, String startdate)");
        }
        return weather;
    }

    @Override
    public Weather getWeatherByLocationStartDateEndDate(String location, String startdate, String enddate) {
        Weather weather = new Weather();
        String baseUrl = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline";
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(String.format("%s/%s/%s/%s?key=%s&contentType=json", baseUrl, location, startdate, enddate, key)))
                    .method("GET", HttpRequest.BodyPublishers.noBody()).build();
            HttpResponse<String> response = HttpClient.newHttpClient()
                    .send(request, HttpResponse.BodyHandlers.ofString());
                    weather = mapper.readValue(response.body(), Weather.class);
        } catch (Exception e) {
            System.out.println("Exception: visualcrossing API --> getWeatherByLocationStartDateEndDate(String location, String startdate, String enddate)");
        }
        return weather;
    }

}
