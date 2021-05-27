package com.bambi.thread.countDownLatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CountDownLatchDemo02 {
    public static void main(String[] args) {
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Thread thread = new Thread(new MyThread(countDownLatch));
        thread.start();
    }
}

class MyThread implements Runnable{

    private CountDownLatch countDownLatch ;

    public MyThread(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            System.out.println("我正在执行");
            countDownLatch.countDown();
            System.out.println("我还能继续走吗");
            countDownLatch.await(15L, TimeUnit.SECONDS);
            System.out.println("能");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
