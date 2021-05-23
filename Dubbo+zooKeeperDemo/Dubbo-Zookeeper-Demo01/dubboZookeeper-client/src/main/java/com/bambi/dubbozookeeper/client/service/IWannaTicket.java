package com.bambi.dubbozookeeper.client.service;

import com.bambi.dubbozookeeper.server.service.ITicketService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Service;

@Service
public class IWannaTicket {

    @DubboReference
    ITicketService iTicketService;

    public void getTicket(){
        iTicketService.buyTicket();
    }
}
