package com.bambi.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.ComponentScan;

/**
 *Ribbon和Eureka整合以后，客户端可以直接调用方法，不用关心ip地址和端口号
 */
@SpringBootApplication
@EnableEurekaClient
//需要添加fegin注解  参数为要扫描的包
@EnableFeignClients(basePackages = {"com.bambi.springcloud"})
public class DeptConsumerFeign_80 {

    public static void main(String[] args) {
        SpringApplication.run(DeptConsumerFeign_80.class);
    }
}
