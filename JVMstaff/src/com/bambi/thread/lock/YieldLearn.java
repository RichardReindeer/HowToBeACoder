package com.bambi.thread.lock;

public class YieldLearn {
    public static void main(String[] args) {
        new MyThread("低级",1).start();
        new MyThread("中级",5).start();
        new MyThread("高级",10).start();
    }
}
class MyThread extends Thread{
    @Override
    public void run() {
        for(int i = 0;i<30;i++){
            System.out.println(this.getName()+"线程第:"+i+"次执行");
            if(i%5==0){
                System.out.println(this.getName()+"线程调用yield方法");
                Thread.yield();
            }
        }
    }

    public MyThread(String name , int pro) {
        super(name);//设置一个线程的名字
        this.setPriority(pro);//设置优先级的方法!!!!!!!
    }
}
