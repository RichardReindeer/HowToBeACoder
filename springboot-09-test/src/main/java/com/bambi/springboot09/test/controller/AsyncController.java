package com.bambi.springboot09.test.controller;

import com.bambi.springboot09.test.service.AsyncService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class AsyncController {

    @Resource
    private AsyncService asyncService;


    @GetMapping("/hello")
    public String hello(){
        asyncService.hello();
        return "正常显示";
    }
}
