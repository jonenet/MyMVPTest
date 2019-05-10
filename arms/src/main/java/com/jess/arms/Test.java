package com.jess.arms;

import com.jess.arms.rxtest.Observable;
import com.jess.arms.rxtest.Schedulers;
import com.jess.arms.rxtest.Subscriber;



/**
 * 作者:      周来
 * 包名:      com.jess.arms
 * 工程名:    MyMVPTest
 * 时间:      2018/11/8
 * 说明:
 */
public class Test {

    public static void main(String[] args) {

        Observable.create(new Observable.OnSubscribe<Integer>() {
            @Override
            public void call(Subscriber<? super Integer> subscriber) {
                subscriber.onNext(1);
            }
        }).map(new Observable.Transformer<Integer, String>() {
            @Override
            public String call(Integer from) {
                return String.valueOf(from);
            }
        }).subscribeOn(Schedulers.io()).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onNext(String var1) {
                System.out.println(Thread.currentThread().getName() + "result = " + var1);
            }
        });

//        Flowable.just(4, 5, 6)
//                .startWith(Flowable.just(1, 2, 3))
//                .startWith(0)
//                .subscribe(ele -> Log.i("tag", String.valueOf(ele)));


//        RxUtils.test(new RxUtils.IUiTask() {
//            @Override
//            public void runOnUiThread() {
//                System.out.println(Thread.currentThread().getName());
//            }
//        });
//        RxUtils.testDelay();
//        RxUtils.testMerge();
//         Observable.just(0).compose(RxUtils.<Integer, String>testCompose()).subscribe(new Consumer<String>() {
//             @Override
//             public void accept(String s) throws Exception {
//                 System.out.println(s);
//             }
//         });


    }

}
