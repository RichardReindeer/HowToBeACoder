package com.bambi.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ConfigBean {

    //@Configuration 相当于 Spring applicationContext.xml

    //配置负载均衡实现RestTemplate
    @LoadBalanced//Ribbon
    @Bean
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }


}
