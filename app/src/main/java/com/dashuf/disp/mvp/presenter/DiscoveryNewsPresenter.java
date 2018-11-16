package com.dashuf.disp.mvp.presenter;

import android.util.Base64;

import com.dashuf.disp.mvp.api.Api;
import com.dashuf.disp.mvp.api.service.UserService;
import com.dashuf.disp.mvp.model.entity.DiscoveryNewsBean;
import com.dashuf.disp.mvp.model.entity.LoginBean;
import com.dashuf.disp.mvp.model.entity.ResultBean;
import com.dashuf.disp.utils.RxTransfer;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.mvp.BaseSimplePresenter;
import com.dashuf.disp.mvp.views.iview.IDiscoveryNewsView;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import me.jessyan.retrofiturlmanager.RetrofitUrlManager;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import timber.log.Timber;


@ActivityScope
public class DiscoveryNewsPresenter extends BaseSimplePresenter<IDiscoveryNewsView> {

    @Inject
    public DiscoveryNewsPresenter(AppComponent appComponent, IDiscoveryNewsView rootView) {
        super(appComponent, rootView);
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void requestPageDetail(Map<String, String> params) {
        RetrofitUrlManager.getInstance().putDomain("dashboard", Api.APP_DASHBOARD);
        Observable<ResultBean<DiscoveryNewsBean>> listObservable = mAppComponent.repositoryManager().obtainRetrofitService(UserService.class).getNewsPageDetail(getParams(params));
//        doBefore(mRootView, listObservable)
        listObservable.compose(RxTransfer.doBefore(mRootView, this))
                .subscribe(new ErrorHandleSubscriber<ResultBean<DiscoveryNewsBean>>(mAppComponent.rxErrorHandler()) {
            @Override
            public void onNext(ResultBean<DiscoveryNewsBean> result) {
                if (result.isSuccess()) {
                    mRootView.onPageDetailResult(result.getData());
                } else {
                    mRootView.showMessage(result.getMessage());
                }
            }
        });
    }
}
