package com.bambi.thread.cas;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 原子自增运算
 *
 * @author Bambi
 */
public class AtomicInstance {
    public static AtomicInteger integer = new AtomicInteger(0);

    public static void instance(){
        //就是个自增
        integer.incrementAndGet();
    }

    //设计一个静态常量，来进行计数
    private static final int THREADS_COUNT=20;

    public static void main(String[] args) {
        Thread[] threads = new Thread[THREADS_COUNT];
        for(int i =0;i<THREADS_COUNT;i++){
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int i =0 ; i<1000;i++){
                        instance();
                    }
                }
            });
            threads[i].start();
        }

        while (Thread.activeCount()>1){
            Thread.yield();
            System.out.println(integer);
        }
    }
}
