package com.rebellion.weather_api_service.serviceImpl;

import java.util.concurrent.TimeUnit;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rebellion.weather_api_service.service.RedisService;

@Service
public class RedisServiceImpl implements RedisService {

    private RedisTemplate<String, String> redisTemplate;
    private ObjectMapper mapper;

    public RedisServiceImpl(ObjectMapper mapper, RedisTemplate<String, String> redisTemplate) {
        this.mapper = mapper;
        this.redisTemplate = redisTemplate;
    }

    @Override
    public <T> T get(String key, Class<T> entityClass) {
        Object object = redisTemplate.opsForValue().get(key);
        if(object != null) {
            try {
                return mapper.readValue(object.toString(), entityClass);
            } catch (JsonProcessingException e) {
                System.out.println(e.getMessage());
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public void set(String key, Object object, Long ttl) {
        try {
            String jsonString = mapper.writeValueAsString(object);
            redisTemplate.opsForValue().set(key, jsonString, ttl, TimeUnit.SECONDS);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
