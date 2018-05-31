package com.dashuf.disp.mvp.views.discovery.discoveryhome;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.RecyclablePagerAdapter;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.bumptech.glide.Glide;
import com.dashuf.disp.R;
import com.dashuf.disp.mvp.model.entity.DiscoveryNewsBean;
import com.dashuf.disp.widget.MyPointHintView;
import com.jess.arms.utils.ArmsUtils;
import com.jude.rollviewpager.RollPagerView;

import java.util.List;

/**
 * Description:
 * Copyright  : Copyright (c) 2016
 * Company    :
 * Author     : jone
 * Date       : 2018/3/8 21:43
 */

public class NewsBannerAdapter extends DelegateAdapter.Adapter<NewsBannerAdapter.BannerViewHolder> {

    private static final int BANNER_TYPE = 101;
    private List<DiscoveryNewsBean.PositionTopBean> positionTop;
    private RecyclerView.RecycledViewPool viewPool;
    private LayoutHelper helper;
    private PagerAdapter pagerAdapter;

    public NewsBannerAdapter(List<DiscoveryNewsBean.PositionTopBean> positionTop, RecyclerView.RecycledViewPool viewPool, LayoutHelper helper) {
        this.positionTop = positionTop;
        this.viewPool = viewPool;
        this.helper = helper;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return helper;
    }


    @Override
    public BannerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView;
        if (viewType == BANNER_TYPE) {
            rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_news_view_pager, parent, false);
        } else {
            rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_news_banner, parent, false);
        }
        return new BannerViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(BannerViewHolder holder, int position) {
        RollPagerView viewPager = (RollPagerView) holder.itemView;
        if (positionTop.size() > 1) {
            viewPager.setPlayDelay(5000);
            viewPager.setAnimationDurtion(2000);
            viewPager.setHintView(new MyPointHintView(viewPager.getContext(), ContextCompat.getColor(viewPager.getContext(), R.color.white), ContextCompat.getColor(viewPager.getContext(), R.color.white_bg_trans)));
        } else {
            viewPager.pause();
            viewPager.setHintView(new MyPointHintView(viewPager.getContext(), ContextCompat.getColor(viewPager.getContext(), R.color.transparent), Color.WHITE));
        }


        viewPager.setPlayDelay(5000);
        viewPager.setAnimationDurtion(2000);
        int phoneWidth = ArmsUtils.getScreenWidth(viewPager.getContext());
        ViewGroup.LayoutParams layoutParams = viewPager.getLayoutParams();
        layoutParams.height = phoneWidth * 2 / 5;
        viewPager.setLayoutParams(layoutParams);
        if (null == pagerAdapter) {
            pagerAdapter = new PagerAdapter(positionTop, this, viewPool);
            viewPager.setAdapter(pagerAdapter);
        } else {
            pagerAdapter.notifyDataSetChanged();
        }

    }


    @Override
    public int getItemViewType(int position) {
        return BANNER_TYPE;
    }


    @Override
    public int getItemCount() {
        return 1;
    }

    static class PagerAdapter extends RecyclablePagerAdapter<BannerViewHolder> {

        private final List<DiscoveryNewsBean.PositionTopBean> positionTop;

        public PagerAdapter(List<DiscoveryNewsBean.PositionTopBean> positionTop, NewsBannerAdapter bannerAdapter, RecyclerView.RecycledViewPool viewPool) {
            super(bannerAdapter, viewPool);
            this.positionTop = positionTop;
        }

        @Override
        public int getCount() {
            return null == positionTop ? 0 : positionTop.size();
        }

        @Override
        public void onBindViewHolder(BannerViewHolder viewHolder, int position) {
            // only vertical
            viewHolder.itemView.setLayoutParams(new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            ImageView ivBanner = viewHolder.itemView.findViewById(R.id.iv_banner);
            Glide.with(viewHolder.itemView.getContext()).load(positionTop.get(position)
                    .getCoverImageUrl())
//                    .placeholder(R.drawable.place_holder_banner_discover)
//                    .error(R.drawable.place_holder_banner_discover)
                    .into(ivBanner);
            viewHolder.itemView.setOnClickListener(listener);
            viewHolder.itemView.setTag(position);
        }

        @Override
        public int getItemViewType(int position) {
            return 0;
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

        private View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = (int) v.getTag();
                if (null != positionTop && positionTop.size() > position) {
//                    WebviewActivity.startThisActivity(v.getContext(), positionTop.get(position).getHref());
                }
            }
        };
    }


    static class BannerViewHolder extends RecyclerView.ViewHolder {

        public BannerViewHolder(View itemView) {
            super(itemView);
        }
    }


}
