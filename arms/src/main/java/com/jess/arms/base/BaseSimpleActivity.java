package com.jess.arms.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.jess.arms.base.delegate.IHandler;
import com.jess.arms.base.delegate.ISimpleActivity;
import com.jess.arms.mvp.IPresenter;

/**
 * Created by ex-zhoulai on 2018/5/25.
 * 简单的activity
 */

public abstract class BaseSimpleActivity<P extends IPresenter> extends BaseActivity implements ISimpleActivity<P>, IHandler {


    protected P mPresenter;

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (mPresenter == null) mPresenter = obtainPresenter();
    }


    @Override
    public void setPresenter(@Nullable P presenter) {
        this.mPresenter = presenter;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null)
            mPresenter.onDestroy();//释放资源
        this.mPresenter = null;
    }
}
