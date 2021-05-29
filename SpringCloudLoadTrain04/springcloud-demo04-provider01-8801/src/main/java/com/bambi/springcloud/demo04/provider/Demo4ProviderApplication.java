package com.bambi.springcloud.demo04.provider;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.bambi.springcloud.demo04.provider.mapper")
public class Demo4ProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(Demo4ProviderApplication.class);
    }
}
