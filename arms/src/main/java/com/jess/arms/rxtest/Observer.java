package com.jess.arms.rxtest;

/**
 * 作者:      周来
 * 包名:      com.jess.arms.rxtest
 * 工程名:    MyMVPTest
 * 时间:      2018/11/23
 * 说明:
 */
public interface Observer<T> {

    void onCompleted();

    void onError(Throwable t);

    void onNext(T var1);
}
