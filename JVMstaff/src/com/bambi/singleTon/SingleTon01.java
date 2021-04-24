package com.bambi.singleTon;

/*
    单例模式实现较多
    暂时共7种

    1.懒汉模式(线程不安全版)
 */
public class SingleTon01 {
    private static SingleTon01 instance;

    private SingleTon01() {
        System.out.println("私有化构造器");
    }

    public static SingleTon01 getInstance(){

        /**
         * 如果多个访问者同时去获取对象实例
         * 肯呢个会造成多个同样的实例并存，从而没有达到单例要求
         * instance可拆成3部分
         */

        if(instance==null){
            instance = new SingleTon01();
        }
        return instance;
    }
}
