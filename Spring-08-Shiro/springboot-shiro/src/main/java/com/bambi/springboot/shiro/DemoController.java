package com.bambi.springboot.shiro;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DemoController {

    @RequestMapping("/index")
    public String goIndex(Model model){
        model.addAttribute("msg","hello shiro");
        return "index";
    }
}
