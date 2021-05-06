package com.bambi.thread.threadPoolExecutors;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolLearn {
    static BlockingQueue blockingQueue;
    static ThreadPoolExecutor executor = new ThreadPoolExecutor(10,10,1, TimeUnit.MINUTES,blockingQueue);

    public static void main(String[] args) {

        /**
         * execute方法没有返回值，利用这种方法提交的任务没有办法得知是否正常执行
         *
         */
        executor.execute(()->{
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}
