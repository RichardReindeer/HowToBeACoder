package com.bambi.singleTon;

/**
 * 第二种单例模式
 *  懒汉模式(线程安全)
 */
public class SingleTon2 {
    private static SingleTon2 instance;

    private SingleTon2() {
        System.out.println("私有化构造器");
    }

    /**
     *
     * @return
     */
    public static synchronized SingleTon2 getInstance(){
        if(instance==null) {
            instance = new SingleTon2();
        }
        return instance;
    }
}
