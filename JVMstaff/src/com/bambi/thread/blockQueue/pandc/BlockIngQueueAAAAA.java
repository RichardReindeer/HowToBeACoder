package com.bambi.thread.blockQueue.pandc;

import java.util.concurrent.*;

/**
 * 运行生产者和消费者的类
 * @author Bambi
 */
public class BlockIngQueueAAAAA {

    public static void main(String[] args) throws InterruptedException {
        //声明一个队列
        BlockingQueue<String> blockingQueue = new LinkedBlockingQueue<>(10);
        //创建三个生产一个消费者
        Producer producer1 = new Producer(blockingQueue);
        Producer producer2 = new Producer(blockingQueue);
        Producer producer3 = new Producer(blockingQueue);
        Consumer consumer = new Consumer(blockingQueue);

        //创建线程池
        ExecutorService service = Executors.newCachedThreadPool();
        //启动线程
        /*service.execute(producer1);
        service.execute(producer2);
        service.execute(producer3);
        service.execute(consumer);*/
        service.submit(producer1);
        service.submit(producer2);
        service.submit(producer3);
        service.submit(consumer);

        //执行10s
        Thread.sleep(10*1000);
        producer1.stop();
        producer2.stop();
        producer3.stop();

        Thread.sleep(2000);
        service.shutdown();
    }
}
