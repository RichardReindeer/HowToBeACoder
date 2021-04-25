package com.bambi.singleTon;

/**
 * 线程安全4
 * 使用类的内部类(线程安全)
 *
 * 使用静态内部类实现单例模式，即保证了线程安全又保证了懒汉式加载，同时不会因为加锁的方式消耗性能
 * 不用加锁
 * 不用饿汉
 */
public class SingleTon04 {

    /**
     * JVM虚拟机可以保证多线程并发访问的正确性，
     * 即一个类的构造方法在多线程环境下可以被正确的加载
     */
    private static class SingletonHolder{
        private static SingleTon04 instance = new SingleTon04();
    }

    private SingleTon04() {

    }

    public static SingleTon04 getInstance(){
        return SingletonHolder.instance;
    }
}
