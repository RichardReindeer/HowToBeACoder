package com.bambi.springcloud.consumer.controller;

import com.bambi.demo.springcloud.service.DeptFeign;
import com.bambi.demo.springcloud.vo.Dept;
import com.bambi.springcloud.consumer.config.FeignConfig;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class ConsumerController {

    @Resource
    private RestTemplate restTemplate;
    @Resource
    private DeptFeign deptFeign;

    private static final String REST_URL="http://localhost:8801";

    @GetMapping("/consumer/dept/list")
    public List<Dept> list(){
        return deptFeign.list();
    }
}
