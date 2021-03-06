package com.bambi.provider.server.service;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Component;

@DubboService//可以被扫描到，在项目一启动就自动注册到注册中心
//使用了Dubbo之后尽量不要用Service注解
@Component
public interface TicketService {

    public String getTicket();
}
