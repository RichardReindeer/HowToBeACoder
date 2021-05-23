package com.bambi.consumer.server.service;

import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

@Service//加入到Spring容器中
public class IUserService {

    //想拿到生产者的票   要去注册中心拿到服务
    @DubboReference//远程引用   如果引用不到  1.引用Pom坐标    2.可以定义路径相同的接口名
    TicketService ticketService;

    //使用:
    //写一个买票的服务
    public void buyTicket(){
        String ticket = ticketService.getTicket();
        System.out.println("在注册中心拿到票"+ticket);
    }
}
