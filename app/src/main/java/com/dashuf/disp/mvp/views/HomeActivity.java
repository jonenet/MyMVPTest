package com.dashuf.disp.mvp.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.dashuf.disp.R;
import com.jess.arms.base.BaseSimpleActivity;
import com.jess.arms.mvp.IPresenter;
import com.jess.arms.mvp.Message;
import com.jess.arms.utils.StatusBarUtil;

import butterknife.BindView;

/**
 * Created by ex-zhoulai on 2018/5/29.
 */

public class HomeActivity extends BaseSimpleActivity {

    @BindView(R.id.fragment_container)
    FrameLayout fragmentContainer;
    @BindView(R.id.activity_main)
    RelativeLayout activityMain;


    @Override
    public void handleMessage(Message msg) {

    }

    @Nullable
    @Override
    public IPresenter obtainPresenter() {
        return null;
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_home;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        StatusBarUtil.setTranslucentForImageView(this, R.color.transparent);
    }

    @Override
    public void killMyself() {
        finish();
    }




}
