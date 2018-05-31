package com.dashuf.disp.mvp.views.test;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.TextView;

import com.jess.arms.base.App;
import com.jess.arms.base.BaseSimpleActivity;
import com.jess.arms.mvp.IView;
import com.jess.arms.mvp.Message;

import butterknife.BindView;
import butterknife.OnClick;
import com.dashuf.disp.R;
import com.dashuf.disp.mvp.presenter.ServerJsonPresenter;

public class ServerJsonActivity extends BaseSimpleActivity<ServerJsonPresenter> implements IView {

    @BindView(R.id.tv_result)
    TextView tvResult;


    @OnClick(R.id.btn_001)
    public void onViewClicked() {
        mPresenter.start();
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_server_json;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void handleMessage(Message msg) {

    }

    @Nullable
    @Override
    public ServerJsonPresenter obtainPresenter() {
        return new ServerJsonPresenter(((App) getApplicationContext()).getAppComponent(), this);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(@NonNull String message) {

    }

    @Override
    public void launchActivity(@NonNull Intent intent) {

    }

    @Override
    public void killMyself() {

    }

    @Override
    public Activity getActivity() {
        return this;
    }
}
