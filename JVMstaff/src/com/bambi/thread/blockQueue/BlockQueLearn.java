package com.bambi.thread.blockQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class BlockQueLearn {
    static BlockingQueue blockingQueue = new LinkedBlockingQueue(1);
    static BlockingQueue array = new ArrayBlockingQueue(1);

    public static void main(String[] args) {
        blockingQueue.offer("这是什么呢");
        try {
            System.out.println(blockingQueue.size()+" 过段时间会自动销毁吗");
            blockingQueue.poll();
            //等待timeout个时间，如果时间到了还没有加进去，就往下走
            blockingQueue.offer("等待一会，看看能不能挤进去?",10000, TimeUnit.MILLISECONDS);
            System.out.println("都能添加成功那可太能了");
            System.out.println(blockingQueue.size());
            System.out.println(blockingQueue.take());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
