package com.bambi.thread.threadPoolExecutors;

import java.util.concurrent.*;

public class ThreadPoolLearn02 {
    static BlockingQueue blockingQueue;
    static ThreadPoolExecutor executor = new ThreadPoolExecutor(10,10,1, TimeUnit.MINUTES,blockingQueue);

    public static void main(String[] args) {
        Future<String> future = executor.submit(()->{
            Thread.sleep(1000);
            return "这是一个返回值";
        });
        try {
            String sqy = future.get();
            System.out.println(sqy);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
