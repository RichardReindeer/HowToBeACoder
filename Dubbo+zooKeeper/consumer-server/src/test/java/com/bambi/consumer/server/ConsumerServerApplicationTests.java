package com.bambi.consumer.server;

import com.bambi.consumer.server.service.IUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ConsumerServerApplicationTests {

    @Autowired
    IUserService userService;
    @Test
    void contextLoads() {

        //去调用方法
        userService.buyTicket();
    }

}
