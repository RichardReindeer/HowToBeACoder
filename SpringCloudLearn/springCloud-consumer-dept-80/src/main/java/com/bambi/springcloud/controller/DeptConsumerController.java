package com.bambi.springcloud.controller;

import com.bambi.springcloud.api.vo.Dept;
import org.springframework.web.bind.annotation.*;
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

    //通过Ribbon去注册时，服务的地址应该是一个变量，通过服务名去访问(Eureka上的名字)

    //因为没有业务逻辑层，所以想要获取对象信息需要去别的服务中拿，http://localhost:8001/dept/list ....
    //因为前面的内容是固定的，所以可以设置一个常量
    private static final String REST_URL_PREFIX = "http://springCloud-provider-dept-8001";


    //与RPC不同的是，不再需要使用DubboReference来远程调用了，只需要使用RestTemplate，用url去请求,
    //实现了完全解耦!!!!!!!!!!!


    //注意，此处需要在provider中添加Requestbody注解
    @RequestMapping("/consumer/dept/add")
    public boolean add( Dept dept){
        //postForObject 中的第二个参数就是请求参数，比如想要添加一个DEPT，直接把dept赋值过去就好
        return restTemplate.postForObject(REST_URL_PREFIX+"/dept/add",dept,Boolean.class);
    }

    @RequestMapping("/consumer/dept/get/{id}")
    public Dept get(@PathVariable("id") Long id){
        //拼接请求路径， responseType要求的是返回值的class类型
        return restTemplate.getForObject(REST_URL_PREFIX+"/dept/get/"+id,Dept.class);
    }

    @RequestMapping("/consumer/dept/list")
    public List<Dept> list(){
        //想携带的参数可以直接是一个实体，也可以使用map封装
        return restTemplate.getForObject(REST_URL_PREFIX+"/dept/list",List.class);
    }
}
