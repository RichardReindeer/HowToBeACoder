package com.bambi.springboot09.test;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@SpringBootTest
class Springboot09TestApplicationTests {

    @Resource
    JavaMailSenderImpl javaMailSender;
    @Test
    void contextLoads() {
        //一个简单的邮件
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setSubject("Spring邮件发送通知");
        mailMessage.setText("这是测试内容");
        //可以发送给自己
        mailMessage.setTo("510614397@qq.com");
        mailMessage.setFrom("510614397@qq.com");
        System.out.println("发送成功");
        javaMailSender.send(mailMessage);
    }

    //发送复杂的邮件
    @Test
    void email() throws MessagingException {
        //创建复杂文件有多种方法
        //1.使用new MimeMessage的方式
        //2.使用javaMailSender自己的createMimeMessage
        //3.还可以传入一个输入流
        MimeMessage message = javaMailSender.createMimeMessage();
        //组装
        //开启mimeMessage
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message,true);
        mimeMessageHelper.setSubject("这是一个复杂邮件的主题");
        //boolean 是否解析为html
        mimeMessageHelper.setText("<p style='color:red'>这是一个内容测试</p>",true);
        //附件
        mimeMessageHelper.addAttachment("1.png",new File("C:\\Users\\1\\Desktop\\新建文件夹\\873441.png"));
        mimeMessageHelper.setTo("510614397@qq.com");
        mimeMessageHelper.setFrom("510614397@qq.com");
        javaMailSender.send(message);
    }

    /**
     *
     * @param html 是否开启mimeMessage
     * @param subject MimeMessage的主题
     * @param text MimeMessage邮件的正文
     * @param textHtml 正文是否识别为html文本
     * @param addAttachmentFileName 发送文件的名称
     * @param sendFile 需要发送的文件
     * @param sendTo 收信人
     * @param sendFrom 发件者
     * @throws MessagingException
     * @Aurher Mr.Bambi
     */
    public void sendMimeMail(Boolean html,String subject ,String text,boolean textHtml,String addAttachmentFileName,File sendFile,String sendTo,String sendFrom) throws MessagingException {
        //创建复杂文件有多种方法
        //1.使用new MimeMessage的方式
        //2.使用javaMailSender自己的createMimeMessage
        //3.还可以传入一个输入流
        MimeMessage message = javaMailSender.createMimeMessage();
        //组装
        //开启mimeMessage
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message,html);
        mimeMessageHelper.setSubject(subject);
        //boolean 是否解析为html
        mimeMessageHelper.setText(text,textHtml);
        //附件
        mimeMessageHelper.addAttachment(addAttachmentFileName,sendFile);
        mimeMessageHelper.setTo(sendTo);
        mimeMessageHelper.setFrom(sendFrom);
        javaMailSender.send(message);
    }

}
