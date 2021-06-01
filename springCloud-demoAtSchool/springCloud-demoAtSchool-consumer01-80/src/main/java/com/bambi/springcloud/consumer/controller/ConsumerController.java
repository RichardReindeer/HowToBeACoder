package com.bambi.springcloud.consumer.controller;

import com.bambi.springcloud.vo.UserVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class ConsumerController {

    @Resource
    private RestTemplate restTemplate;

    private static final String REST_URL = "http://localhost:8801";

    @GetMapping("/consumer/user/list")
    public List<UserVo> getList(){
        return restTemplate.getForObject(REST_URL+"/user/list",List.class);
    }

    @GetMapping("/consumer/user/getUser/{userId}")
    public UserVo getUserVo(@PathVariable("userId") Long userId){
        return restTemplate.getForObject(REST_URL+"/user/getUser/"+userId,UserVo.class);
    }
}
