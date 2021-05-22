package com.bambi.springboot09.test.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * 异步任务
 */
@Service
public class AsyncService {

    //想办法告诉Spring 这是一个异步方法
    @Async
    public void hello(){
        try {
            Thread.sleep(3000);
            System.out.println("数据正在处理");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
