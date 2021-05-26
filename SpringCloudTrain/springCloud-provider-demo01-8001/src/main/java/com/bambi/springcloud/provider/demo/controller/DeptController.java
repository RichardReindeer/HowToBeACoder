package com.bambi.springcloud.provider.demo.controller;

import com.bambi.spring.api.pojo.Dept;
import com.bambi.springcloud.provider.demo.service.IDeptService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 控制层
 */
@RestController
public class DeptController {

    @Resource
    private IDeptService deptService;

    @GetMapping("/dept/list")
    public List<Dept> getList(){
        return deptService.selectAll();
    }

    @PostMapping("/dept/add")
    public boolean add(Dept dept){
        return deptService.addDept(dept);
    }

    @GetMapping("/dept/get/{id}")
    public Dept get(Long id){
        return deptService.selectDeptById(id);
    }
}
