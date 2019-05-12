package com.dashuf.disp.mvp.views.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dashuf.disp.R;
import com.jess.arms.base.BaseSimpleFragment;
import com.jess.arms.mvp.IPresenter;
import com.jess.arms.mvp.Message;

/**
 * Created by ex-zhoulai on 2018/5/29.
 */

public class MineFragment extends BaseSimpleFragment {
    @Override
    public void handleMessage(Message msg) {

    }

    @Nullable
    @Override
    public IPresenter obtainPresenter() {
        return null;
    }



    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(mContext).inflate(R.layout.fragment_discovery, container, false);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    public void setData(@Nullable Object data) {

    }
}
