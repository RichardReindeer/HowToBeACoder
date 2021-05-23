package com.bambi.provider.server.service.impl;

import com.bambi.provider.server.service.TicketService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Component;

//zookeeper:服务注册与发现

@DubboService//可以被扫描到，在项目一启动就自动注册到注册中心
//使用了Dubbo之后尽量不要用Service注解
@Component
public class TicketServiceImpl implements TicketService {
    @Override
    public String getTicket() {
        return "今天天气 雨";
    }
}
