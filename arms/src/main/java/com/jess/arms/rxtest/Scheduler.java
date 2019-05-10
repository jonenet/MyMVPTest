package com.jess.arms.rxtest;

import java.util.concurrent.Executor;

/**
 * 作者:      周来
 * 包名:      com.jess.arms.rxtest
 * 工程名:    MyMVPTest
 * 时间:      2018/11/23
 * 说明:
 */
public class Scheduler {
    final Executor executor;

    public Scheduler(Executor executor) {
        this.executor = executor;
    }

    public Worker createWorker() {
        return new Worker(executor);
    }

    public static class Worker {
        final Executor executor;

        public Worker(Executor executor) {
            this.executor = executor;
        }

        public void schedule(Runnable runnable) {
            executor.execute(runnable);
        }
    }
}
