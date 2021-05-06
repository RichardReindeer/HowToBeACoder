package com.bambi.thread.reentrantLock;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockLearn02 {
    static ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        Thread thread1 = new Thread(){
            @Override
            public void run() {
                lock.lock();
                System.out.println("测试lock锁的机制");
                lock.unlock();
            }
        };
        Thread thread2 = new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock.lock();
                System.out.println("如果不解锁的话会锁定吗");
            }
        };
        ReentrantLockLearn02 reentrantLockLearn02 = new ReentrantLockLearn02();
        thread1.start();
        //如果lock对象不主动执行unlock解锁，则锁一直在
        thread2.start();
    }

}
