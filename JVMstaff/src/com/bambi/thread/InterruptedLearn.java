package com.bambi.thread;

public class InterruptedLearn {
    public static void main(String[] args) {
        MyThread thread = new MyThread();
        thread.start();
    }
}

class MyThread extends Thread{
    int i = 1;

    @Override
    public void run() {
        while (true){
            System.out.println(i);
            System.out.println(this.isInterrupted());
            try {
                System.out.println("马上去sleep");
                Thread.sleep(2000);
                this.interrupt();
            } catch (InterruptedException e) {
                System.out.println("异常捕获:"+this.isInterrupted());
                return ;
            }
            i++;
        }

    }
}
