package com.bambi.springboot.swagger.demo01.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;

/**
 * Swagger配置文件
 */
@Configuration
public class SwaggerConfig {
    //使用@Bean注解创建一个Docket实例
    @Bean
    public Docket getDocket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo());
    }
    //配置Swagger中的信息
    //Swagger里面没有set方法，需要使用构造方法赋值

    private ApiInfo apiInfo(){
        Contact contact = new Contact("Mr.bambi","","");
        return new ApiInfo("SwaggerDemo01"
                , "今天外面依旧很热"
                , "1.0"
                , "urn:tos"
                , contact
                , "Apache 2.0"
                , "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());
    }

}
