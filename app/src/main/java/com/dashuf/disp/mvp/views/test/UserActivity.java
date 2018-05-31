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
package com.dashuf.disp.mvp.views.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jess.arms.base.App;
import com.jess.arms.base.BaseSimpleActivity;
import com.jess.arms.base.DefaultAdapter;
import com.jess.arms.mvp.Message;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.Preconditions;
import com.paginate.Paginate;

import java.util.ArrayList;

import butterknife.BindView;

import com.dashuf.disp.R;
import com.dashuf.disp.mvp.presenter.SimpleUserPresenter;

import timber.log.Timber;


/**
 * ================================================
 * 展示 View 的用法
 *
 * @see <a href="https://github.com/JessYanCoding/MVPArms/wiki#2.4.2">View wiki 官方文档</a>
 * Created by JessYan on 09/04/2016 10:59
 * <a href="mailto:jess.yan.effort@gmail.com">Contact me</a>
 * <a href="https://github.com/JessYanCoding">Follow me</a>
 * ================================================
 */
public class UserActivity extends BaseSimpleActivity<SimpleUserPresenter> implements IMoreView, SwipeRefreshLayout.OnRefreshListener {

    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    RecyclerView.LayoutManager mLayoutManager;
    UserAdapter mAdapter;

    private Paginate mPaginate;
    private boolean isLoadingMore;


    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_user;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
        initPaginate();
        mPresenter.requestUsers(this, Message.obtain(this, new Object[]{true}));//打开app时自动加载列表
    }



    @Override
    public void onRefresh() {
        mPresenter.requestUsers(this, Message.obtain(UserActivity.this, new Object[]{true}));
    }


    @Override
    public void showLoading() {
        Timber.tag(TAG).w("showLoading");
        mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        Timber.tag(TAG).w("hideLoading");
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void showMessage(@NonNull String message) {
        ArmsUtils.snackbarText(message);
    }

    @Override
    public void handleMessage(Message msg) {
        Preconditions.checkNotNull(msg);
        switch (msg.what) {
            case 0:
                isLoadingMore = true;//开始加载更多
                break;
            case 1:
                isLoadingMore = false;//结束加载更多
                break;
        }
    }

    /**
     * 开始加载更多
     */
    @Override
    public void startLoadMore() {
        isLoadingMore = true;
    }

    /**
     * 结束加载更多
     */
    @Override
    public void endLoadMore() {
        isLoadingMore = false;
    }

    @Override
    public Activity getActivity() {
        return this;
    }

//    @Override
//    public RxPermissions getRxPermissions() {
//        return mRxPermissions;
//    }

    /**
     * 初始化Paginate,用于加载更多
     */
    private void initPaginate() {
        if (mPaginate == null) {
            Paginate.Callbacks callbacks = new Paginate.Callbacks() {
                @Override
                public void onLoadMore() {
                    mPresenter.requestUsers(UserActivity.this, Message.obtain(UserActivity.this, new Object[]{false}));
                }

                @Override
                public boolean isLoading() {
                    return isLoadingMore;
                }

                @Override
                public boolean hasLoadedAllItems() {
                    return false;
                }
            };

            mPaginate = Paginate.with(mRecyclerView, callbacks)
                    .setLoadingTriggerThreshold(0)
                    .build();
            mPaginate.setHasMoreDataToLoad(false);
        }
    }

    @Override
    protected void onDestroy() {
        DefaultAdapter.releaseAllHolder(mRecyclerView);//super.onDestroy()之后会unbind,所有view被置为null,所以必须在之前调用
        super.onDestroy();
//        this.mRxPermissions = null;
        this.mPaginate = null;
    }

    @Nullable
    @Override
    public SimpleUserPresenter obtainPresenter() {
        mAdapter = new UserAdapter(new ArrayList<>());
        mLayoutManager = new GridLayoutManager(this, 2);
        return new SimpleUserPresenter(((App) getApplicationContext()).getAppComponent(), this, mAdapter);
    }


    @Override
    public void launchActivity(@NonNull Intent intent) {
        ArmsUtils.startActivity(intent);
    }

    @Override
    public void killMyself() {

    }
}
