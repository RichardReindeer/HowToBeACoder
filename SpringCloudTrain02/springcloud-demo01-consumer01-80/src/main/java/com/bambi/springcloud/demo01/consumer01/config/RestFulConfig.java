package com.bambi.springcloud.demo01.consumer01.config;

import com.bambi.springcloud.demo01.api.vo.Dept;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestFulConfig {

    @LoadBalanced
    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @LoadBalanced
    @Bean
    public Dept dept(){
        return new Dept();
    }
}
