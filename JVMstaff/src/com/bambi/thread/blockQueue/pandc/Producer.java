package com.bambi.thread.blockQueue.pandc;

import java.io.Serializable;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 生产者线程
 *
 * @author Bambi
 */

public class Producer implements Runnable, Serializable {

    /**
     * 判断是否在运行
     */
    private static volatile boolean isRunning = true;

    /**
     * 创建阻塞队列
     */
    private BlockingQueue<String> queue;

    /**
     * 创建一个自动更新的值，原子类
     */
    private static AtomicInteger count = new AtomicInteger();

    /**
     * 默认的睡眠时间常量
     */
    private static final int DEFAULT_RANGE_FOR_SLEEP = 1000;

    //构造器
    public Producer(BlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        String data = null;
        Random random = new Random();
        System.out.println("开启生产者线程");
        try {
            while (isRunning) {
                System.out.println("正在生产数据");
                //在默认值中取一个随机数
                Thread.sleep(random.nextInt(DEFAULT_RANGE_FOR_SLEEP));
                //以原子方式将count自增1
                data = "data "+ count.incrementAndGet();
                System.out.println("将数据"+data+"放入队列");
                if(!queue.offer(data,2, TimeUnit.SECONDS)){
                    System.out.println("放入数据失败"+data);
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            //发生打断异常则打断
            Thread.currentThread().interrupt();
        } finally {
            System.out.println("退出生产者线程");
        }
    }

    /**
     * 需要手动关闭生产者类
     */
    public void stop(){
        isRunning = false;
    }
}
