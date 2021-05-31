package com.bambi.springcloud.api.service;


import com.bambi.springcloud.api.service.impl.DeptClientServiceFallBackFactory;
import com.bambi.springcloud.api.service.impl.DeptServiceClientImpl;
import com.bambi.springcloud.api.vo.Dept;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

//@Component
//跟Reference一样，会被服务器直接调用
//
@FeignClient(value = "springCloud-provider-dept",fallbackFactory = DeptClientServiceFallBackFactory.class)
public interface IDeptServiceClient {

    //将接口全部写在这里
    @GetMapping("/dept/get/{id}")
    //提供服务
    public Dept queryById(@PathVariable("id") Long id);

    @GetMapping("/dept/list")
    public List<Dept> queryAll();

    @GetMapping("/dept/add")
    public boolean addDept(Dept dept);

}
