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
package com.jess.arms.mvp;

import android.app.Activity;
import android.app.Service;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.OnLifecycleEvent;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.support.v4.app.Fragment;
import android.support.v4.app.SupportActivity;
import android.view.View;

import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.DeviceUtils;
import com.jess.arms.utils.Preconditions;
import com.jess.arms.utils.RxLifecycleUtils;
import com.jess.arms.utils.cipher.RSAEncryptor;
import com.trello.rxlifecycle2.RxLifecycle;

import org.json.JSONException;
import org.json.JSONObject;
import org.simple.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;

/**
 * ================================================
 * 基类 Presenter
 *
 * @see <a href="https://github.com/JessYanCoding/MVPArms/wiki#2.4.4">Presenter wiki 官方文档</a>
 * Created by JessYan on 4/28/2016
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * ================================================
 */
public class BaseSimplePresenter<V extends IView> implements IPresenter, LifecycleObserver {
    protected final String TAG = this.getClass().getSimpleName();
    protected CompositeDisposable mCompositeDisposable;

    protected V mRootView;
    protected AppComponent mAppComponent;

    /**
     * 如果当前页面同时需要 Model 层和 View 层,则使用此构造函数(默认)
     *
     * @param appComponent
     * @param rootView
     */
    public BaseSimplePresenter(AppComponent appComponent, V rootView) {
        Preconditions.checkNotNull(appComponent, "%s cannot be null", IModel.class.getName());
        Preconditions.checkNotNull(rootView, "%s cannot be null", IView.class.getName());
        this.mAppComponent = appComponent;
        this.mRootView = rootView;
        onStart();
    }

    /**
     * 如果当前页面不需要操作数据,只需要 View 层,则使用此构造函数
     *
     * @param rootView
     */
    public BaseSimplePresenter(V rootView) {
        Preconditions.checkNotNull(rootView, "%s cannot be null", IView.class.getName());
        this.mRootView = rootView;
        onStart();
    }

    public BaseSimplePresenter() {
        onStart();
    }


    public String mapToJson(Map<String, String> params) {
        JSONObject jo = new JSONObject();
        if (null != params) {
            Set<String> keys = params.keySet();
            for (String key : keys) {
                try {
                    jo.put(key, params.get(key));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return mAppComponent.gson().toJson(jo);
    }

    @Override
    public void onStart() {
        //将 LifecycleObserver 注册给 LifecycleOwner 后 @OnLifecycleEvent 才可以正常使用
        if (mRootView != null && mRootView instanceof LifecycleOwner) {
            ((LifecycleOwner) mRootView).getLifecycle().addObserver(this);
//            if (mModel != null && mModel instanceof LifecycleObserver) {
//                ((LifecycleOwner) mRootView).getLifecycle().addObserver((LifecycleObserver) mModel);
//            }
        }
        if (useEventBus())//如果要使用 Eventbus 请将此方法返回 true
            EventBus.getDefault().register(this);//注册 Eventbus
    }

    /**
     * 在框架中 {@link Activity#onDestroy()} 时会默认调用 {@link IPresenter#onDestroy()}
     */
    @Override
    public void onDestroy() {
        if (useEventBus())//如果要使用 Eventbus 请将此方法返回 true
            EventBus.getDefault().unregister(this);//解除注册 Eventbus
        unDispose();//解除订阅
        this.mRootView = null;
        this.mCompositeDisposable = null;
        this.mAppComponent = null;
    }

    /**
     * 只有当 {@code mRootView} 不为 null, 并且 {@code mRootView} 实现了 {@link LifecycleOwner} 时, 此方法才会被调用
     * 所以当您想在 {@link Service} 以及一些自定义 {@link View} 或自定义类中使用 {@code Presenter} 时
     * 您也将不能继续使用 {@link OnLifecycleEvent} 绑定生命周期
     *
     * @param owner link {@link SupportActivity} and {@link Fragment}
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void onDestroy(LifecycleOwner owner) {
        /**
         * 注意, 如果在这里调用了 {@link #onDestroy()} 方法, 会出现某些地方引用 {@code mModel} 或 {@code mRootView} 为 null 的情况
         * 比如在 {@link RxLifecycle} 终止 {@link Observable} 时, 在 {@link io.reactivex.Observable#doFinally(Action)} 中却引用了 {@code mRootView} 做一些释放资源的操作, 此时会空指针
         * 或者如果你声明了多个 @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY) 时在其他 @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
         * 中引用了 {@code mModel} 或 {@code mRootView} 也可能会出现此情况
         */
        owner.getLifecycle().removeObserver(this);

    }

    /**
     * 是否使用 {@link EventBus},默认为使用(true)，
     *
     * @return
     */
    public boolean useEventBus() {
        return true;
    }


    /**
     * 将 {@link Disposable} 添加到 {@link CompositeDisposable} 中统一管理
     * 可在 {@link Activity#onDestroy()} 中使用 {@link #unDispose()} 停止正在执行的 RxJava 任务,避免内存泄漏
     * 目前框架已使用 {@link RxLifecycle} 避免内存泄漏,此方法作为备用方案
     *
     * @param disposable
     */
    public void addDispose(Disposable disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);//将所有 Disposable 放入集中处理
    }

    /**
     * 停止集合中正在执行的 RxJava 任务
     */
    public void unDispose() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();//保证 Activity 结束时取消所有正在执行的订阅
        }
    }

//    /**
//     * @param rootView   视图
//     * @param observable 请求
//     * @param consumer   请求前
//     * @param fina       请求后
//     */
//    protected <T> Observable<T> doBefore(IView rootView, Observable<T> observable, Consumer<? super Disposable> consumer, Action fina) {
//        return observable.subscribeOn(Schedulers.io())
////                .retryWhen(new RetryWithDelay(3, 2))
//                .doOnSubscribe(consumer)
//                .observeOn(AndroidSchedulers.mainThread())
//                .doFinally(fina)
//                .compose(RxLifecycleUtils.bindToLifecycle(rootView));//使用 Rxlifecycle,使 Disposable 和 Activity 一起销毁
//    }

//    protected <T> Observable<T> doBefore(IView rootView, Observable<T> observable) {
//        return observable.subscribeOn(Schedulers.io())
//                .retryWhen(new RetryWithDelay(3, 2))
//                .doOnSubscribe(disposable -> {
//                    addDispose(disposable);
//                    rootView.showLoading();
//                })
//                .subscribeOn(AndroidSchedulers.mainThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .doFinally(new Action() {
//                    @Override
//                    public void run() throws Exception {
//                        rootView.hideLoading();
//                    }
//                })
//                .compose(RxLifecycleUtils.bindToLifecycle(rootView));//使用 Rxlifecycle,使 Disposable 和 Activity 一起销毁
//    }


    public Map<String, String> getParams(Map<String, String> params) {
        if (null != params) {
            RSAEncryptor rsaEncryptor;
            try {
                rsaEncryptor = new RSAEncryptor();
                params.put("timeset", rsaEncryptor.encryptWithBase64("" + System.currentTimeMillis()));
            } catch (Exception e) {
                e.printStackTrace();
            }
            params.put("cv", DeviceUtils.getVersionCode(mAppComponent.application()) + "");
            params.put("jsonFlag", "Y");
            params.put("channelType", "02");

            PackageInfo pinfo = DeviceUtils.getPackageInfo(mAppComponent.application());

            String osVersion = "Android " + android.os.Build.VERSION.RELEASE;
            String appVersion = pinfo.versionName + "/" + pinfo.versionCode;
            String device = Build.MANUFACTURER + " " + android.os.Build.MODEL;
            String deviceId = "";
            try {
                deviceId = DeviceUtils.getIMEI(mAppComponent.application());
            } catch (Exception e) {
                e.printStackTrace();
            }
            params.put("osVersion", osVersion);
            params.put("appVersion", appVersion);
            params.put("device", device);

            params.put("deviceId", "867305036892201");
            params.put("province", "广东省");
            params.put("city", "深圳市");
//            params.put("cityId", UserSettingProvider.provider().getUserLocationCityId());
//            params.put("province", UserSettingProvider.provider().getUserLocationProvice());
//            params.put("city", UserSettingProvider.provider().getUserLocationCity());
        } else {
            return new HashMap<>();
        }
        return params;
    }

}
