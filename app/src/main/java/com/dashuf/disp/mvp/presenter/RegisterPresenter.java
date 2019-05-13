package com.dashuf.disp.mvp.presenter;

import android.util.Base64;

import com.dashuf.disp.mvp.api.service.UserService;
import com.dashuf.disp.mvp.model.entity.RegisterBean;
import com.dashuf.disp.mvp.model.entity.ResultBean;
import com.dashuf.disp.utils.RxTransfer;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.mvp.BaseSimplePresenter;
import com.jess.arms.mvp.IView;
import com.jess.arms.mvp.Message;

import java.util.Map;

import io.reactivex.Observable;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import timber.log.Timber;


public class RegisterPresenter extends BaseSimplePresenter<IView> {


    public RegisterPresenter(AppComponent appComponent, IView rootView) {
        super(appComponent, rootView);
    }

    public void register(Message message) {
        Observable<ResultBean<RegisterBean>> listObservable = mAppComponent.repositoryManager().obtainRetrofitService(UserService.class).doRegister(getParams((Map<String, String>) message.obj));
        listObservable.compose(RxTransfer.doBefore(mRootView, this))
                .subscribe(new ErrorHandleSubscriber<ResultBean<RegisterBean>>(mAppComponent.rxErrorHandler()) {
                    @Override
                    public void onNext(ResultBean<RegisterBean> result) {
                        if (result.getErrorCode() == 0) {
                            RegisterBean loginBean = result.getData();
                            String token = loginBean.getToken();
                            if (null != token) {
                                mAppComponent.extras().put("token", Base64.encodeToString(token.getBytes(), Base64.DEFAULT).replace("\n", ""));
                            }
                            message.what = 1;
                            message.handleMessageToTarget();
                        } else {
                            if (null != mRootView) {
                                mRootView.showMessage(result.getErrorMsg());
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        Timber.i(TAG, "register_onError_msg = %s", t.getMessage());
                    }
                });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}