package com.bambi.thread;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class Cooperative {
    //测试如果线程没有获得，抛出异常时会yeild
    public static void main(String[] args) throws InterruptedException {
        String str = null;
        Mytest mytest = new Mytest(str);
        Mytest2 mytest2 = new Mytest2(str);
        Thread thread = new Thread(mytest);
        Thread thread1 = new Thread(mytest2);
        thread1.start();
        Thread.sleep(1000);
        thread.start();
    }
}

class Mytest implements Runnable{
    private String myValue;

    public Mytest(String myValue) {
        this.myValue = myValue;
    }

    @Override
    public void run() {
        try{
            Thread.sleep(1000);
            Integer size = myValue.length();
            System.out.println("size = " + size);
        }catch (NullPointerException e){
            System.out.println("未获取到，则跳出");
            Thread.yield();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}

class Mytest2 implements Runnable{
    private String str;

    public Mytest2(String str) {
        this.str = str;
    }

    @Override
    public void run() {
        str = "1";
        System.out.println("str = " + str);
        AtomicInteger atomicInteger;
        AtomicLong atomicLong;
    }
}