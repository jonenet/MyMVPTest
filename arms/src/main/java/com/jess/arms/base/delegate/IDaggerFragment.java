package com.jess.arms.base.delegate;

import android.support.annotation.NonNull;

import com.jess.arms.base.delegate.IFragment;
import com.jess.arms.di.component.AppComponent;

/**
 * Created by ex-zhoulai on 2018/5/28.
 */

public interface IDaggerFragment extends IFragment {

    /**
     * 提供 AppComponent (提供所有的单例对象) 给实现类, 进行 Component 依赖
     *
     * @param appComponent
     */
    void setupFragmentComponent(@NonNull AppComponent appComponent);
}
