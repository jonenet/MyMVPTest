package com.jess.arms.rxtest;


/**
 * 作者:      周来
 * 包名:      com.jess.arms.rxtest
 * 工程名:    MyMVPTest
 * 时间:      2018/11/23
 * 说明:
 */
public class MapSubscriber<T, R> extends Subscriber<R> {

    final Subscriber<? super T> actual;
    final Observable.Transformer<? super R, ? extends T> transformer;

    public MapSubscriber(Subscriber<? super T> actual, Observable.Transformer<? super R, ? extends T> transformer) {
        this.actual = actual;
        this.transformer = transformer;
    }

    @Override
    public void onCompleted() {
        actual.onCompleted();
    }

    @Override
    public void onError(Throwable t) {
        actual.onError(t);
    }

    @Override
    public void onNext(R var1) {
        actual.onNext(transformer.call(var1));
    }
}
