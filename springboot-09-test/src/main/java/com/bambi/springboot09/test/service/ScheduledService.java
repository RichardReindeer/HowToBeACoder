package com.bambi.springboot09.test.service;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service

public class ScheduledService {

    //让此方法在固定的时间执行此代码
    //cron表达式
    //秒 分 时 日 月 周几

    /**
     * 30 0/5 10,18 * * ?  每天10点和18点，每隔五分钟执行一次
     *
     */
    @Scheduled(cron = "0 58 16 22 5 0-7")
    public void hello(){
        System.out.println("你被执行了哦~~~~");
    }
}
