package com.bambi.singleTon;

import java.util.concurrent.atomic.AtomicReference;

/**
 * CAS [AtomicReference]
 * 线程安全
 *
 * 乐观锁
 * 使用AtomicReference 创建常量引用，作为CAS的V
 *
 */
public class SingleTon06 {
    // 因为CAS 的 V值底层是使用的volatile修饰
    /**
     * volatile的特点就是不能保证原子性
     * 而想要保证原子性，可以使用JAVA提供的原子类来保证并发访问的原子性
     * AtomicInteger AtomicBoolean AtomicLong AtomicReference
     */
   private static final AtomicReference<SingleTon06> INSTANCE = new AtomicReference<SingleTon06>();

    private SingleTon06() {
    }

    private static SingleTon06 instance;


    /**
     * 被final修饰的方法 不能扩展，不允许进行重写
     * final修饰类无法被继承
     * 将所有变量设为私有
     * 没有set方法
     * 通过构造器初始化所有成员，进行深拷贝
     * get方法调用时，克隆对象，返回对象的拷贝
     * @return
     */
    public static final SingleTon06 getInstance(){

        /**
         * 乐观锁的缺点:
         * 忙等
         * 如果一直没有获取到值(锁） 就会进入死循环去等待
         */
        for(;;){
            instance = INSTANCE.get();
            if(instance!=null) return instance;
            INSTANCE.compareAndSet(null,new SingleTon06());
            return INSTANCE.get();
        }
    }
}
