package com.bambi.springboot.send.demo01.controller;

import io.swagger.annotations.ApiModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * 测试用Controller
 */
@ApiModel("这是一个测试类Controller,不要管它")
@Controller
public class TestController {
    @GetMapping({"/","/index"})
    public String toIndex(Model model){
        model.addAttribute("message","hello shiro");
        return "index";
    }
}
