package com.bambi.springcloud.consumer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class Consumer01Application {

    public static void main(String[] args) {
        SpringApplication.run(Consumer01Application.class);
    }
}
