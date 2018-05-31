package com.dashuf.disp.mvp.views.discovery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dashuf.disp.R;
import com.dashuf.disp.mvp.model.entity.DiscoveryItemBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Description:
 * Copyright  : Copyright (c) 2016
 * Company    :
 * Author     : jone
 * Date       : 2018/3/9 14:33
 */

public class DiscoveryHotInfoAdapter extends RecyclerArrayAdapter<DiscoveryItemBean> implements RecyclerArrayAdapter.ItemView {

    private DiscoveryItemBean headBean;

    public DiscoveryHotInfoAdapter(Context context ) {
        super(context);
    }


    public void setHeadBean(DiscoveryItemBean headBean) {
        this.headBean = headBean;
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_discovery_hot_info_pager, viewGroup, false));
    }

    @Override
    public View onCreateView(ViewGroup viewGroup) {
        return LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_discovery_hot_info_top, viewGroup, false);
    }

    @Override
    public void onBindView(View view) {
        if (null != headBean && view instanceof LinearLayout) {
            ((TextView) view.findViewById(R.id.tv_hot_info_head_title)).setText(headBean.getTitle());
            Glide.with(getContext()).load(headBean.getCoverImageUrl())
//                    .placeholder(R.drawable.place_holder_banner)
//                    .error(R.drawable.place_holder_banner)
                    .into(((ImageView) view.findViewById(R.id.iv_hot_info_head_img)));
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    WebviewActivity.startThisActivity(getContext(), headBean.getHref());
                }
            });
        }
    }


    static class ViewHolder extends BaseViewHolder<DiscoveryItemBean> {

        @BindView(R.id.iv_hot_info)
        ImageView ivHotInfo;
        @BindView(R.id.tv_hot_info_title)
        TextView tvHotInfoTitle;
        @BindView(R.id.tv_hot_info_size)
        TextView tvHotInfoSize;
        @BindView(R.id.view_line)
        View viewLine;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void setData(DiscoveryItemBean data) {
            super.setData(data);
            if (getDataPosition() == getItemCount() - 1) {
                viewLine.setVisibility(View.GONE);
            } else {
                viewLine.setVisibility(View.VISIBLE);
            }
            Glide.with(getContext()).load(data.getCoverImageUrl())
//                    .placeholder(R.drawable.place_holder_discovery_hot_info)
//                    .error(R.drawable.place_holder_discovery_hot_info)
                    .into(ivHotInfo);
            tvHotInfoTitle.setText(data.getTitle());
            tvHotInfoSize.setText(String.valueOf(data.getViewCount()));
        }

    }


}
