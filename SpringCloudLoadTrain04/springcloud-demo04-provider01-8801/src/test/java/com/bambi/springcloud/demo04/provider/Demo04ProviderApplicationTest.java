package com.bambi.springcloud.demo04.provider;

import com.bambi.springcloud.api.vo.Dept;
import com.bambi.springcloud.demo04.provider.service.IDemo04Service;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
public class Demo04ProviderApplicationTest {

    @Resource
    private IDemo04Service demo04Service;

    @Test
    public void test(){
        List<Dept> list = demo04Service.getList();
        System.out.println("list = " + list);
    }
}
