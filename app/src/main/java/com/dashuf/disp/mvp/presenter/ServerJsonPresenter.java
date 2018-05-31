package com.dashuf.disp.mvp.presenter;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.mvp.BaseSimplePresenter;
import com.jess.arms.mvp.IView;

import java.util.List;

import io.reactivex.Observable;
import com.dashuf.disp.mvp.api.Api;
import com.dashuf.disp.mvp.api.service.UserService;
import com.dashuf.disp.mvp.model.entity.PostTestBean;
import me.jessyan.retrofiturlmanager.RetrofitUrlManager;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import timber.log.Timber;

public class ServerJsonPresenter extends BaseSimplePresenter<IView> {

    private AppComponent appComponent;

    public ServerJsonPresenter(AppComponent appComponent, IView rootView) {
        super(rootView);
        this.appComponent = appComponent;
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onDestroy() {

    }

    public void start() {
        RetrofitUrlManager.getInstance().putDomain("douban", Api.APP_SERVER_JSON);
        Observable<List<PostTestBean>> listObservable = appComponent.repositoryManager().obtainRetrofitService(UserService.class).postTest();
        doBefore(mRootView, listObservable, disposable -> {
            addDispose(disposable);
            Timber.tag(TAG).i("start");
        }, () -> {
            Timber.tag(TAG).i("end");
        }).subscribe(new ErrorHandleSubscriber<List<PostTestBean>>(appComponent.rxErrorHandler()) {
            @Override
            public void onNext(List<PostTestBean> result) {
                Timber.tag(TAG).i("result = " + result);
            }
        });
    }
}
