package com.bambi.springcloud.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients({"com.bambi.springcloud"})
public class DeptConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(DeptConsumerApplication.class);
    }
}