package com.bambi.springboot.shiro.demo05.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyController {

    /**
     * 调换其他的页面
     */

    @RequestMapping("user/add")
    public String toAdd(){
        return "user/add";
    }

    @RequestMapping("user/update")
    public String toUpdate(){
        return "user/update";
    }

    //跳转登录页面
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
            //登录成功
            return "index";
        }catch (UnknownAccountException e){
            model.addAttribute("message","用户名不对劲");
            return "login";
        }catch (IncorrectCredentialsException e){
            model.addAttribute("message","密码不正确");
            return "login";
        }catch (AuthenticationException e){
            model.addAttribute("message","账号锁定了，自己想办法吧，憨批");
            return "login";
        }
    }

    @RequestMapping("/unauth")
    @ResponseBody
    public String unAuth(){
        return "未经授权不能访问该页面";
    }
}
