package com.bambi.springcloud.provider.controller;

import com.bambi.demo.springcloud.vo.Dept;
import com.bambi.springcloud.provider.mapper.DeptMapper;
import com.bambi.springcloud.provider.serivce.IDeptService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class ProviderController {

    @Resource
    private IDeptService deptService;

    @GetMapping("/dept/list")
    public List<Dept> list(){
        return deptService.list();
    }

    @GetMapping("/dept/get/{id}")
    public Dept get(@PathVariable("id") Long id){
        return deptService.getDeptByNo(id);
    }

    @PostMapping("/dept/add")
    public boolean add(@RequestBody Dept dept){
        return deptService.add(dept);
    }
}
