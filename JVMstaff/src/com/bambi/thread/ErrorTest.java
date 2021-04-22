package com.bambi.thread;

public class ErrorTest {
    public static void main(String[] args) {
        /**
         * 如果对已经启动的线程调用start方法，会出现illegalThreadStateException
         */
        Thread thread = new Thread(new ThreadNew());
        thread.start();
        thread.start();
        /**
         * Exception in thread "main" java.lang.IllegalThreadStateException
         * 	at java.base/java.lang.Thread.start(Thread.java:789)
         * 	at com.bambi.thread.ErrorTest.main(ErrorTest.java:10)
         */
    }
}

class ThreadNew implements Runnable{
    @Override
    public void run() {
        System.out.println("线程开始运行");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
