package com.bambi.singleTon.doubleCheck;

public class SingleTon01 {
    private volatile static SingleTon01 instance;

    private SingleTon01() {
    }

    public static SingleTon01 getInstance(){
        if(instance==null){ //<-------当instance不为null时，仍可能指向一个”被部分初始化的对象"
            synchronized (SingleTon01.class){
                if(instance==null){
                    instance = new SingleTon01();
                }
            }
        }
        return instance;
    }
}
