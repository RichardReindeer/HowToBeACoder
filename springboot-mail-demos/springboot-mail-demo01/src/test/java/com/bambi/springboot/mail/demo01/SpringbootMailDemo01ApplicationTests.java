package com.bambi.springboot.mail.demo01;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.MailMessage;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;

@SpringBootTest
class SpringbootMailDemo01ApplicationTests {

    @Resource
    JavaMailSenderImpl javaMailSender ;

    @Test
    void contextLoads() {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo("510614397@qq.com");
        simpleMailMessage.setFrom("510614397@qq.com");
        simpleMailMessage.setSubject("这是自己的一个练习");
        simpleMailMessage.setText("打字打字打字");
        javaMailSender.send(simpleMailMessage);
    }

}
