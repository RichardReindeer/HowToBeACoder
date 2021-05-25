package com.bambi.thread.deadLockDemo;

import java.util.concurrent.TimeUnit;

/**
 * 写一个死锁
 *
 * 死锁产生的条件
 * 互斥
 * 请求保持
 * 不剥夺
 * 循环等待
 */
public class DeadLockTest01 {

    private static Object object1 = new Object();
    private static Object object2 = new Object();

    public static void main(String[] args) {

        Thread t1 = new Thread(()->{
           synchronized (object1){
               try {
                   TimeUnit.SECONDS.sleep(2);
                   synchronized (object2){
                       System.out.println("我竟然没有死锁");
                   }
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
        });
        Thread t2 = new Thread(()->{
            synchronized (object2){
                try {
                    TimeUnit.SECONDS.sleep(2);
                    synchronized (object1){
                        System.out.println("我竟然没死锁");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();
    }
}