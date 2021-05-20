package com.bambi.springboot.shiro.demo03.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyController {

    @RequestMapping({"/","/index"})
    public String goIndex(Model model){
        model.addAttribute("message","hello shiro");
        return "index";
    }

    @RequestMapping("/user/add")
    public String goAdd(){
        return "user/add";
    }

    @RequestMapping("/user/update")
    public String goUpdate(){
        return "user/update";
    }
}
