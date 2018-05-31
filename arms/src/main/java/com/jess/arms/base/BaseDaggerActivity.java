package com.jess.arms.base;

import android.support.annotation.Nullable;

import com.jess.arms.base.delegate.IDaggerActivity;
import com.jess.arms.mvp.IPresenter;

import javax.inject.Inject;


/**
 * Created by ex-zhoulai on 2018/5/25.
 */

public abstract class BaseDaggerActivity<P extends IPresenter> extends BaseActivity implements IDaggerActivity {


    @Inject
    @Nullable
    protected P mPresenter;//如果当前页面逻辑简单, Presenter 可以为 null


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null)
            mPresenter.onDestroy();//释放资源
        this.mPresenter = null;
    }

}
