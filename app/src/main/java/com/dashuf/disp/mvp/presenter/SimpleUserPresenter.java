package com.dashuf.disp.mvp.presenter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.integration.IRepositoryManager;
import com.jess.arms.mvp.BaseSimplePresenter;
import com.jess.arms.mvp.IView;
import com.jess.arms.mvp.Message;
import com.jess.arms.utils.PermissionUtil;
import com.jess.arms.utils.RxLifecycleUtils;
import com.dashuf.disp.global.AppConstant;
import com.dashuf.disp.mvp.model.entity.User;
import com.dashuf.disp.mvp.views.test.UserAdapter;
import com.tbruyelle.rxpermissions2.RxPermissions;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import io.rx_cache2.DynamicKey;
import io.rx_cache2.EvictDynamicKey;
import io.rx_cache2.Reply;

import com.dashuf.disp.mvp.api.cache.CommonCache;
import com.dashuf.disp.mvp.api.service.UserService;
import com.dashuf.disp.mvp.views.test.IMoreView;

import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;

/**
 * Created by ex-zhoulai on 2018/5/25.
 * SimpleUserPresenter
 */

public class SimpleUserPresenter extends BaseSimplePresenter<IMoreView> {


    private int preEndIndex;
    private int lastUserId = 1;
    private boolean isFirst = true;
    private RxErrorHandler mErrorHandler;
    private RecyclerView.Adapter mAdapter;
    private List<User> mUsers;
    private RxPermissions mRxPermissions;


    public SimpleUserPresenter(AppComponent appComponent, IMoreView rootView, UserAdapter adapter) {
        super(appComponent, rootView);
        mErrorHandler = appComponent.rxErrorHandler();
        mRxPermissions = new RxPermissions((Activity) rootView);
        this.mAdapter = adapter;
        mUsers = adapter.getInfos();
    }


    public void requestUsers(IView iView, Message message) {

        PermissionUtil.externalStorage(new PermissionUtil.RequestPermission() {
            @Override
            public void onRequestPermissionSuccess() {
                //request permission success, do something.
            }

            @Override
            public void onRequestPermissionFailure(List<String> permissions) {
                mRootView.showMessage("Request permissions failure");
            }

            @Override
            public void onRequestPermissionFailureWithAskNeverAgain(List<String> permissions) {
                mRootView.showMessage("Need to go to the settings");
            }
        }, mRxPermissions, mErrorHandler);

        final boolean pullToRefresh = (boolean) message.objs[0];
        if (pullToRefresh) lastUserId = 1;//下拉刷新默认只请求第一页

        //关于RxCache缓存库的使用请参考 http://www.jianshu.com/p/b58ef6b0624b

        boolean isEvictCache = pullToRefresh;//是否驱逐缓存,为ture即不使用缓存,每次下拉刷新即需要最新数据,则不使用缓存

        if (pullToRefresh && isFirst) {//默认在第一次下拉刷新时使用缓存
            isFirst = false;
            isEvictCache = false;
        }
        IRepositoryManager iRepositoryManager = mAppComponent.repositoryManager();
        boolean finalIsEvictCache = isEvictCache;
        Observable<List<User>> listObservable = Observable.just(iRepositoryManager.obtainRetrofitService(UserService.class)
                .getUsers(lastUserId, AppConstant.USERS_PER_PAGE)
        ).flatMap((Function<Observable<List<User>>, ObservableSource<List<User>>>) listObservable1 -> iRepositoryManager.obtainCacheService(CommonCache.class)
                .getUsers(listObservable1
                        , new DynamicKey(lastUserId)
                        , new EvictDynamicKey(finalIsEvictCache))
                .map(Reply::getData));
        listObservable
                .subscribeOn(Schedulers.io())
                .retryWhen(new RetryWithDelay(3, 2))//遇到错误时重试,第一个参数为重试几次,第二个参数为重试的间隔
                .doOnSubscribe(disposable -> {
                    addDispose(disposable);//在订阅时必须调用这个方法,不然Activity退出时可能内存泄漏
                    if (pullToRefresh)
                        iView.showLoading();//显示下拉刷新的进度条
                    else {
                        //显示上拉加载更多的进度条
                        message.what = 0;
                        message.handleMessageToTargetUnrecycle();
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doFinally(() -> {
                    if (pullToRefresh) {
                        iView.hideLoading();//隐藏下拉刷新的进度条
                        //因为hideLoading,为默认方法,直接可以调用所以不需要发送消息给handleMessage()来处理,
                        //HandleMessageToTarget()的原理就是发送消息并回收消息
                        //调用默认方法后不需要调用HandleMessageToTarget(),但是如果后面对view没有其他操作了请调用message.recycle()回收消息
                        message.recycle();
                    } else {
                        //隐藏上拉加载更多的进度条
                        message.what = 1;
                        message.handleMessageToTarget();//方法最后必须调HandleMessageToTarget,将消息所有引用清空后回收进消息池
                    }
                })
                .compose(RxLifecycleUtils.bindToLifecycle(mRootView))//使用 Rxlifecycle,使 Disposable 和 Activity 一起销毁
                .subscribe(new ErrorHandleSubscriber<List<User>>(mErrorHandler) {
                    @Override
                    public void onNext(List<User> users) {
                        lastUserId = users.get(users.size() - 1).getId();//记录最后一个id,用于下一次请求
                        if (pullToRefresh) mUsers.clear();//如果是下拉刷新则清空列表
                        preEndIndex = mUsers.size();//更新之前列表总长度,用于确定加载更多的起始位置
                        mUsers.addAll(users);
                        if (pullToRefresh)
                            mAdapter.notifyDataSetChanged();
                        else
                            mAdapter.notifyItemRangeInserted(preEndIndex, users.size());
                    }
                });
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mAdapter = null;
        this.mUsers = null;
        this.mErrorHandler = null;
    }

}
