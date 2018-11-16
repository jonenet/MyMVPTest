/*
 * Copyright 2017 JessYan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.dashuf.disp.mvp.presenter;

import android.app.Application;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.OnLifecycleEvent;
import android.support.v4.app.Fragment;
import android.support.v4.app.SupportActivity;
import android.support.v7.widget.RecyclerView;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseSimplePresenter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.rx_cache2.DynamicKey;
import io.rx_cache2.EvictDynamicKey;
import io.rx_cache2.Reply;

import com.dashuf.disp.global.AppConstant;
import com.dashuf.disp.mvp.api.cache.CommonCache;
import com.dashuf.disp.mvp.api.service.UserService;
import com.dashuf.disp.mvp.model.entity.User;
import com.dashuf.disp.mvp.views.test.IMoreView;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;


/**
 * ================================================
 * 展示 Presenter 的用法
 *
 * @see <a href="https://github.com/JessYanCoding/MVPArms/wiki#2.4.4">Presenter wiki 官方文档</a>
 * Created by JessYan on 09/04/2016 10:59
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * ================================================
 */
@ActivityScope
public class UserPresenter extends BaseSimplePresenter<IMoreView> {
    @Inject
    RxErrorHandler mErrorHandler;

    @Inject
    IRepositoryManager mRepositoryManager;
    @Inject
    Application mApplication;
    @Inject
    List<User> mUsers;
    @Inject
    RecyclerView.Adapter mAdapter;
    private int lastUserId = 1;
    private boolean isFirst = true;
    private int preEndIndex;


    @Inject
    public UserPresenter(AppComponent appComponent, IMoreView rootView) {
        super(appComponent, rootView);
    }

    /**
     * 使用 2017 Google IO 发布的 Architecture Components 中的 Lifecycles 的新特性 (此特性已被加入 Support library)
     * 使 {@code Presenter} 可以与 {@link SupportActivity} 和 {@link Fragment} 的部分生命周期绑定
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void onCreate() {
        System.out.println("justTest");
        requestUsers(true);//打开 App 时自动加载列表
    }

    public void requestUsers(final boolean pullToRefresh) {
        //请求外部存储权限用于适配android6.0的权限管理机制.rxPermission使用
//        PermissionUtil.externalStorage(new PermissionUtil.RequestPermission() {
//            @Override
//            public void onRequestPermissionSuccess() {
//                //request permission success, do something.
//            }
//
//            @Override
//            public void onRequestPermissionFailure(List<String> permissions) {
//                mRootView.showMessage("Request permissions failure");
//            }
//
//            @Override
//            public void onRequestPermissionFailureWithAskNeverAgain(List<String> permissions) {
//                mRootView.showMessage("Need to go to the settings");
//            }
//        }, mRootView.getRxPermissions(), mErrorHandler);


        if (pullToRefresh) lastUserId = 1;//下拉刷新默认只请求第一页

        //关于RxCache缓存库的使用请参考 http://www.jianshu.com/p/b58ef6b0624b

        boolean isEvictCache = pullToRefresh;//是否驱逐缓存,为ture即不使用缓存,每次下拉刷新即需要最新数据,则不使用缓存

        if (pullToRefresh && isFirst) {//默认在第一次下拉刷新时使用缓存
            isFirst = false;
            isEvictCache = false;
        }
        boolean finalIsEvictCache = isEvictCache;
        Observable<List<User>> listObservable = Observable.just(mRepositoryManager.obtainRetrofitService(UserService.class)
                .getUsers(lastUserId, AppConstant.USERS_PER_PAGE)
        )
                .flatMap(new Function<Observable<List<User>>, ObservableSource<List<User>>>() {
                    @Override
                    public ObservableSource<List<User>> apply(@NonNull Observable<List<User>> listObservable) throws Exception {
                        return mRepositoryManager.obtainCacheService(CommonCache.class)
                                .getUsers(listObservable
                                        , new DynamicKey(lastUserId)
                                        , new EvictDynamicKey(finalIsEvictCache))
                                .map(new Function<Reply<List<User>>, List<User>>() {
                                    @Override
                                    public List<User> apply(Reply<List<User>> listReply) throws Exception {
                                        return listReply.getData();
                                    }
                                });
                    }
                });
//        doBefore(mRootView, listObservable, disposable -> {
//            addDispose(disposable);
//            if (pullToRefresh)
//                mRootView.showLoading();//显示下拉刷新的进度条
//            else
//                mRootView.startLoadMore();//显示上拉加载更多的进度条
//        }, () -> {
//            if (pullToRefresh)
//                mRootView.hideLoading();//隐藏下拉刷新的进度条
//            else
//                mRootView.endLoadMore();//隐藏上拉加载更多的进度条
//        }).subscribe(new ErrorHandleSubscriber<List<User>>(mErrorHandler) {
//            @Override
//            public void onNext(List<User> users) {
//                lastUserId = users.get(users.size() - 1).getId();//记录最后一个id,用于下一次请求
//                if (pullToRefresh) mUsers.clear();//如果是下拉刷新则清空列表
//                preEndIndex = mUsers.size();//更新之前列表总长度,用于确定加载更多的起始位置
//                mUsers.addAll(users);
//                if (pullToRefresh)
//                    mAdapter.notifyDataSetChanged();
//                else
//                    mAdapter.notifyItemRangeInserted(preEndIndex, users.size());
//            }
//        });
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mAdapter = null;
        this.mUsers = null;
        this.mErrorHandler = null;
        this.mApplication = null;
    }
}
