package com.bambi.springcloud.demo04.provider.controller;

import com.bambi.springcloud.api.vo.Dept;
import com.bambi.springcloud.demo04.provider.mapper.DeptMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class Demo04Controller {

    @Resource
    private DeptMapper deptMapper;

    @GetMapping("/dept/list")
    public List<Dept> getList(){
        return deptMapper.getList();
    }
}
