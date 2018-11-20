package com.jess.arms.utils.rxFun;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * 作者:      周来
 * 包名:      com.jess.arms.utils.rxFun
 * 工程名:    MyMVPTest
 * 时间:      2018/11/13
 * 说明:
 */
public final class OnSubscribeMap<T, R> implements Observable.OnSubscribe<R> {

    final Observable<T> source;
    final Func1<? super T, ? extends R> transformer;

    public OnSubscribeMap(Observable<T> source, Func1<? super T, ? extends R> transformer) {
        this.source = source;
        this.transformer = transformer;
    }

    @Override
    public void call(final Subscriber<? super R> o) {
        Subscriber<T> parent = new Subscriber<T>() {
            @Override
            public void onNext(T t) {
                // 注意看这里，调用了外部的转换函数后得到一个新值，回调给原始的观察者
                R mapper = transformer.call(t);
                o.onNext(mapper);
            }

            @Override
            public void onCompleted() {
                o.onCompleted();
            }

            @Override
            public void onError(Throwable e) {
                o.onError(e);
            }
        };
        //最后，用原始的Observable对象订阅新的观察者
        source.unsafeSubscribe(parent);
    }
}