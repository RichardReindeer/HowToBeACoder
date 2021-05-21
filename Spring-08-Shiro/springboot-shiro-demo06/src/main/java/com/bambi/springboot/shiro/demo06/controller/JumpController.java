package com.bambi.springboot.shiro.demo06.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class JumpController {
    @RequestMapping("/user/add")
    public String toAdd(){
        return "user/add";
    }

    @RequestMapping("/user/update")
    public String toUpdate(){
        return "user/update";
    }

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }
    @RequestMapping("/login")
    public String login(String username , String password , Model model){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        try {
            subject.login(token);
            return "index";
        }catch (UnknownAccountException e){
            model.addAttribute("message","用户名不正确");
            return "login";
        }catch (IncorrectCredentialsException e){
            model.addAttribute("message","用户密码不正确");
            return "login";
        }
        catch (LockedAccountException e) {
            e.printStackTrace();
            return "login";
        }
    }

    @RequestMapping("/unauth")
    @ResponseBody
    public String unauth(){
        return "权限不足，不能访问";
    }
}
