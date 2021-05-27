package com.bambi.thread.countDownLatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 相当于一个倒叙计数器，用来协调多个线程的执行
 * 多个线程通过他们所共享的计数器的countDown()方法来让计数器减一，哦那个过CountDownLatch对象的await方法来阻塞当前线程
 */
public class CountDownLatchDemo01 {

    public static void main(String[] args) {
        int count = 200;

        //初始化计数器
        CountDownLatch countDownLatch = new CountDownLatch(count);

        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for(int i =0;i<count;i++){
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println(countDownLatch.getCount());
                    } finally {
                        //计数器减1
                        System.out.println("计数器减一");
                        countDownLatch.countDown();
                    }
                }
            });
        }
        //阻塞当前线程直到计数器减为0
        try {
            countDownLatch.await(10L, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
        System.out.println(countDownLatch.getCount());
    }

}
