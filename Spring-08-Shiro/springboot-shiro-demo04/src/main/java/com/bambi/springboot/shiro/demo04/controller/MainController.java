package com.bambi.springboot.shiro.demo04.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    @RequestMapping({"user/add"})
    public String toAdd(){
        return "user/add";
    }
    @RequestMapping({"user/update"})
    public String toUpdate(){
        return "user/update";
    }

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/Login")
    public String login(String username , String password , Model model){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        try {
            subject.login(token);
            return "index";
        }catch (UnknownAccountException e){
            model.addAttribute("message","用户名不对");
            return "login";
        }catch (IncorrectCredentialsException e){
            model.addAttribute("message","密码不对");
            return "login";
        }catch (LockedAccountException e){
            return null;
        }
    }
}
