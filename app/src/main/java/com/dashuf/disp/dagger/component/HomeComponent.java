package com.dashuf.disp.dagger.component;

import com.dashuf.disp.dagger.module.HomeModule;
import com.dashuf.disp.mvp.views.home.HomeFragment6;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.di.scope.FragmentScope;

import dagger.Component;

/**
 * Created by ex-zhoulai on 2018/5/30.
 */
@FragmentScope
@Component(modules = HomeModule.class, dependencies = AppComponent.class)
public interface HomeComponent {
    void inject(HomeFragment6 homeFragment6);
}
