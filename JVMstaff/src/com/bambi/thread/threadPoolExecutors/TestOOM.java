package com.bambi.thread.threadPoolExecutors;

import java.util.HashMap;
import java.util.Map;

/**
 * 测试大量创建线程引发内存溢出
 */
public class TestOOM {
    public static void main(String[] args) {
        Map<Integer,Thread> threadMap = new HashMap<>();
        Thread thread;
        for(int i =0;;i++){
            threadMap.put(i,new Thread());
            threadMap.get(i).start();
            System.out.println("创建一个线程:"+threadMap.get(i).getName());
        }
    }
}
