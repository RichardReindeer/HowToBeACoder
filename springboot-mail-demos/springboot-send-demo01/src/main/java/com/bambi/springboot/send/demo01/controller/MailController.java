package com.bambi.springboot.send.demo01.controller;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class MailController {

    @Resource
    JavaMailSenderImpl javaMailSender;

    @GetMapping("/sendMail")
    @ResponseBody
    public String sendMail(){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject("这是一个邮件主题");
        simpleMailMessage.setText("这是邮件正文");
        simpleMailMessage.setFrom("510614397@qq.com");
        simpleMailMessage.setTo("510614397@qq.com");
        javaMailSender.send(simpleMailMessage);
        return "Ok";
    }

}
