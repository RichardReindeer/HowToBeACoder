package com.bambi.springcloud.controller;

import com.bambi.springcloud.api.service.IDeptServiceClient;
import com.bambi.springcloud.api.vo.Dept;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

/**
 * 控制器类
 */

@RestController
public class DeptConsumerController {

    //消费者不应该有数据访问层(service)应该使用url去请求
    //RestFul风格请求
    //RestTemplate ... 我们直接调用就可以，首先需要注册到Spring 中

    //url , 实体(Map) ,Request
    @Resource
    private RestTemplate restTemplate;//提供多种便捷访问远程http服务的方法，简单的restFul服务模板


    @Resource
    private IDeptServiceClient deptServiceClient ;

    //与RPC不同的是，不再需要使用DubboReference来远程调用了，只需要使用RestTemplate，用url去请求,
    //实现了完全解耦!!!!!!!!!!!


    //注意，此处需要在provider中添加Requestbody注解
    @RequestMapping("/consumer/dept/add")
    public boolean add( Dept dept){
        //postForObject 中的第二个参数就是请求参数，比如想要添加一个DEPT，直接把dept赋值过去就好
        return this.deptServiceClient.addDept(dept);
    }

    @RequestMapping("/consumer/dept/get/{id}")
    public Dept get(@PathVariable("id") Long id){
        //拼接请求路径， responseType要求的是返回值的class类型
        return this.deptServiceClient.queryById(id);
    }

    @RequestMapping("/consumer/dept/list")
    public List<Dept> list(){
        //想携带的参数可以直接是一个实体，也可以使用map封装
        return this.deptServiceClient.queryAll();
    }
}
