package com.bambi.springcloud.provider.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class ProviderConfig {

    @Bean
    public Docket getDocket(){
        return new Docket(DocumentationType.SWAGGER_2).
                apiInfo(apiInfo())
                .enable(true)
                .groupName("BambiTeam")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.bambi.springcloud"))
                .build();
    }

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
