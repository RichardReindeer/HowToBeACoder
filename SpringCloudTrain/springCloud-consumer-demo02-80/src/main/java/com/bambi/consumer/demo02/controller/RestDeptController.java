package com.bambi.consumer.demo02.controller;

import com.bambi.springcloud.api.vo.Dept;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class RestDeptController {

    @Resource
    private RestTemplate restTemplate;

    private static final String REST_URL = "http://localhost:8801/";

    @GetMapping("/consumer/dept/list")
    public List<Dept> list(){
        return restTemplate.getForObject(REST_URL+"dept/list",List.class);
    }

    @GetMapping("/consumer/dept/get/{id}")
    public Dept get( Long id){
        return restTemplate.getForObject(REST_URL+"dept/get"+id,Dept.class);
    }

    @PostMapping("/consumer/dept/add")
    public boolean add(Dept dept){
        return restTemplate.postForObject(REST_URL+"dept/add",dept,Boolean.class);
    }
}
