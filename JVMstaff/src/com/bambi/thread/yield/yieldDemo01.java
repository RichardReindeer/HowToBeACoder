package com.bambi.thread.yield;

/**
 * 测试线程的yield方法
 *
 *  yield方法会让当前线程让出当前CPU时间片，让其他线程执行，再次拿到时间片的时间不确定
 */
public class yieldDemo01 {
    public static void main(String[] args) {
        Thread t1 = new Thread(new Mythread());
        t1.start();
    }
}

class Mythread implements Runnable{

    @Override
    public void run() {
        //获取当前毫秒数
        long startTime = System.currentTimeMillis();
        int count = 0;
        for (int i = 0;i<500000;i++){
            Thread.yield();
            count = count+(i+1);
        }
        long end = System.currentTimeMillis()-startTime;
        System.out.println("用时:" + end);
    }
}