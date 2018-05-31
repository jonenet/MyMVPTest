package com.dashuf.disp.mvp.views.discovery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dashuf.disp.R;
import com.dashuf.disp.mvp.model.entity.DiscoveryItemBean;
import com.jess.arms.utils.ArmsUtils;
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

public class DiscoveryDSClassroomAdapter extends RecyclerArrayAdapter<DiscoveryItemBean> {


    public DiscoveryDSClassroomAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_news_ds_classroom, viewGroup, false));
    }

    @Override
    public void OnBindViewHolder(BaseViewHolder holder, int position) {
        super.OnBindViewHolder(holder, position);
        int margin = (int) getContext().getResources().getDimension(R.dimen.activity_horizontal_margin);
        int fiveDp = ArmsUtils.dip2px(getContext(), 5);
        if (position % 2 == 0) {
            holder.itemView.setPadding(margin, fiveDp, fiveDp, fiveDp);
        } else {
            holder.itemView.setPadding(fiveDp, fiveDp, margin, fiveDp);
        }
    }


    static class ViewHolder extends BaseViewHolder<DiscoveryItemBean> {

        @BindView(R.id.iv_ds_classroom)
        ImageView ivDsClassroom;
        @BindView(R.id.tv_da_classroom_title)
        TextView tvDaClassroomTitle;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        @Override
        public void setData(DiscoveryItemBean data) {
            super.setData(data);
            Glide.with(getContext()).load(data.getCoverImageUrl())
//                    .placeholder(R.drawable.place_holder_discovery_dsclassroom)
//                    .error(R.drawable.place_holder_discovery_dsclassroom)
                    .into(ivDsClassroom);
            tvDaClassroomTitle.setText(data.getTitle());
        }
    }
}
