package com.bambi.thread.customSynchronized;

import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * 使用独占方式的AQS自定义控制器类
 */
public class CustomSynExclusive01 extends AbstractQueuedSynchronizer {
    @Override
    protected boolean tryAcquire(int arg) {
        return super.tryAcquire(arg);
    }

    @Override
    protected boolean tryRelease(int arg) {
        return super.tryRelease(arg);
    }
}
