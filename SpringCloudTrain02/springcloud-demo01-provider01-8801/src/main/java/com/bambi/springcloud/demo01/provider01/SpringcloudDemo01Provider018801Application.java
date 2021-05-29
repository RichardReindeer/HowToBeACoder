package com.bambi.springcloud.demo01.provider01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class SpringcloudDemo01Provider018801Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringcloudDemo01Provider018801Application.class, args);
    }

}
