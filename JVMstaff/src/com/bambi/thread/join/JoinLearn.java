package com.bambi.thread.join;

public class JoinLearn {
    public static void main(String[] args) {

        Thread thread = new Thread(new MyThread());
        thread.start();
        try {
            thread.join(0,1);
            for(int i =0;i<10;i++){
                System.out.println("主线线程开始执行:"+i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class MyThread implements Runnable{
    @Override
    public void run() {
        System.out.println("这是一个子线程");
        System.out.println("子线程开始休息");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("子线程又开始执行了");
    }
}
