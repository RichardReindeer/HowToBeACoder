package com.bambi.thread.lock;

/**
 * synchronized和wait()调用的对象应为同一个对象！！！
 */
public class WaitAndSynchronized {
    private Object lock = new Object();
    private Object notify = new Object();
    public synchronized static void function01(){
        //因为给静态方法加锁相当于给类加锁
        try {
            WaitAndSynchronized.class.wait();
            System.out.println("synchronized和wait()调用的对象应为同一个对象");
            WaitAndSynchronized.class.notify();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void function02(){
        synchronized (lock){
            try {
                System.out.println("让这个线程挂起");
                lock.wait();
                System.out.println("它被唤醒了");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        synchronized (notify){
            notify.notifyAll();
            System.out.println("唤醒所有线程");
        }
    }

    public static void main(String[] args) {
        WaitAndSynchronized waitAndSynchronized = new WaitAndSynchronized();
        waitAndSynchronized.function02();
    }
}
