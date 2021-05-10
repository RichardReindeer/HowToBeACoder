package com.bambi.thread.blockQueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class LinkedBlockingQLearn01 {
    static BlockingQueue blockingQueue = new LinkedBlockingQueue();

    public static void main(String[] args) {
        for(int i = 0;;i++){
            System.out.println("开始往里面添加    "+i);
            blockingQueue.offer(1);
        }

    }
}
