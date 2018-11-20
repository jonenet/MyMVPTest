package com.jess.arms;

import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;

/**
 * 作者:      周来
 * 包名:      com.jess.arms
 * 工程名:    MyMVPTest
 * 时间:      2018/11/15
 * 说明:
 */
public class SomeType {

    private String value;

    public void setValue(String value) {
        this.value = value;
    }

    public Observable<String> valueObservable() {
        return Observable.defer(new Callable<ObservableSource<? extends String>>() {
            @Override
            public ObservableSource<? extends String> call() throws Exception {
                return Observable.just(value);
            }
        });
    }
}
