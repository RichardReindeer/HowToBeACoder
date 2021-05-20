package com.bambi.springbootshirodemo02.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 测试使用的控制器
 */
@Controller
public class MyController {

    @RequestMapping({"/","/index"})
    public String toIndex(Model model){
        model.addAttribute("message","hello shiro");
        return "index";
    }

    @RequestMapping("/user/update")
    public String toUpdate(){
        return "/user/update";
    }

    @RequestMapping("/user/add")
    public String toAdd(){
        return "/user/add";
    }
    @RequestMapping("/login")
    public String toLogin(){
        return "login";
    }
}
