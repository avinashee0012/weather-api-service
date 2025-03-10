package com.rebellion.weather_api_service.serviceImpl;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rebellion.weather_api_service.entity.Weather;
import com.rebellion.weather_api_service.service.WeatherService;

@Service
public class WeatherServiceImpl implements WeatherService {

    private final ObjectMapper mapper;
    private final String key = "your_visualcrossing_key";

    public WeatherServiceImpl(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    // TODO: Redis cache to be configured
    public Weather getWeatherByLocation(String location) {
        Weather weather = new Weather();
        String baseUrl = "https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/";
        String city = location;
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(String.format("%s%s?key=%s&contentType=json", baseUrl, city, key)))
                    .method("GET", HttpRequest.BodyPublishers.noBody()).build();
            HttpResponse<String> response = HttpClient.newHttpClient()
                    .send(request, HttpResponse.BodyHandlers.ofString());
                    weather = mapper.readValue(response.body(), Weather.class);
                    weather.setFetchTime(LocalDateTime.now());
        } catch (Exception e) {
            System.out.println("Exception occured during 3rd party API call.");
        }
        return weather;
    }

    @Override
    public Weather getWeatherByLocationStartDate(String location, String startdate) {
        // TODO Implement getWeatherByLocationStartDate(String location, String
        // startdate)
        return new Weather();
    }

    @Override
    public Weather getWeatherByLocationStartDateEndDate(String location, String startdate, String enddate) {
        // TODO Implement getWeatherByLocationStartDateEndDate(String location, String
        // startdate, String enddate)
        return new Weather();
    }

}
