package com.bambi.springcloud.provider.hystrix.controller;

import com.bambi.springcloud.api.vo.Dept;
import com.bambi.springcloud.provider.hystrix.service.IDeptService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 提供Restful服务
 */
@RestController
public class DeptController {

   @Resource
    private IDeptService deptService;

   //HystrixCommand,点开看源码； 设置一个fallbackMethod，指执行出现异常后，选择去执行的另一种方法
   @GetMapping("/dept/get/{id}")
   @HystrixCommand(fallbackMethod = "HystrixGet")
   public Dept get(@PathVariable("id") Long id){
       //get到的Id可能是空的，需要进行非空判断
       Dept dept = deptService.queryDeptById(id);
       System.out.println(id);
       if(dept==null){
           throw new RuntimeException("id->"+id+"不存在该用户，信息无法找到");
       }
       return dept;
   }


   //备选方案  当抛出异常后去调用的方法
    public Dept HystrixGet(Long id){
        return new Dept().setDeptName("这是一个不存在的Id,createdBy Hystrix");
    }
}
