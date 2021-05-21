package com.bambi.springboot.shiro.demo06.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 跳转首页
 */
@Controller
public class DemoController {

    @RequestMapping({"/","/index"})
    public String toIndex(Model model){
        model.addAttribute("message","hello shiro");
        return "index";
    }
}
