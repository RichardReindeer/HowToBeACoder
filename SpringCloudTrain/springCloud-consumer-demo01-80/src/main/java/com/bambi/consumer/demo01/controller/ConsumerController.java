package com.bambi.consumer.demo01.controller;

import com.bambi.spring.api.pojo.Dept;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class ConsumerController {

    @Resource
    private RestTemplate restTemplate;

    private static final String REST_URL_PROVIDER="http://localhost:8001";

    @GetMapping("/consumer/dept/list")
    public List<Dept> getAll(){
        return restTemplate.getForObject(REST_URL_PROVIDER+"/dept/list",List.class);
    }

    @GetMapping("/consumer/dept/get/{id}")
    public Dept get(Long id){
        return restTemplate.getForObject(REST_URL_PROVIDER+"/dept/get/"+id,Dept.class);
    }

    @PostMapping("/consumer/dept/add")
    public boolean add(Dept dept){
        return restTemplate.postForObject(REST_URL_PROVIDER+"/dept/add",dept,Boolean.class);
    }

}
