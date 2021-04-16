package com.bambi.thread;

/**
 * 重排序
 */
public class VolatileTest {
    int a = 1;
    boolean status = false;

    //状态切换为true
    public void changeStatus(){
        a = 2;
        status = true;
    }

    //若状态为true，则为running
    public void run(){
        if(status){
            int b = a+1;
            System.out.println("b = " + b);
        }
    }
}
