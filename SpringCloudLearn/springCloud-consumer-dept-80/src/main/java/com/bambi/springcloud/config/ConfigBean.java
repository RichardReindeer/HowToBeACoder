package com.bambi.springcloud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ConfigBean {

    //@Configuration 相当于 Spring applicationContext.xml

    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
