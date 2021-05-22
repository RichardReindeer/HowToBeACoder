package com.bambi.spring.swagger.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
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
    public Docket getDocket1(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("test1");
    }

    @Bean
    public Docket getDocket2(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("test2");
    }
    @Bean
    public Docket getDocket3(){
        return new Docket(DocumentationType.SWAGGER_2).groupName("test3");
    }

    @Bean
    public Docket getDocket(Environment environment){
        //可以返回多个，可变参数
        //设置要显示的swagger环境
        Profiles profiles = Profiles.of("dev");
        //获取项目的环境
        //监听 accept
        //acceptsProfiles 过时的方法中可以传入可变参数，
        //新重载的方法中只能传入一个ProFiles,ProFiles的设置swagger环境的操作单独拿出来进行
        //boolean   <---通过环境监听变量来判断是否处在自己设定的环境当中
        boolean flag = environment.acceptsProfiles(profiles);
        //↑↑↑↑↑↑可以将这个值动态传给enable
        System.out.println(flag);
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("Bambi")
                //enable默认为true，是打开模式，可以改为false    功能:是否启动swagger，如果为false,则swagger不能在浏览器中访问
                .enable(flag)
                .select()
                //RequestHandlerSelectors 配置要扫描接口的方法式
                //basePackage 指定要扫描的包
                //any();扫描全部
                //none() 都不扫描
                //withClassAnnotation 扫描类上的注解
                //withMethodAnnotation 扫描方法上的注解
                .apis(RequestHandlerSelectors.basePackage("com.bambi.spring.swagger.controller"))
                //.apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                //.apis(RequestHandlerSelectors.withClassAnnotation(GetMapping.class))
                //过滤， 过滤什么路径
                //扫描只带有/bambi这个请求的接口 就好比 /v1/users/这样的
                //.paths(PathSelectors.ant("/bambi/**"))
                .build();
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
