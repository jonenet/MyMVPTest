package com.dashuf.disp.mvp.views.discovery.discoveryhome;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.dashuf.disp.global.AppConstant;
import com.dashuf.disp.mvp.model.entity.DiscoveryNewsBean;
import com.dashuf.disp.mvp.views.iview.IDiscoveryNewsView;
import com.jess.arms.base.BaseDaggerFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;
import com.dashuf.disp.dagger.module.DiscoveryNewsModule;
import com.dashuf.disp.dagger.component.DaggerDiscoveryNewsComponent;
import com.dashuf.disp.mvp.presenter.DiscoveryNewsPresenter;
import com.dashuf.disp.R;
import com.jess.arms.utils.Preconditions;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;


public class DiscoveryNewsFragment extends BaseDaggerFragment<DiscoveryNewsPresenter> implements IDiscoveryNewsView {

    @BindView(R.id.news_recycler_discovery)
    RecyclerView newsRecyclerDiscovery;
    @BindView(R.id.swipe_refresh_discovery)
    SmartRefreshLayout swipeRefreshLayout;
    private String id;

    private List<DiscoveryNewsBean.PositionTopBean> positionTop = new ArrayList<>();
    private List<DiscoveryNewsBean.HotSpotsBean> hotSpots = new ArrayList<>();
    private List<DiscoveryNewsBean.HotInfoBean> hotInfo = new ArrayList<>();
    private List<DiscoveryNewsBean.DSClassroomBean> dsClassroom = new ArrayList<>();
    private List<DiscoveryNewsBean.ProductExchangeBean> productExchange = new ArrayList<>();
    private List<DelegateAdapter.Adapter> adapters;


    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerDiscoveryNewsComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .discoveryNewsModule(new DiscoveryNewsModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_discovery_news, container, false);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        VirtualLayoutManager layoutManager = new VirtualLayoutManager(mContext);
        newsRecyclerDiscovery.setLayoutManager(layoutManager);

        RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();

        newsRecyclerDiscovery.setRecycledViewPool(viewPool);

        viewPool.setMaxRecycledViews(0, 20);

        DelegateAdapter delegateAdapter = new DelegateAdapter(layoutManager, false);

        adapters = new LinkedList<>();
        LinearLayoutHelper linearLayoutHelper = new LinearLayoutHelper();
        linearLayoutHelper.setBgColor(Color.WHITE);
        NewsBannerAdapter bannerAdapter = new NewsBannerAdapter(positionTop, viewPool, linearLayoutHelper);
        NewsDSClassroomAdapter dsClassroomAdapter = new NewsDSClassroomAdapter(mContext, dsClassroom);
        NewsProductExchangeAdapter newsProductExchangeAdapter = new NewsProductExchangeAdapter(mContext, productExchange);
        NewsHotInfoAdapter newsHotInfoAdapter = new NewsHotInfoAdapter(mContext, hotInfo);
        NewsHotSpotsAdapter newsHotSpotsAdapter = new NewsHotSpotsAdapter(mContext, hotSpots);

        adapters.add(bannerAdapter);
        adapters.add(dsClassroomAdapter);
        adapters.add(newsHotInfoAdapter);
        adapters.add(newsProductExchangeAdapter);
        adapters.add(newsHotSpotsAdapter);
        delegateAdapter.setAdapters(adapters);

        newsRecyclerDiscovery.setAdapter(delegateAdapter);

        swipeRefreshLayout.setEnableFooterFollowWhenLoadFinished(true);
        swipeRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                getData();
            }
        });
        swipeRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                swipeRefreshLayout.finishLoadMoreWithNoMoreData();
            }
        });
        Bundle arguments = getArguments();
        if (null != arguments) {
            id = arguments.getString(AppConstant.ID);
        }
        swipeRefreshLayout.finishLoadMoreWithNoMoreData();
        ((ClassicsFooter) mRootView.findViewById(R.id.footView)).setNoMoreDataText("更多精彩尽请期待!");
        getData();
    }


    private void getData() {
        Map<String, String> params = new HashMap<>();
        params.put("columnId", id);
        params.put("isPage", "0");
        params.put("mCurrentPage", "1");
        params.put("pageSize", "1");
        assert mPresenter != null;
        mPresenter.requestPageDetail(params);
    }

    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(@NonNull String message) {
        Preconditions.checkNotNull(message);
        ArmsUtils.snackbarText(message);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        Preconditions.checkNotNull(intent);
        ArmsUtils.startActivity(intent);
    }

    @Override
    public void onPageDetailResult(DiscoveryNewsBean dataBean) {
        swipeRefreshLayout.finishRefresh(true);
        if (null != dataBean) {
            List<DiscoveryNewsBean.DSClassroomBean> dsClassroom = dataBean.getDSClassroom();
            List<DiscoveryNewsBean.HotInfoBean> hotInfo = dataBean.getHotInfo();
            List<DiscoveryNewsBean.ProductExchangeBean> productExchange = dataBean.getProductExchange();
            List<DiscoveryNewsBean.PositionTopBean> positionTop = dataBean.getPositionTop();
            List<DiscoveryNewsBean.HotSpotsBean> hotSpots = dataBean.getHotSpots();
            if (null != dsClassroom) {
                this.dsClassroom.clear();
                this.dsClassroom.addAll(dsClassroom);
            }
            if (null != hotInfo) {
                this.hotInfo.clear();
                this.hotInfo.addAll(hotInfo);

            }
            if (null != productExchange) {
                this.productExchange.clear();
                this.productExchange.addAll(productExchange);
            }
            if (null != positionTop) {
                this.positionTop.clear();
                this.positionTop.addAll(positionTop);
            }
            if (null != hotSpots) {
                this.hotSpots.clear();
                this.hotSpots.addAll(hotSpots);
            }

            notifyView();
        }
    }

    public void notifyView() {
        for (DelegateAdapter.Adapter adapter : adapters) {
            adapter.notifyDataSetChanged();
        }
    }

}
