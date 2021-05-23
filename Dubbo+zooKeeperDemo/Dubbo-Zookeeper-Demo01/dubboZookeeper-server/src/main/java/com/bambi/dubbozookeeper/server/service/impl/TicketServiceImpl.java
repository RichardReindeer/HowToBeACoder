package com.bambi.dubbozookeeper.server.service.impl;

import com.bambi.dubbozookeeper.server.service.ITicketService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

//需要在实现类上标注DubboService注解，不然不会注册到Registry中
@DubboService
@Service
public class TicketServiceImpl implements ITicketService {


    @Override
    public void buyTicket() {
        System.out.println("这是我买到的票，喜欢吗");
    }
}
