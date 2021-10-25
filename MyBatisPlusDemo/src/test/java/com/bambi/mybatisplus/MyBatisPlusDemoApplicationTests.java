package com.bambi.mybatisplus;

import com.bambi.mybatisplus.dao.User;
import com.bambi.mybatisplus.mapper.IUserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MyBatisPlusDemoApplicationTests {

    @Autowired
    private IUserMapper iUserMapper;

    @Test
    void contextLoads() {
    }

    @Test
    public void test(){
        User u1 = new User();
        u1.setAge(13);
        u1.setEmail("ss");
        u1.setName("ss");
        iUserMapper.insert(u1);
    }
}
