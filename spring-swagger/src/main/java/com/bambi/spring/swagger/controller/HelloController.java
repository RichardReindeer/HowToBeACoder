package com.bambi.spring.swagger.controller;

import com.bambi.spring.swagger.vo.User;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        return "hello World";
    }

    //只要我们的接口中，返回值存在实体类，就会被扫描到Swagger中
    @PostMapping("/user")
    public User user(){
        return new User();
    }

    //给接口加一个注释
    //Operation不是放在类上的，是放在方法上的
    @ApiOperation("hello 控制类")
    @GetMapping("/hello2")
    public String hello2(@ApiParam("用户名") String username){
        return "hello"+username;
    }

    @ApiOperation("post测试类")
    @PostMapping("/postTest")
    public String postTest(@ApiParam("用户名") User user){
        return "Post Test "+user;
    }
}
