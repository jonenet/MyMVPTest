package com.dashuf.disp.mvp.presenter;


import com.dashuf.disp.mvp.api.Api;
import com.dashuf.disp.mvp.api.service.UserService;
import com.dashuf.disp.mvp.model.entity.ResultBean;
import com.dashuf.disp.utils.RxTransfer;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.FragmentScope;
import com.jess.arms.mvp.BaseSimplePresenter;
import com.jess.arms.mvp.IView;
import com.jess.arms.mvp.Message;

import java.util.Map;

import javax.inject.Inject;

import io.reactivex.Observable;
import me.jessyan.retrofiturlmanager.RetrofitUrlManager;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import timber.log.Timber;


public class BaseRecyclerPresenter extends BaseSimplePresenter<IView> {


    public BaseRecyclerPresenter(AppComponent appComponent, IView rootView) {
        super(appComponent, rootView);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public void getDiscoveryItemList(Message message) {
        RetrofitUrlManager.getInstance().putDomain("dashboard", Api.APP_DASHBOARD);
        Map<String, String> params = getParams((Map<String, String>) message.obj);
        Observable<String> listObservable = mAppComponent.repositoryManager().obtainRetrofitService(UserService.class).getRecyclerList(params);
        listObservable.compose(RxTransfer.doBefore(mRootView, this))
//        doBefore(mRootView, listObservable)
                .subscribe(new ErrorHandleSubscriber<String>(mAppComponent.rxErrorHandler()) {
            @Override
            public void onNext(String result) {
                message.what = Api.TAG_ONE;
                message.obj = result;
                message.handleMessageToTarget();

            }
        });
    }


}
