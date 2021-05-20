package com.bambi.springboot.shiro;

import com.bambi.springboot.shiro.pojo.User;
import com.bambi.springboot.shiro.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class SpringbootShiroApplicationTests {

    @Autowired
    UserService userService;
    @Test
    void contextLoads() {
        User user = userService.queryUserByName("Reindeer");
        System.out.println(user);
    }

}
