package com.bambi.thread;

/**
 *  可重入锁:
 *      自己可以再次获取自己的内部锁
 */
public class EnableToReuse {
    public static void main(String[] args) {
        Thread thread1 = new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (EnableToReuse.class){
                    System.out.println("等待");
                }
            }
        };
        Thread thread2 = new Thread(){
            @Override
            public void run() {
                synchronized (EnableToReuse.class){
                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (EnableToReuse.class){
                        System.out.println("可重入锁哦!!!");
                    }
                }
            }
        };

        thread1.start();
        thread2.start();
    }
}
