package com.example.demo.configuration;

import com.example.demo.loginLimit.LoginAttempt;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;


@Configuration
public class CacheConfig {

    @Bean
    public Cache<String, LoginAttempt> loginAttemptCache() {
        return Caffeine.newBuilder().maximumSize(100).expireAfterWrite(15, TimeUnit.MINUTES).build();
    }

}
