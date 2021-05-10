package com.bambi.singleTon.doubleCheck;


import java.io.Serializable;

public class SingleTon03 implements Serializable {
    private static volatile SingleTon03 singleTon03;

    private SingleTon03() {
        System.out.println("测试双重检查所单例模式");
    }

    public static SingleTon03 getInstance(){
        if(singleTon03==null){
            synchronized (SingleTon03.class){
                if(singleTon03==null){
                    singleTon03 = new SingleTon03();
                }
            }
        }
        return singleTon03;
    }


    public static void main(String[] args) {
        SingleTon03.getInstance();
    }
}
