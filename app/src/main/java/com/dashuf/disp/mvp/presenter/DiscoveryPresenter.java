package com.dashuf.disp.mvp.presenter;

import com.dashuf.disp.mvp.api.Api;
import com.dashuf.disp.mvp.api.service.UserService;
import com.dashuf.disp.mvp.model.entity.DiscoveryTopTabBean;
import com.dashuf.disp.mvp.model.entity.ResultBean;
import com.dashuf.disp.utils.RxTransfer;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.mvp.BaseSimplePresenter;
import com.jess.arms.mvp.IView;
import com.jess.arms.mvp.Message;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import me.jessyan.retrofiturlmanager.RetrofitUrlManager;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;

/**
 * Created by ex-zhoulai on 2018/5/31.
 */

public class DiscoveryPresenter extends BaseSimplePresenter<IView> {

    public DiscoveryPresenter(AppComponent appComponent, IView rootView) {
        super(appComponent, rootView);
    }


    public void requestDiscoveryPageList(Message message) {
        RetrofitUrlManager.getInstance().putDomain("dashboard", Api.APP_DASHBOARD);
        Map<String, String> params = getParams((Map<String, String>) message.obj);
        Observable<ResultBean<List<DiscoveryTopTabBean>>> listObservable = mAppComponent.repositoryManager().obtainRetrofitService(UserService.class).getDiscoveryPages(params);
//        doBefore(mRootView, listObservable)
        listObservable.compose(RxTransfer.doBefore(mRootView, this))
                .subscribe(new ErrorHandleSubscriber<ResultBean<List<DiscoveryTopTabBean>>>(mAppComponent.rxErrorHandler()) {
            @Override
            public void onNext(ResultBean<List<DiscoveryTopTabBean>> result) {
                if (result.isSuccess()) {
                    List<DiscoveryTopTabBean> resultData = result.getData();
                    message.what = Api.TAG_ONE;
                    message.obj = resultData;
                    message.handleMessageToTarget();
                } else {
                    mRootView.showMessage(result.getMessage());
                }
            }
        });
    }
}
