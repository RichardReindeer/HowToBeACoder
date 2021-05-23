package com.bambi.dubbozookeeper.client.controller;

import com.bambi.dubbozookeeper.client.service.IWannaTicket;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller
public class ConsumerController {

    @Resource
    IWannaTicket wannaTicket;

    @GetMapping("/hello")
    @ResponseBody
    public String ticket(){
        wannaTicket.getTicket();
        return "hello";
    }
}
