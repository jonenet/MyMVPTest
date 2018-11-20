package com.jess.arms;

import com.jess.arms.utils.RxUtils;
import com.jess.arms.utils.rxFun.MapFun;
import com.jess.arms.utils.rxFun.MyOperator;

import io.reactivex.Observable;
import io.reactivex.ObservableOperator;
import io.reactivex.Observer;
import io.reactivex.functions.Consumer;
import rx.Subscriber;

/**
 * 作者:      周来
 * 包名:      com.jess.arms
 * 工程名:    MyMVPTest
 * 时间:      2018/11/8
 * 说明:
 */
public class Test {

    public static void main(String[] args) {
//        RxUtils.test(new RxUtils.IUiTask() {
//            @Override
//            public void runOnUiThread() {
//                System.out.println(Thread.currentThread().getName());
//            }
//        });
//        RxUtils.testDelay();
        RxUtils.testMerge();
//         Observable.just(0).compose(RxUtils.<Integer, String>testCompose()).subscribe(new Consumer<String>() {
//             @Override
//             public void accept(String s) throws Exception {
//                 System.out.println(s);
//             }
//         });


    }

}
