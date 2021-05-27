package com.bambi.provider.demo02;

import com.bambi.provider.demo02.service.IDeptService;
import com.bambi.springcloud.api.vo.Dept;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
public class SpringDeptTest {

    @Resource
    private IDeptService deptService;

    @Test
    public void get(){
        List<Dept> depts = deptService.getAll();
        depts.forEach(dept -> System.out.println("dept = " + dept));
    }
}
