package com.dashuf.disp.dagger.module;

import com.jess.arms.di.scope.ActivityScope;

import com.dashuf.disp.mvp.views.iview.IDiscoveryNewsView;

import dagger.Module;
import dagger.Provides;


@Module
public class DiscoveryNewsModule {
    private IDiscoveryNewsView view;

    /**
     * 构建DiscoveryNewsModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public DiscoveryNewsModule(IDiscoveryNewsView view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    IDiscoveryNewsView provideDiscoveryNewsView() {
        return this.view;
    }

}