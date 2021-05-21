package com.bambi.springboot.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DemoController {

    @RequestMapping("/index")
    public String goIndex(Model model){
        model.addAttribute("msg","hello shiro");
        return "index";
    }

    @RequestMapping("user/add")
    public String add(){
        return "user/add";
    }

    @RequestMapping("user/update")
    public String update(){
        return "user/update";
    }

    //跳转登录页面
    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/login")
    public String login(String username , String password,Model model){
        //获取当前用户
        Subject currentUser = SecurityUtils.getSubject();
        //封装用户的登录数据
        //可以验证密码是否成功或者失败 放在令牌加密
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        //固定的登录
        try{
            //shiro已经将判断用户名和密码完成了封装，如果没有异常则说明ok
            currentUser.login(token);
            return "index";
        }catch (UnknownAccountException e){
            System.out.println("用户名不存在异常");
            model.addAttribute("message","用户名异常");
            return "login";
        }catch (IncorrectCredentialsException e){
            System.out.println("密码不存在");
            model.addAttribute("message","密码错误");
            return "login";
        }

    }

    @RequestMapping("/noauth")
    @ResponseBody
    public String unAuthorized(){
        return "未经授权无法访问此页面";
    }
}
