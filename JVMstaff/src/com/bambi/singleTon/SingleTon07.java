package com.bambi.singleTon;

/**
 * 使用枚举类实现单例(线程安全)
 * 解决了:
 * 线程安全、自由串行化、单一实例
 */
public enum SingleTon07 {
    INSTANCE;
    public void test(){
        System.out.println("???????"+INSTANCE);
    }
}
