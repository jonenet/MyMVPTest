package com.dashuf.disp.utils;

import com.dashuf.disp.mvp.model.entity.ResultBean;
import com.jess.arms.mvp.BaseSimplePresenter;
import com.jess.arms.mvp.IView;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;

/**
 * 作者:      周来
 * 包名:      com.dashuf.disp.utils
 * 工程名:    MyMVPTest
 * 时间:      2018/11/16
 * 说明:
 */
public class RxTransfer {

    public static <T> ObservableTransformer<T, T> doBefore(IView iView, BaseSimplePresenter baseSimplePresenter) {
        return upstream -> upstream.subscribeOn(Schedulers.io())
                .retryWhen(new RetryWithDelay(3, 2))
                .doOnSubscribe(disposable -> {
                    baseSimplePresenter.addDispose(disposable);
                    iView.showLoading();
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(iView::hideLoading);
    }

}
