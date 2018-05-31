package com.dashuf.disp.mvp.views.discovery.discoveryhome;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.bumptech.glide.Glide;
import com.dashuf.disp.R;
import com.dashuf.disp.mvp.model.entity.DiscoveryNewsBean;
import com.jess.arms.utils.ArmsUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Description:
 * Copyright  : Copyright (c) 2016
 * Company    :
 * Author     : jone
 * Date       : 2018/3/8 23:16
 */

public class NewsHotSpotsAdapter extends DelegateAdapter.Adapter<NewsHotSpotsAdapter.BannerViewHolder> {

    private Context context;
    private List<DiscoveryNewsBean.HotSpotsBean> hotSpotsBeanList;

    public NewsHotSpotsAdapter(Context context, List<DiscoveryNewsBean.HotSpotsBean> hotSpotsBeanList) {
        this.context = context;
        this.hotSpotsBeanList = hotSpotsBeanList;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(2, null == hotSpotsBeanList ? 0 : hotSpotsBeanList.size());
        gridLayoutHelper.setWeights(new float[]{50f, 50f});
        int left = ArmsUtils.dip2px(context, 5);
        int margin = (int) context.getResources().getDimension(R.dimen.activity_horizontal_margin) - left;
        gridLayoutHelper.setPadding(margin, margin, margin, margin);
        gridLayoutHelper.setBgColor(Color.WHITE);
        gridLayoutHelper.setMarginTop(20);
        return gridLayoutHelper;
    }

    @Override
    public BannerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BannerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_news_hot_spots, parent, false));
    }

    @Override
    public void onBindViewHolder(BannerViewHolder holder, int position) {
        Context context = holder.itemView.getContext();
        Glide.with(context).load(hotSpotsBeanList.get(position)
                .getCoverImageUrl())
//                .placeholder(R.drawable.place_holder_discovery_dsclassroom)
//                .error(R.drawable.place_holder_discovery_dsclassroom)
                .into(holder.ivHotSpots);
        holder.itemView.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(listener);
    }


    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int position = (int) v.getTag();
//            WebviewActivity.startThisActivity(v.getContext(), hotSpotsBeanList.get(position).getHref());
        }
    };

    @Override
    public int getItemCount() {
        return hotSpotsBeanList == null ? 0 : hotSpotsBeanList.size();
    }


    static class BannerViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_hot_spots)
        ImageView ivHotSpots;

        BannerViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
