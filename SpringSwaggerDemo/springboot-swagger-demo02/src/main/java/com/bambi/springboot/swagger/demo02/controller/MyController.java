package com.bambi.springboot.swagger.demo02.controller;

import com.bambi.springboot.swagger.demo02.vo.UserVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 一个项目，默认会存在一个error请求，在base controller中
 */
@RestController
public class MyController {

    @GetMapping("/hello")
    public String hello(){
        return "hello swagger";
    }

    @GetMapping("/user")
    public UserVo getUser(String username , String password){
        System.out.println("username"+username+" password"+password);
        return new UserVo(username,password);
    }
}
