package com.bambi.springboot.shiro.demo05;

import com.bambi.springboot.shiro.demo05.service.IUserService;
import com.bambi.springboot.shiro.demo05.vo.UserVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootShiroDemo05ApplicationTests {

    @Autowired
    IUserService userService;
    @Test
    void contextLoads() {

        UserVo user = userService.selectUserByUsername("User");
        System.out.println(user);
    }

}
