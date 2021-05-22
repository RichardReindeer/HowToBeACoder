package com.bambi.springboot.send.demo01.controller;

import io.swagger.annotations.ApiModel;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@ApiModel("这是一个正式处理页面请求的controller")
@Controller
public class ShiroController {

    @GetMapping("user/send")
    public String toSend(){
        return "user/send";
    }

    @GetMapping("user/update")
    public String toUpdate(){
        return "user/update";
    }

    @GetMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    @PostMapping("/login")
    public String login(String username,String password){
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
            return "index";
        }catch (UnknownAccountException e){
            return "login";
        }catch (IncorrectCredentialsException e){
            return "login";
        }catch (AuthenticationException e){
            return "login";
        }
    }

}
