package com.bambi.thread.customSynchronized;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * 使用共享方式AQS的自定义同步器类
 */
public class CustomSynShare01 extends AbstractQueuedSynchronizer {
    @Override
    protected int tryAcquireShared(int arg) {
        return super.tryAcquireShared(arg);
    }

    @Override
    protected boolean tryReleaseShared(int arg) {
        return super.tryReleaseShared(arg);
    }
}
