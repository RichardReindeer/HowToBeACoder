package com.bambi.jvmCode;

/**
 * native : 凡是带有native关键字的，说明java的作用范围达不到了，需要去调用底层的其他代码数据库(C)
 * 会进入本地方法栈
 * 调用本地方法本地接口 JNI
 * JNI 的作用是: 扩展java的使用，融合不同的编程语言，
 * 它在jvm中开辟了一块标记区域，native Method stack 等级 native方法
 * 最终执行的时候，加载本地方法库中的方法是需要通过JNI的
 */
public class NativeDemo {
    public static void main(String[] args) {
        new Thread(()->{

        }).start();
    }
}
