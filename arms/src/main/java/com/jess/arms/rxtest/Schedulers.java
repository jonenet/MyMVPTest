package com.jess.arms.rxtest;

import java.util.concurrent.Executors;

/**
 * 作者:      周来
 * 包名:      com.jess.arms.rxtest
 * 工程名:    MyMVPTest
 * 时间:      2018/11/23
 * 说明:
 */
public class Schedulers {
    private static final Scheduler ioScheduler = new Scheduler(Executors.newSingleThreadExecutor());

    public static Scheduler io() {
        return ioScheduler;
    }
}
