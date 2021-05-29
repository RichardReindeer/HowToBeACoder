package com.bambi.springcloud.demo01.provider01.controller;

import com.bambi.springcloud.demo01.api.vo.Dept;
import com.bambi.springcloud.demo01.provider01.service.IDemoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class DemoController {

    @Resource
    private IDemoService demoService;

    @GetMapping("/dept/list")
    public List<Dept> list(){
        return demoService.getAll();
    }
}
