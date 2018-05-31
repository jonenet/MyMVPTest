package com.dashuf.disp.dagger.component;

import dagger.Component;

import com.jess.arms.di.component.AppComponent;

import com.dashuf.disp.dagger.module.DiscoveryNewsModule;

import com.jess.arms.di.scope.ActivityScope;
import com.dashuf.disp.mvp.views.discovery.discoveryhome.DiscoveryNewsFragment;

@ActivityScope
@Component(modules = DiscoveryNewsModule.class, dependencies = AppComponent.class)
public interface DiscoveryNewsComponent {
    void inject(DiscoveryNewsFragment fragment);
}