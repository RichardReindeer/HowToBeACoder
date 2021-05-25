package com.bambi.thread.deadLockDemo;

import java.util.concurrent.TimeUnit;

/**
 * 死锁测试
 * 死锁产生的条件
 * <p>
 * 1.互斥 只线程在某段时间内独占资源
 * 2.请求保持 在线程请求一个资源而阻塞时，不会释放自己手头的资源
 * 3.不剥夺，在自己独占资源时不会有其他线程强行剥夺资源
 * 4.循环等待，多线程之间形成一种头尾相连的循环等待
 */
public class DeadLockTest02 {

    private static final Object object1 = new Object();
    private static final Object object2 = new Object();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            synchronized (object1) {
                try {
                    TimeUnit.SECONDS.sleep(2);
                    synchronized (object2) {
                        System.out.println("我没有死锁哦");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        });
        Thread t2 = new Thread(() -> {
            synchronized (object2) {
                try {
                    TimeUnit.SECONDS.sleep(2);
                    synchronized (object1) {
                        System.out.println("我没有死锁");
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
