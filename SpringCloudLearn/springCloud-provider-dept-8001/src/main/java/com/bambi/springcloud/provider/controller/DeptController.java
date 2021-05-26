package com.bambi.springcloud.provider.controller;

import com.bambi.springcloud.api.vo.Dept;
import com.bambi.springcloud.provider.service.IDeptService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 提供Restful服务
 */
@RestController
public class DeptController {

    @Resource
    IDeptService service;

    @PostMapping("/dept/add")
    public boolean addDept( @RequestBody Dept dept){
        return service.addDept(dept);
    }

    @GetMapping("/dept/get/{id}")
    public Dept get(@PathVariable("id") Long id){
        return service.queryDeptById(id);
    }

    @GetMapping("/dept/list")
    public List<Dept> queryAll(){
        return service.queryAll();
    }
}
