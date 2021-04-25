package com.bambi.singleTon;

/**
 * 双重检查锁!!!!!
 *
 * 方法锁的优化，减少了部分获取实例的耗时
 * 满足了懒汉式加载
 */
public class SingleTon05 {
    private static volatile SingleTon05 instance;

    private SingleTon05() {

    }

    public static SingleTon05 getInstance(){
        if(instance==null){
            //把这个类锁起来，因为是静态的
            synchronized (SingleTon05.class){
                if(instance==null){
                    instance = new SingleTon05();
                }
            }
        }
        return instance;
    }
}
