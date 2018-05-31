package com.dashuf.disp.mvp.presenter;

import com.dashuf.disp.mvp.api.Api;
import com.dashuf.disp.mvp.api.service.UserService;
import com.dashuf.disp.mvp.model.entity.HomeBean;
import com.dashuf.disp.mvp.model.entity.ResultBean;
import com.dashuf.disp.mvp.views.iview.IHomeView;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.FragmentScope;
import com.jess.arms.mvp.BaseSimplePresenter;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import me.jessyan.retrofiturlmanager.RetrofitUrlManager;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

/**
 * Created by ex-zhoulai on 2018/5/29.
 */

@FragmentScope
public class HomePresenter extends BaseSimplePresenter {

    @Inject
    public HomePresenter(AppComponent appComponent, IHomeView rootView) {
        super(appComponent, rootView);
    }


    public void requestHomeData(Map<String, String> message) {
        RetrofitUrlManager.getInstance().putDomain("dashboard", Api.APP_DASHBOARD);
        Map<String, String> params = getParams(message);
        Observable<ResultBean<HomeBean>> listObservable = mAppComponent.repositoryManager().obtainRetrofitService(UserService.class).getHomeData(params);
        IHomeView moreView = (IHomeView) this.mRootView;
        doBefore(this.mRootView, listObservable).subscribe(new ErrorHandleSubscriber<ResultBean<HomeBean>>(mAppComponent.rxErrorHandler()) {
            @Override
            public void onNext(ResultBean<HomeBean> result) {
                if (result.isSuccess()) {
                    HomeBean homeBean = result.getData();
                    moreView.onHomeResult(homeBean);
                } else {
                    HomePresenter.this.mRootView.showMessage(result.getMessage());
                }
            }
        });
    }
}
