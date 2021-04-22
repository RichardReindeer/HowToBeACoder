package com.bambi.thread.lock;

public class WaitNotify {

    public static void main(String[] args) {
        final Object A = new Object();
        final Object B = new Object();

        Thread thread1 = new Thread("t1-thread"){
            @Override
            public void run() {
                synchronized (A){
                    System.out.println(Thread.currentThread().getName()+"拿到CPU时间片,A的监视器锁");
                    System.out.println(Thread.currentThread().getName()+"尝试拿到B的锁");
                    try {
                        System.out.println("休眠1秒");
                        Thread.sleep(1000);
                        A.wait();//挂起自己
                        System.out.println("被唤醒，等待B的锁");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (B){
                        System.out.println(Thread.currentThread().getName()+"拿到B的监视器锁");
                        B.notify();
                    }
                }
            }
        };
        Thread thread2 = new Thread("t2-thread"){
            @Override
            public void run() {
                synchronized (B){
                    System.out.println(Thread.currentThread().getName()+"拿到B的锁");
                    System.out.println(Thread.currentThread().getName()+"尝试拿A的锁");
                    synchronized (A){
                        System.out.println(Thread.currentThread().getName()+"拿到了A的锁");
                        System.out.println("休眠两秒");
                        try {
                            Thread.sleep(2000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(Thread.currentThread().getName()+"挂起自己，释放A");
                        A.notify();
                    }
                    try {
                        System.out.println("休眠2秒");
                        Thread.sleep(2000);
                        System.out.println("挂起自己");
                        B.wait();
                        System.out.println("被唤醒");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        thread1.start();
        thread2.start();
    }
}
