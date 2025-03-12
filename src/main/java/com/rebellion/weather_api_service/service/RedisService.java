package com.rebellion.weather_api_service.service;

public interface RedisService {
    <T> T get(String key, Class<T> entityClass);
    void set(String key, Object object, Long ttl);
}
