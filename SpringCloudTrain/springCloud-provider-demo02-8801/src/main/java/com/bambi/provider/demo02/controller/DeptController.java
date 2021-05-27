package com.bambi.provider.demo02.controller;

import com.bambi.provider.demo02.service.IDeptService;
import com.bambi.springcloud.api.vo.Dept;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class DeptController {

    @Resource
    private IDeptService deptService;

    @GetMapping("/dept/list")
    public List<Dept> list(){
        return deptService.getAll();
    }

    @GetMapping("/dept/get/{id}")
    public Dept get(@PathVariable Long id){
        return deptService.getDeptById(id);
    }

    @PostMapping("/dept/add")
    public boolean add(@RequestBody Dept dept){
        return deptService.insertDept(dept);
    }

}
