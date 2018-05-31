package com.jess.arms.base.delegate;

import android.support.annotation.Nullable;

import com.jess.arms.mvp.IPresenter;

/**
 * Created by ex-zhoulai on 2018/5/28.
 */

public interface ISimpleFragment<P extends IPresenter> extends IFragment {
    /**
     * 此类是onCreate的时候创建的，优先于activity的onCreate执行，所有初始化的操作尽量在这里执行
     */
    @Nullable
    P obtainPresenter();

    void setPresenter(@Nullable P presenter);

}

