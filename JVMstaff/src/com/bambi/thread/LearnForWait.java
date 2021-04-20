package com.bambi.thread;

import javax.management.monitor.Monitor;

/**
 * 你是如何调用wait()方法的？
 * --->if块还是循环?
 * --->为什么
 *
 * 处于等待状态的线程可能会收到错误警报和伪唤醒，
 * 如果不在循环中检查等待条件，程序就会在没有满足结束条件的情况下推出
 */
public class LearnForWait {
    /**
     * wait（）方法应该在循环调用，因为当线程获得到cpu开始执行的时候，其他条件可能还没有满足
     * 所以在处理前，循环检测条件是否满足会更好。
     */
    public static void main(String[] args) {
        Monitor monitor = new Monitor() {
            @Override
            public void start() {

            }

            @Override
            public void stop() {

            }
        };
    }
}
