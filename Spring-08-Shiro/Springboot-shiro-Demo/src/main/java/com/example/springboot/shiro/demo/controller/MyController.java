package com.example.springboot.shiro.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class MyController {

    @RequestMapping({"/","/index"})
    public String goIndex(Model model){
        model.addAttribute("message","hello shiro");
        return "index";
    }
}
