package com.dashuf.disp.mvp.views.home;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.dashuf.disp.R;
import com.dashuf.disp.dagger.anno.AdapterQualifier;
import com.dashuf.disp.dagger.component.DaggerHomeComponent;
import com.dashuf.disp.dagger.module.HomeModule;
import com.dashuf.disp.mvp.model.entity.HomeBean;
import com.dashuf.disp.mvp.model.entity.HomeNotificationBean;
import com.dashuf.disp.mvp.model.entity.HomeTabBean;
import com.dashuf.disp.mvp.presenter.HomePresenter;
import com.dashuf.disp.mvp.views.HomeActivity;
import com.dashuf.disp.mvp.views.iview.IHomeView;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.base.BaseDaggerFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.StatusBarUtil;
import com.jess.arms.widget.BadgeView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by ex-zhoulai on 2018/5/29.
 * 首页
 */

public class HomeFragment6 extends BaseDaggerFragment<HomePresenter> implements IHomeView {

    @BindView(R.id.city)
    TextView city;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.tv_empty)
    ImageView msg;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.status_bar)
    View statusBar;
    @BindView(R.id.recycler_view_home)
    RecyclerView recyclerViewHome;
    @BindView(R.id.refresh_layout_home)
    SmartRefreshLayout refreshLayoutHome;

    // 将透明度声明成局部变量用于判断
    float alpha = 0;
    float scale = 0;
    int height = 0;
    private BadgeView badgeViewMsgUnread;
    public int overScrollY = 0;

    @AdapterQualifier("bannerAdapter")
    @Inject
    DelegateAdapter.Adapter bannerAdapter;
    @AdapterQualifier("tabAdapter")
    @Inject
    DelegateAdapter.Adapter homeTabAdapter;
    @AdapterQualifier("easyAdapter")
    @Inject
    DelegateAdapter.Adapter homeEasyAdapter;
    @AdapterQualifier("newRecommendAdapter")
    @Inject
    DelegateAdapter.Adapter homeNewRecommendAdapter;

    @Inject
    RecyclerView.RecycledViewPool viewPool;
    @Inject
    DelegateAdapter delegateAdapter;
    @Inject
    LinkedList<DelegateAdapter.Adapter> adapterList;

    HomeAdvAdapter homeAdvAdapter;
    @Inject
    VirtualLayoutManager virtualLayoutManager;

    @Inject
    List<HomeBean.BannerBean> homeBannerList;
    @Inject
    List<HomeBean.EasyProductBean> homeEasyProductList;
    @Inject
    List<HomeBean.NewProductBean> homeNewsProductList;

    @Inject
    List<HomeTabBean> homeTabBeanList;

    private List<HomeNotificationBean.ContentBean> notificationList = new ArrayList<>();


    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return LayoutInflater.from(mContext).inflate(R.layout.content_home6, container, false);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        height = 0;//(int) ArmsUtils.getActionBarHeight(mBaseActivity) + ArmsUtils.getStatusBarHeight(mBaseActivity);
        //状态栏设置
        title.setTextSize(20);
        setActionBarAlpha(0);
//        if (!TextUtils.isEmpty(UserSettingProvider.provider().getUserLocationCity())) {
//            city.setText(UserSettingProvider.provider().getUserLocationCity());
//        } else {
//            city.setText("定位失败");
//        }

        viewPool.setMaxRecycledViews(0, 20);
        recyclerViewHome.setRecycledViewPool(viewPool);

        homeAdvAdapter = new HomeAdvAdapter(mContext, notificationList);
        adapterList.add(bannerAdapter);
        adapterList.add(homeTabAdapter);
        adapterList.add(homeAdvAdapter);
        adapterList.add(homeEasyAdapter);
        adapterList.add(homeNewRecommendAdapter);

        delegateAdapter.addAdapters(adapterList);
        recyclerViewHome.setLayoutManager(virtualLayoutManager);
        recyclerViewHome.setAdapter(delegateAdapter);

        recyclerViewHome.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int topRowVerticalPosition = (recyclerView == null || recyclerView.getChildCount() == 0) ? 0
                        : recyclerView.getChildAt(0).getTop();

                refreshLayoutHome.setEnabled(topRowVerticalPosition >= 0);

                overScrollY += dy;
                if (getActivity() != null && getActivity() instanceof HomeActivity && isAdded()) {
                    if (overScrollY < 0) overScrollY = 0;
                    if (overScrollY <= height) {
                        scale = (float) overScrollY / height;
                        alpha = 255 * scale;
                        // 随着滑动距离改变透明度
                        // Log.e("al=","="+alpha);
                    } else {
                        if (alpha < 255) {
                            // 防止频繁重复设置相同的值影响性能
                            alpha = 255;
                        }
                    }
                    setActionBarAlpha(alpha);
                }
            }
        });


        refreshLayoutHome.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                requestData();
            }
        });

        refreshLayoutHome.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                refreshLayoutHome.finishLoadMoreWithNoMoreData();
            }
        });

        StatusBarUtil.StatusBarDarkMode(mContext);


        requestData();
    }


    private void requestData() {
        overScrollY = 0;
        getUnReadMsg();

        Map<String, String> params = new HashMap<>();
        params.put("dataType", "appIndexPage");
        params.put("cityId", "440300");
        mPresenter.requestHomeData(params);
        if (BaseActivity.isLogin()) {
//            mPresenter.requestSwich(show);
        }
    }


    public void setActionBarAlpha(float alpha) {
        float tran = alpha / 255;
        toolbar.setTitleTextColor(ContextCompat.getColor(mContext, R.color.black));
        //TODO 状态栏兼容性
        if (alpha > 5) {
            if (alpha > 100) {
//                clientOnline.setImageResource(R.drawable.ico_help_online_p);
                msg.setImageResource(R.drawable.ico_msg_p);
                city.setTextColor(ContextCompat.getColor(mContext, R.color.colorPrimary));
                city.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ico_arrow_down_p, 0);

                StatusBarUtil.setTranslucentForImageView((Activity) mContext, R.color.transparent);
                StatusBarUtil.StatusBarLightMode(mContext);
            } else {
//                clientOnline.setImageResource(R.drawable.ico_help_online);
                msg.setImageResource(R.drawable.ico_msg);
                city.setTextColor(ContextCompat.getColor(mContext, R.color.white));
                city.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ico_arrow_down, 0);
                StatusBarUtil.StatusBarDarkMode(mContext, StatusBarUtil.StatusBarLightMode(mContext));
                StatusBarUtil.setTranslucentForImageView(mContext, R.color.transparent);
            }
        } else {
            tran = 0;
//            clientOnline.setImageResource(R.drawable.ico_help_online);
            msg.setImageResource(R.drawable.ico_msg);
            city.setTextColor(ContextCompat.getColor(mContext, R.color.white));
            city.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ico_arrow_down, 0);
            StatusBarUtil.StatusBarDarkMode(mContext, StatusBarUtil.StatusBarLightMode(mContext));
            StatusBarUtil.setTranslucentForImageView(mContext, R.color.transparent);
        }

        statusBar.getBackground().mutate().setAlpha((int) alpha);
        toolbar.getBackground().mutate().setAlpha((int) alpha);
//        toolbar.setAlpha(tran);
        title.setAlpha(tran);
        title.setTextSize(tran * 20);
    }

    @Override
    public void setData(@Nullable Object data) {

    }


    private void getUnReadMsg() {
        //小能客服的登陆在‘我的’界面点击进入客服的时候，这里是十分钟的时间回调消息，十分钟之后通过推送来进行获取未读消息
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {
        refreshLayoutHome.finishRefresh();
    }

    @Override
    public void showMessage(@NonNull String message) {

    }

    @Override
    public void launchActivity(@NonNull Intent intent) {

    }

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerHomeComponent
                .builder()
                .appComponent(appComponent)
                .homeModule(new HomeModule(this))
                .build()
                .inject(this);
    }


    @Override
    public void onHomeResult(HomeBean homeBean) {
        if (null != homeBean.getBanner()) {
            homeBannerList.clear();
            homeBannerList.addAll(homeBean.getBanner());
            bannerAdapter.notifyDataSetChanged();
        }
        if (null != homeBean.getBanner()) {
            homeEasyProductList.clear();
            homeEasyProductList.addAll(homeBean.getEasyProduct());
            homeEasyAdapter.notifyDataSetChanged();
        }
        if (null != homeBean.getNewProduct()) {
            homeNewsProductList.clear();
            homeNewsProductList.addAll(homeBean.getNewProduct());
            homeNewRecommendAdapter.notifyDataSetChanged();
        }
    }
}