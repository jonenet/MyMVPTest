package com.dashuf.disp.mvp.views.discovery.discoveryhome;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
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

public class NewsHotInfoAdapter extends DelegateAdapter.Adapter<NewsHotInfoAdapter.HotInfoViewHolder> {

    private Context context;
    private List<DiscoveryNewsBean.HotInfoBean> hotInfo;

    public NewsHotInfoAdapter(Context context, List<DiscoveryNewsBean.HotInfoBean> hotInfo) {
        this.context = context;
        this.hotInfo = hotInfo;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        LinearLayoutHelper linearLayoutHelper = new LinearLayoutHelper();
        int margin = (int) context.getResources().getDimension(R.dimen.activity_horizontal_margin);
        linearLayoutHelper.setPadding(margin, ArmsUtils.dip2px(context,6), margin, ArmsUtils.dip2px(context,6));
        linearLayoutHelper.setBgColor(Color.WHITE);
        linearLayoutHelper.setMarginTop(20);
        return linearLayoutHelper;
    }

    @Override
    public HotInfoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HotInfoViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_news_hot_info, parent, false));
    }

    @Override
    public void onBindViewHolder(HotInfoViewHolder holder, int position) {
        DiscoveryNewsBean.HotInfoBean hotInfoBean = hotInfo.get(position);
        Glide.with(holder.itemView.getContext()).load(hotInfoBean.getCoverImageUrl())
//                .placeholder(R.drawable.place_holder_discovery_hot_info)
//                .error(R.drawable.place_holder_discovery_hot_info)
                .into(holder.ivHotInfo);
        holder.tvHotInfoTitle.setText(hotInfoBean.getTitle());
        holder.tvHotInfoContent.setText(hotInfoBean.getAbstractContent());
        if (position == hotInfo.size() - 1) {
            holder.viewLine.setVisibility(View.GONE);
        } else {
            holder.viewLine.setVisibility(View.VISIBLE);
        }
        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(listener);
    }


    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int position = (int) v.getTag();
//            WebviewActivity.startThisActivity(v.getContext(), hotInfo.get(position).getHref());
        }
    };

    @Override
    public int getItemCount() {
        return hotInfo == null ? 0 : hotInfo.size();
    }


    static class HotInfoViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_hot_info)
        ImageView ivHotInfo;
        @BindView(R.id.tv_hot_info_title)
        TextView tvHotInfoTitle;
        @BindView(R.id.tv_hot_info_content)
        TextView tvHotInfoContent;
        @BindView(R.id.view_line)
        View viewLine;

        public HotInfoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
