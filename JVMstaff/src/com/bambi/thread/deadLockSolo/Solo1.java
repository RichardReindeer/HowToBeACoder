package com.bambi.thread.deadLockSolo;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class Solo1 {
    public static void main(String[] args) {
        /**
         * FutureTask
         *  异步运算的任务，FutureTask里面可以传入一个Callable的具体实现类，可以对这个异步任务运算的任务的结果进行等待获取、判断是福哦已经完成、取消任务等操作。
         *  只有当运算完成的时候结果才能返回，如果运算尚未完成，get方法将会阻塞，一个FutureTask对象可以对调用了Callable和Runnable的对象进行包装，由于FutureTask也是Runnable 接口的实现，
         *  所以FutureTask也可以放入线程池中
         */
        FutureTask<String> futureTask = new FutureTask<String>(new ClassTest());
        Thread thread = new Thread(futureTask);
        thread.start();

        try {
            String val = futureTask.get();
            System.out.println("val = " + val);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }


}
class ClassTest implements Callable<String>{
    @Override
    public String call() throws Exception {
        System.out.println(Thread.currentThread().getName()+"正在执行");
        return "这是一个返回值";
    }
}