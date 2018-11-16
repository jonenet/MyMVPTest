package com.dashuf.disp.mvp.presenter;

import android.util.Base64;

import com.dashuf.disp.mvp.api.Api;
import com.dashuf.disp.mvp.api.service.UserService;
import com.dashuf.disp.mvp.model.entity.LoginBean;
import com.dashuf.disp.mvp.model.entity.ResultBean;
import com.dashuf.disp.utils.RxTransfer;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.mvp.BaseSimplePresenter;
import com.jess.arms.mvp.IView;
import com.jess.arms.mvp.Message;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.ObservableTransformer;
import me.jessyan.retrofiturlmanager.RetrofitUrlManager;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import timber.log.Timber;

/**
 * Created by ex-zhoulai on 2018/5/28.
 */

public class LoginPrePresenter extends BaseSimplePresenter<IView> {

    public LoginPrePresenter(AppComponent appComponent, IView rootView) {
        super(appComponent, rootView);
    }


    public void doLogin(Message message) {
        RetrofitUrlManager.getInstance().putDomain("dashboard", Api.APP_DASHBOARD);
        Map<String, String> params = getParams((Map<String, String>) message.obj);
        Observable<ResultBean<LoginBean>> listObservable = mAppComponent.repositoryManager().obtainRetrofitService(UserService.class).doLogin(params);
        Timber.i("TIME_START = " + System.currentTimeMillis());

        listObservable.compose(RxTransfer.doBefore(mRootView, this))
                .subscribe(new ErrorHandleSubscriber<ResultBean<LoginBean>>(mAppComponent.rxErrorHandler()) {
                    @Override
                    public void onNext(ResultBean<LoginBean> result) {
                        Timber.i("TIME_END = " + System.currentTimeMillis());
                        if (result.isSuccess()) {
                            LoginBean loginBean = result.getData();
                            String token = loginBean.getToken();
                            if (null != token) {
                                mAppComponent.extras().put("token", Base64.encodeToString(token.getBytes(), Base64.DEFAULT).replace("\n", ""));
                            }
                            message.what = 1;
                            message.handleMessageToTarget();
                        } else {
                            message.what = 1;
                            message.handleMessageToTarget();
                            if (null != mRootView) {
                                mRootView.showMessage(result.getMessage());
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable t) {
                        super.onError(t);
                        Timber.i("result = %s", "onError");
                    }
                });


    }


}
