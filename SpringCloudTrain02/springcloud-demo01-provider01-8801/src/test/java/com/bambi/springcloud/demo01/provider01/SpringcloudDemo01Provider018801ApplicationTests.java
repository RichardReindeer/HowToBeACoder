package com.bambi.springcloud.demo01.provider01;

import com.bambi.springcloud.demo01.api.vo.Dept;
import com.bambi.springcloud.demo01.provider01.service.IDemoService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class SpringcloudDemo01Provider018801ApplicationTests {

    @Resource
    IDemoService demoService;

    @Test
    void contextLoads() {

        List<Dept> all = demoService.getAll();
        System.out.println("all = " + all);
    }

}
