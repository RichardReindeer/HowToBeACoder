package com.bambi.thread;

/**
 * Volatile的用法:
 *    volatile只可以用来修饰变量，不能修饰方法以及类
 */
public class VolatileLearn {
    private static volatile VolatileLearn volatileLearn;

    private VolatileLearn() {
    }
    public static VolatileLearn getInstance(){
        if (volatileLearn==null){
            synchronized (VolatileLearn.class){
                if (volatileLearn==null){
                    volatileLearn = new VolatileLearn();
                }
            }
        }
        return volatileLearn;
    }

    public static void main(String[] args) {
        VolatileLearn volatileLearn = VolatileLearn.getInstance();
        System.out.println("volatileLearn = " + volatileLearn);
    }
}
