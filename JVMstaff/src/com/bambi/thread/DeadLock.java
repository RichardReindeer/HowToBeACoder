package com.bambi.thread;

/**
 * 有关死锁的代码
 *
 * 代码大概:
 *      线程1持有1资源 ， 线程2持有2资源，线程1想访问2资源，线程2想访问1资源
 */
public class DeadLock {
    private static Object resource1 = new Object();
    private static Object resource2 = new Object();
    public static void main(String[] args) {
        Thread thread = new Thread(()->{
           synchronized (resource1){
               System.out.println(Thread.currentThread()+",已经获取线程1");
               try {
                   Thread.sleep(1000);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
               System.out.println("开始尝试获取资源2");
               synchronized (resource2){
                   System.out.println("已经成功获取线程:"+Thread.currentThread());
               }
           }
        });
        Thread thread1 = new Thread(()->{
            synchronized (resource2) {
                System.out.println("获取资源2:" + Thread.currentThread());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("正在尝试获取资源1");
                synchronized (resource1){
                    System.out.println("获取到资源1");
                }
            }
        });
        thread.start();
        thread1.start();
    }
}
