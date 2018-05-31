package com.dashuf.disp.dagger.module;

import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.dashuf.disp.R;
import com.dashuf.disp.dagger.anno.AdapterQualifier;
import com.dashuf.disp.mvp.model.entity.HomeBean;
import com.dashuf.disp.mvp.model.entity.HomeNotificationBean;
import com.dashuf.disp.mvp.model.entity.HomeTabBean;
import com.dashuf.disp.mvp.model.entity.User;
import com.dashuf.disp.mvp.views.home.HomeAdvAdapter;
import com.dashuf.disp.mvp.views.home.HomeBannerAdapter6;
import com.dashuf.disp.mvp.views.home.HomeEasyAdapter;
import com.dashuf.disp.mvp.views.home.HomeNewRecommendAdapter;
import com.dashuf.disp.mvp.views.home.HomeTabAdapter;
import com.dashuf.disp.mvp.views.iview.IHomeView;
import com.dashuf.disp.mvp.views.test.IMoreView;
import com.dashuf.disp.mvp.views.test.UserAdapter;
import com.jess.arms.di.scope.ActivityScope;
import com.jess.arms.di.scope.FragmentScope;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Qualifier;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ex-zhoulai on 2018/5/30.
 */

@Module
public class HomeModule {


    private static final int[] imgRes = new int[]{
            R.drawable.home_shortcut_progress,
            R.drawable.home_shortcut_help,
            R.drawable.home_shortcut_brand,
            R.drawable.home_shortcut_team
    };
    private static final String[] textRes = new String[]{"进度查询", "智能客服", "品牌专区", "组建团队"};


    private IHomeView view;

    /**
     * 构建 UserModule 时,将 View 的实现类传进来,这样就可以提供 View 的实现类给 Presenter
     */
    public HomeModule(IHomeView view) {
        this.view = view;
    }

    @FragmentScope
    @Provides
    IHomeView provideHomeView() {
        return this.view;
    }


    @FragmentScope
    @Provides
    RecyclerView.RecycledViewPool provideRecyclerViewPool() {
        return new RecyclerView.RecycledViewPool();
    }

    @FragmentScope
    @Provides
    VirtualLayoutManager provideVirtualLayoutManager() {
        return new VirtualLayoutManager(view.getActivity());
    }


    @FragmentScope
    @Provides
    DelegateAdapter provideDelegateAdapter(VirtualLayoutManager virtualLayoutManager) {
        return new DelegateAdapter(virtualLayoutManager, false);
    }


    @FragmentScope
    @Provides
    LinkedList<DelegateAdapter.Adapter> provideDelegateAdapterLinkedList() {
        return new LinkedList<>();
    }


    @FragmentScope
    @Provides
    List<HomeBean.BannerBean> provideListBannerBean() {
        return new ArrayList<>();
    }


    @FragmentScope
    @Provides
    List<HomeBean.NewProductBean> provideListNewProductBean() {
        return new ArrayList<>();
    }


    @FragmentScope
    @Provides
    List<HomeBean.EasyProductBean> provideListEasyProductBean() {
        return new ArrayList<>();
    }

    @AdapterQualifier("bannerAdapter")
    @FragmentScope
    @Provides
    DelegateAdapter.Adapter provideHomeBannerAdapter6(List<HomeBean.BannerBean> beanList, RecyclerView.RecycledViewPool viewPool) {
        return new HomeBannerAdapter6(view.getActivity(), beanList, viewPool);
    }

    @AdapterQualifier("tabAdapter")
    @FragmentScope
    @Provides
    DelegateAdapter.Adapter provideHomeTabAdapter(List<HomeTabBean> beanList) {
        return new HomeTabAdapter(view.getActivity(), beanList);
    }

    @AdapterQualifier("easyAdapter")
    @FragmentScope
    @Provides
    DelegateAdapter.Adapter provideHomeEasyAdapter(List<HomeBean.EasyProductBean> beanList) {
        return new HomeEasyAdapter(view.getActivity(), beanList);
    }

    @AdapterQualifier("newRecommendAdapter")
    @FragmentScope
    @Provides
    DelegateAdapter.Adapter provideHomeNewRecommendAdapter(List<HomeBean.NewProductBean> beanList) {
        return new HomeNewRecommendAdapter(view.getActivity(), beanList);
    }

    @FragmentScope
    @Provides
    List<HomeTabBean> provideListHomeTabBean() {
        List<HomeTabBean> homeTabBeanList = new ArrayList<>();
        for (int i = 0; i < imgRes.length; i++) {
            homeTabBeanList.add(new HomeTabBean(imgRes[i], 0, textRes[i]));
        }
        return homeTabBeanList;
    }


}
