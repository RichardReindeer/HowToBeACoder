package com.bambi.springboot.shiro.demo04.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 测试一下好不好使
 */
@Controller
public class MyController {

    @RequestMapping("/index")
    public String toIndex(Model model){
        model.addAttribute("message","hello shiro");
        return "index";
    }

}
