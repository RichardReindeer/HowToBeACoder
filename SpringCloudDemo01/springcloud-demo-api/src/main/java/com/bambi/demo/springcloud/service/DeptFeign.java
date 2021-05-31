package com.bambi.demo.springcloud.service;

import com.bambi.demo.springcloud.vo.Dept;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

//传入服务的id名称
@FeignClient("SPRINGCLOUD-DEMO-PROVIDER-01")
public interface DeptFeign {
    @GetMapping("/dept/list")
    public List<Dept> list();

    @GetMapping("/dept/get/{id}")
    public Dept get(@PathVariable("id") Long id);

    @PostMapping("/dept/add")
    public boolean add(@RequestBody Dept dept);
}
