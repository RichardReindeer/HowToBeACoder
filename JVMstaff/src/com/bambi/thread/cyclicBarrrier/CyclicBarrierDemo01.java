package com.bambi.thread.cyclicBarrrier;

import java.util.HashMap;
import java.util.concurrent.*;
import java.util.logging.Logger;

/**
 * java.until.concurrent.CyclicBarrier 时一个可重用的线程阻塞器，通过调用其await()方法在代码中形成
 * 栅栏，率先执行到栅栏处的线程会被阻塞，直到指定数量的线程到达栅栏处
 * 其构造函数的参数用于指定要阻塞多少个线程
 * barrierAction参数则用于指定当条件满足时要执行的内容
 */
public class CyclicBarrierDemo01 implements Runnable{

    //指定到达的线程数
    private static final CyclicBarrier CYCLIC_BARRIER = new CyclicBarrier(5);

    private int page;

    public CyclicBarrierDemo01(int page) {
        this.page = page;
    }


    @Override
    public void run() {
        for(int i =0;i<2;i++){
            //模拟不同的处理耗时，最慢的一个线程需要5秒
            try {
                Thread.sleep(1000L+page*1000L);
                System.out.println("等待线程数量:"+CYCLIC_BARRIER.getNumberWaiting());
                CYCLIC_BARRIER.await(10L, TimeUnit.SECONDS);
                System.out.println("Continue.....");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for(int i =0;i<5;i++){
            CyclicBarrierDemo01 cyclicBarrierDemo01 = new CyclicBarrierDemo01(i);
            executorService.execute(cyclicBarrierDemo01);
        }

        executorService.shutdown();
    }
}
