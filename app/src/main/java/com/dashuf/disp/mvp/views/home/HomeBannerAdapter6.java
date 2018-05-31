package com.dashuf.disp.mvp.views.home;

import android.content.Context;
import android.database.DataSetObserver;
import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.VirtualLayoutManager;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.dashuf.disp.R;
import com.dashuf.disp.mvp.model.entity.HomeBean;
import com.dashuf.disp.widget.MyPointHintView;
import com.jess.arms.base.BaseActivity;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
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

public class HomeBannerAdapter6 extends DelegateAdapter.Adapter<RecyclerView.ViewHolder> {

    private static final int BANNER_TYPE = 101;
    private Context context;
    private List<HomeBean.BannerBean> positionTop;
    private RecyclerView.RecycledViewPool viewPool;
    private PagerAdapter pagerAdapter;

    public HomeBannerAdapter6(Context context, List<HomeBean.BannerBean> positionTop, RecyclerView.RecycledViewPool viewPool) {
        this.context = context;
        this.positionTop = positionTop;
        this.viewPool = viewPool;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return new LinearLayoutHelper();
    }

//    @Override
//    public void onViewRecycled(BannerViewHolder holder) {
//        if (holder.itemView instanceof RollPagerView) {
//            ((RollPagerView) holder.itemView).setAdapter(null);
//        }
//    }

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
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder.itemView instanceof RollPagerView) {
            RollPagerView viewPager = (RollPagerView) holder.itemView;
            if (positionTop.size() > 1) {
                viewPager.setPlayDelay(5000);
                viewPager.setAnimationDurtion(2000);
                viewPager.setHintView(new MyPointHintView(context, ContextCompat.getColor(context, R.color.white), ContextCompat.getColor(context, R.color.white_bg_trans)));
            } else {
                viewPager.pause();
                viewPager.setHintView(new MyPointHintView(context, ContextCompat.getColor(context, R.color.transparent), Color.WHITE));
            }


            int phoneWidth = ArmsUtils.getScreenWidth(viewPager.getContext());
            ViewGroup.LayoutParams layoutParams = viewPager.getLayoutParams();
            layoutParams.height = (phoneWidth * 179 / 375);
            viewPager.setLayoutParams(layoutParams);

            if (null == pagerAdapter) {
                pagerAdapter = new PagerAdapter(context, viewPager, positionTop, this, viewPool);
                viewPager.setAdapter(pagerAdapter);
                pagerAdapter.registerDataSetObserver(new DataSetObserver() {
                    @Override
                    public void onChanged() {
                        super.onChanged();
                    }
                });
            } else {
                pagerAdapter.notifyDataSetChanged();
            }
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

    static class PagerAdapter extends HomeLooperAdapter {

        private Context context;
        private List<HomeBean.BannerBean> positionTop;
        private final ImageLoader imageLoader;


        public PagerAdapter(Context context, RollPagerView viewPager, List<HomeBean.BannerBean> positionTop, RecyclerView.Adapter<RecyclerView.ViewHolder> bannerAdapter, RecyclerView.RecycledViewPool viewPool) {
            super(bannerAdapter, viewPool, viewPager);
            this.context = context;
            this.positionTop = positionTop;
            AppComponent appComponent = ArmsUtils.obtainAppComponentFromContext(context);
            imageLoader = appComponent.imageLoader();
        }


        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
            if (positionTop.size() == 0) {
                return;
            }
            int i = position % positionTop.size();
            viewHolder.itemView.setLayoutParams(new VirtualLayoutManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            ImageView ivBanner = viewHolder.itemView.findViewById(R.id.iv_banner);
            ivBanner.setImageResource(R.drawable.home_default_banner);
//            Glide.with(viewHolder.itemView.getContext()).load(positionTop.get(i).getImg())
//                    .placeholder(R.drawable.place_holder_banner)
//                    .error(R.drawable.home_default_banner)
//                    .into(ivBanner);

            imageLoader.loadImage(viewHolder.itemView.getContext(),
                    ImageConfigImpl
                            .builder()
                            .url(positionTop.get(i).getImg())
                            .errorPic(R.drawable.home_default_banner)
                            .placeholder(R.drawable.place_holder_banner)
                            .imageView(ivBanner)
                            .build());
            ;

            viewHolder.itemView.setOnClickListener(listener);
            viewHolder.itemView.setTag(i);
        }


        @Override
        protected int getRealCount() {
            return null == positionTop ? 0 : positionTop.size();
        }

        private View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (BaseActivity.isLogin()) {
                    int position = (int) v.getTag();
                    if (positionTop.size() > position) {
//                        WebviewActivity.startThisActivity(context, positionTop.get(position).getLink());
                    }
                } else {
//                    BaseActivity.reLogin((Activity) context, null);
                }
            }
        };

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }


    }


    static class BannerViewHolder extends RecyclerView.ViewHolder {
        public BannerViewHolder(View itemView) {
            super(itemView);
        }
    }


}
