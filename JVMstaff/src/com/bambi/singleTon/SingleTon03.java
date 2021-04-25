package com.bambi.singleTon;

/**
 * 饿汉模式，线程安全
 * 一开始就创建占用内存
 *
 */
public class SingleTon03 {

    /**
     * 因为静态变量在类加载的时候创建，所以不存在线程安全问题
     */
    private static SingleTon03 instance = new SingleTon03();

    private SingleTon03() {
    }

    public static SingleTon03 getInstance(){
        return instance;
    }
}
