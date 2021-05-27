package com.bambi.consumer.demo01.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class DeptConfig {

    @Bean
    public RestTemplate getRestTemple(){
        return new RestTemplate();
    }
}
