package com.bambi.springboot.send.demo01.config;

import io.swagger.annotations.ApiModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;

@ApiModel("这是Swagger的配置类")
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket getDocket(Environment environment){
        Profiles profiles = Profiles.of("dev");
        boolean acceptsProfiles = environment.acceptsProfiles(profiles);

        return new Docket(DocumentationType.SWAGGER_2).groupName("Bambi")
                .apiInfo(sendApiInfo())
                .enable(acceptsProfiles)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.bambi.springboot.send.demo01"))
                .build();
    }

    private ApiInfo sendApiInfo(){
        Contact contact = new Contact("Bambi","","510614397@qq.com");
        ApiInfo apiInfo = new ApiInfo("邮件自动发送",
                "根据用户数据库存储的权限，进行邮件发送的登录，如果登录成功则自动发送邮件到作者邮箱",
                "0.1",
                "urn:tos",
                contact,
                "SendMail.2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());
        return apiInfo;
    }
}
