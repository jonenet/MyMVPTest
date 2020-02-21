package com.dashuf.disp.utils;

/**
 * 作者:      周来
 * 包名:      com.dashuf.disp.utils
 * 工程名:    MyMVPTest
 * 时间:      2018/11/16
 * 说明:
 */
public class RxTransfer {

//    public static <T> ObservableTransformer<T, T> doBefore(IView iView, BaseSimplePresenter baseSimplePresenter) {
//        return upstream -> upstream.subscribeOn(Schedulers.io())
//                .retryWhen(new RetryWithDelay(3, 2))
//                .compose(RxLifecycleUtils.bindToLifecycle(iView))
//                .doOnSubscribe(disposable -> {
//                    baseSimplePresenter.addDispose(disposable);
//                    iView.showLoading();
//                })
//                .compose(RxLifecycleUtils.bindUntilEvent(iView, ActivityEvent.DESTROY))
//                .observeOn(AndroidSchedulers.mainThread())
//                .doFinally(iView::hideLoading);
//    }

}
