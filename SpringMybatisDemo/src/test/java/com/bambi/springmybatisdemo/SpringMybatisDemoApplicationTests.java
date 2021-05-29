package com.bambi.springmybatisdemo;

import com.bambi.springmybatisdemo.service.IDeptService;
import com.bambi.springmybatisdemo.vo.DeptVo;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class SpringMybatisDemoApplicationTests {

    @Resource
    private IDeptService deptService;
    @Test
    void contextLoads() {
        List<DeptVo> deptVos = deptService.get();
        System.out.println("deptVos = " + deptVos);
    }

}
