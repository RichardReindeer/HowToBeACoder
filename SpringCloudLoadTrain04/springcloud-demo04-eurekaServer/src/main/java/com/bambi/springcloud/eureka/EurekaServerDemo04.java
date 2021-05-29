package com.bambi.springcloud.eureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServerDemo04 {

    public static void main(String[] args) {
        SpringApplication.run(EurekaServerDemo04.class);
    }
}
