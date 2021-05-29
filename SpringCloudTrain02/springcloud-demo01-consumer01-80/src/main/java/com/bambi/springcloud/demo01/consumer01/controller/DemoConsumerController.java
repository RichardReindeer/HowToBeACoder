package com.bambi.springcloud.demo01.consumer01.controller;

import com.bambi.springcloud.demo01.api.vo.Dept;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class DemoConsumerController {

    @Resource
    private Dept dept;

    private static final String REST_URL="http://SPRINGCLOUD-DEMO01-PROVIDER";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/consumer/dept/list")
    public List<Dept> list(){
        return restTemplate.getForObject(REST_URL+"/dept/list",List.class);
    }

}
