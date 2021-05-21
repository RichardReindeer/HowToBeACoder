package com.bambi.spring.swagger.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
//开启Swagger2
@EnableSwagger2
public class SwaggerConfig {

    //配置Swagger的Docket 的Bean实例
    @Bean
    public Docket getDocket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo());
    }

    //配置Swagger 信息 ==> ApiInfo
    private ApiInfo apiInfo (){
        //作者信息
        Contact contact = new Contact("Bambi", "http://localhost8080", "zhuyulin@gmail.com");
        return new ApiInfo("Mr.Bambi.Swagger日记",
                "这个地方使用来描述作者有多帅的",
                "Bambi",
                "http://localhost8080",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());
    }

}
