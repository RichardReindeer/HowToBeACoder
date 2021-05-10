package com.bambi.thread.threadPoolExecutors;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 阿里java编程规范中，
 * 要求使用ThreadPoolExecutor来创建线程池
 */
public class ThreadPoolExeLearn01 {
    static BlockingDeque let = new LinkedBlockingDeque(10);
    /**
     * 构造方法中的参数意义:
     *
     *  corePoolSize(线程池的基本大小):即使其他空闲的基本线程能够执行任务也会创建线程，
     *  等到需要执行的任务数大于线程池基本大小时便不再创建
     *      如果调用了prestartAllCoreThreads()方法，线程会体现创建并启动所有基本线程
     *
     *  workQueue(任务队列):用于保存等待执行的任务的阻塞队列
     *
     *  maximumPoolSize(线程池最大数量) : 线程池允许创建的最大线程数，
     *  如果队列已满，且已经创建的线程数小于最大线程数，则线程池会创建新的线程执行任务
     *  如果使用无界的任务队列则参数无用
     *
     *  threadFactory(线程工厂) 可以通过线程工厂为每个创建出来的线程设置更有意义的名字
     *
     *  RejectedExecutionHandler(饱和策略):当队列和线程池都满了，说明线程池处于饱和状态，
     *  则必须采用一种策略来处理新提交的任务。
     *      AbortPolicy:直接抛出异常，默认情况下采用这种
     *      CallerRunsPolicy:只用调用者所在线程来执行任务
     *      DiscardOldestPolicy:丢弃队列里最近的一个任务，并执行当前任务
     *      DiscardPolicy:不处理，丢弃掉
     *   更多时候，通过实现RejectExecutionHandler接口来自定义策略，比如记录日志，或持久化存储等
     *
     *   keepAliveTime(线程活动时间) 线程池的工作线程空闲后，保持存活的时间
     *
     *   TimeUnit(线程活动时间的单位):可选的单位有days hours minutes milliseconds microseconds nanoseconds
     *
     */
    static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10,20,3000, TimeUnit.MINUTES, let);
}
