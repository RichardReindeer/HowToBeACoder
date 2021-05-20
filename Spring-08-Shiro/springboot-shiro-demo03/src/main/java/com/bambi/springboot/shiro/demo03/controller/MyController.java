package com.bambi.springboot.shiro.demo03.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
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

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/Login")
    public String login(String username , String password,Model model){
        Subject subject = SecurityUtils.getSubject();
        //封装用户的登录数据
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        //用户登录
        try{
            subject.login(token);
            //登陆成功访问首页
            return "index";
        }catch (UnknownAccountException e){
            model.addAttribute("message","用户名不对劲");
            //重新登录
            return "login";
        }catch (IncorrectCredentialsException e){
            return "login";
            //↓↓↓↓↓登录失败账号锁定
        }catch (LockedAccountException e){
            return null;
        }

    }
}
