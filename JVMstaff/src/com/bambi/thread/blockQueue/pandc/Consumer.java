package com.bambi.thread.blockQueue.pandc;

import java.io.Serializable;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 消费者类
 * @author Bambi
 *
 */
public class Consumer implements Runnable, Serializable {
    private BlockingQueue<String> blockingQueue;
    private static final int DEFAULT_NUM_FOR_RANDOM = 1000;

    public Consumer(BlockingQueue<String> blockingQueue) {
        this.blockingQueue = blockingQueue;
    }

    @Override
    public void run() {
        System.out.println("启动消费者线程");
        Random random = new Random();
        boolean isRunning = true;
        try {
            while (isRunning) {
                System.out.println("从队列中获取数据");
                //有数据的时候直接从队列的队首取走，无数据时候会发生阻塞
                String data = blockingQueue.poll(2, TimeUnit.SECONDS);
                if(null != data){
                    System.out.println("我拿到数据了！！！！ "+data);
                    System.out.println("正在使用这个数据哦 " +data);
                    Thread.sleep(random.nextInt(DEFAULT_NUM_FOR_RANDOM));
                }else {
                    //如果data = null , 则证明没有获取到数据，则认为生产者线程退出
                    isRunning = false;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }finally {
            System.out.println("退出消费者此案成");
        }
    }
}
