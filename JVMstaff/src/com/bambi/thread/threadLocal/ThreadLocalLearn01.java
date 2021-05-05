package com.bambi.thread.threadLocal;

/**
 * 并发容器:
 * ThreadLocal
 */
public class ThreadLocalLearn01 {

    public static final ThreadLocal<Integer> THREAD_LOCAL = new ThreadLocal<Integer>(){
        //给ThreadLocalMap赋初值
        //通过get方法调用
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };

    public static void main(String[] args) {
        Thread thread1 = new MyThread01();
        Thread thread2 = new MyThread01();
        Thread thread3 = new MyThread01();
        thread1.start();
        thread2.start();
        thread3.start();
    }

}

class MyThread01 extends Thread{
    @Override
    public void run() {
        for(int i =0;i<5;i++){
            //获取ThreadLocal的值，因为底层是一个映射，可以通过get的方式获取value
            Integer n = ThreadLocalLearn01.THREAD_LOCAL.get();
            n++;
            //以及通过set方式赋值
            ThreadLocalLearn01.THREAD_LOCAL.set(n);
            System.out.println(Thread.currentThread().getName()+"正在对自己的ThreadLocalMap自增:"+ThreadLocalLearn01.THREAD_LOCAL.get());
            //避免内存泄漏，最好自己调用
            ThreadLocalLearn01.THREAD_LOCAL.remove();
        }
    }
}
