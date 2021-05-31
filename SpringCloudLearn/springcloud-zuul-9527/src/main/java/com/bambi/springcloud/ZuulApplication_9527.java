package com.bambi.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableEurekaClient
//一般会用代理，因为它并不是一个服务
@EnableZuulProxy
public class ZuulApplication_9527 {

    public static void main(String[] args) {
        SpringApplication.run(ZuulApplication_9527.class);
    }
}
