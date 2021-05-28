package com.bambi.springcloud.provider.controller;

import com.bambi.springcloud.api.vo.Dept;
import com.bambi.springcloud.provider.service.IDeptService;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
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

    //获取这些类的信息,得到具体的微服务
    @Resource
    private DiscoveryClient client;

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

    @GetMapping("/dept/discovery")
    //注册进来的微服务，获取一些信息
    public Object discovery(){
        //获得微服务列表的清单
        List<String> services = client.getServices();
        services.forEach(s -> System.out.println(s));
        //得到一个具体的微服务信息
        //需要传入服务实例id applicationName
        List<ServiceInstance> instances = client.getInstances("SPRINGCLOUD-PROVIDER-DEPT-8001");
        instances.forEach(serviceInstance -> System.out.println("serviceInstance's host = " + serviceInstance.getHost()));

        return this.client;
    }
}
