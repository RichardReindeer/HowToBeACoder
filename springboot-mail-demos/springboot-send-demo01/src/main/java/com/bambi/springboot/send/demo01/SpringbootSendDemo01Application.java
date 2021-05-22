package com.bambi.springboot.send.demo01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableScheduling
public class SpringbootSendDemo01Application {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootSendDemo01Application.class, args);
    }

}
