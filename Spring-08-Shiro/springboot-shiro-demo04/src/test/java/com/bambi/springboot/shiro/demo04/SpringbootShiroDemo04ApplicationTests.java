package com.bambi.springboot.shiro.demo04;

import com.bambi.springboot.shiro.demo04.pojo.User;
import com.bambi.springboot.shiro.demo04.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class SpringbootShiroDemo04ApplicationTests {

    @Resource
    UserService userService;
    @Test
    void contextLoads() {
        User user = userService.selectUserByUsername("Reindeer");
        System.out.println(user);
    }

}
