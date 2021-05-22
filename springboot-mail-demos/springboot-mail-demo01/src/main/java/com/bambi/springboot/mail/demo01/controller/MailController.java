package com.bambi.springboot.mail.demo01.controller;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * 发送邮件的controller
 */
@Controller
public class MailController {

    @Resource
    JavaMailSenderImpl javaMailSender;

    @GetMapping("/sendHello")
    @ResponseBody
    public String sendHello(){
        sendSimpleMessage();
        return "信息发送完成";
    }

    private void sendSimpleMessage(){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo("510614397@qq.com");
        simpleMailMessage.setFrom("510614397@qq.com");
        simpleMailMessage.setSubject("这是自己的一个练习");
        simpleMailMessage.setText("打字打字打字");
        javaMailSender.send(simpleMailMessage);
    }
}
