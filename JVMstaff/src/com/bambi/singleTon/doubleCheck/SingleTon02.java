package com.bambi.singleTon.doubleCheck;

/**
 * 使用双重检查锁实现单例模式
 */
public class SingleTon02 {

    private static volatile SingleTon02 singleTon02;

    private SingleTon02() {
        System.out.println("这里写只想执行一次的代码");
    }

    public static SingleTon02 getInstance(){
        if(singleTon02==null){
            synchronized (SingleTon02.class){
                if(singleTon02==null){
                    singleTon02 = new SingleTon02();
                }
            }
        }
        return singleTon02;
    }

    public static void main(String[] args) {
        SingleTon02.getInstance();
    }
}
