package com.rebellion.weather_api_service.entity;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Weather {

    private String address;
    private String resolvedAddress;
    private double latitude;
    private double longitude;
    private List<Day> days;

    public Weather(String address, String resolvedAddress, double latitude, double longitude, List<Day> days) {
        this.address = address;
        this.resolvedAddress = resolvedAddress;
        this.latitude = latitude;
        this.longitude = longitude;
        this.days = days;
    }

    public Weather() {
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getResolvedAddress() {
        return resolvedAddress;
    }

    public void setResolvedAddress(String resolvedAddress) {
        this.resolvedAddress = resolvedAddress;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Day> getDays() {
        return days;
    }

    public void setDays(List<Day> days) {
        this.days = days;
    }

}

class Day {
    private String datetime;
    private double temp;
    private double feelslike;
    private List<Hour> hours;

    public Day(String datetime, double temp, double feelslike, List<Hour> hours) {
        this.datetime = datetime;
        this.temp = temp;
        this.feelslike = feelslike;
        this.hours = hours;
    }

    public Day() {
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getFeelslike() {
        return feelslike;
    }

    public void setFeelslike(double feelslike) {
        this.feelslike = feelslike;
    }

    public List<Hour> getHours() {
        return hours;
    }

    public void setHours(List<Hour> hours) {
        this.hours = hours;
    }

}

class Hour {
    private String datetime;
    private double temp;
    private double feelslike;
    private double humidity;

    public Hour(String datetime, double temp, double feelslike, double humidity) {
        this.datetime = datetime;
        this.temp = temp;
        this.feelslike = feelslike;
        this.humidity = humidity;
    }

    public Hour() {
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getFeelslike() {
        return feelslike;
    }

    public void setFeelslike(double feelslike) {
        this.feelslike = feelslike;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

}
