package com.bambi.springcloud.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class UserConsumer02Application {

    public static void main(String[] args) {
        SpringApplication.run(UserConsumer02Application.class);
    }
}
