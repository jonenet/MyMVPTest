package com.jess.arms.utils.rxFun;

import rx.Subscriber;
import rx.functions.Func1;

/**
 * 作者:      周来
 * 包名:      com.jess.arms.utils.rxFun
 * 工程名:    MyMVPTest
 * 时间:      2018/11/13
 * 说明:
 */
public interface Operator<R, T> extends Func1<Subscriber<? super R>, Subscriber<? super T>> {
    Subscriber<R> call(Subscriber<T> subscriber);
}