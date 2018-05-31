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
 * Author     : jone
 * Date       : 2018/3/8 22:40
 */

public class NewsDSClassroomAdapter extends DelegateAdapter.Adapter<NewsDSClassroomAdapter.OneViewHolder> {


    private Context context;
    private List<DiscoveryNewsBean.DSClassroomBean> dsClassroom;

    public NewsDSClassroomAdapter(Context context, List<DiscoveryNewsBean.DSClassroomBean> dsClassroom) {
        this.context = context;
        this.dsClassroom = dsClassroom;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        GridLayoutHelper helper = new GridLayoutHelper(2, null == dsClassroom ? 0 : dsClassroom.size());
        helper.setWeights(new float[]{50f, 50f});
        int left = ArmsUtils.dip2px(context, 10);
        helper.setGap(left);
        int margin = (int) context.getResources().getDimension(R.dimen.activity_horizontal_margin);
        helper.setPadding(margin, margin, margin, margin);
        helper.setBgColor(Color.WHITE);
        return helper;
    }

    @Override
    public OneViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new OneViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_news_ds_classroom, parent, false));
    }

    @Override
    public void onBindViewHolder(OneViewHolder holder, int position) {
        DiscoveryNewsBean.DSClassroomBean dsClassroomBean = dsClassroom.get(position);
        Glide.with(holder.itemView.getContext()).load(dsClassroomBean.getCoverImageUrl())
//                .placeholder(R.drawable.place_holder_discovery_dsclassroom)
//                .error(R.drawable.place_holder_discovery_dsclassroom)
                .into(holder.ivDsClassroom);
        holder.tvDaClassroomTitle.setText(dsClassroomBean.getTitle());
        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(listener);
    }


    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int position = (int) v.getTag();
//            WebviewActivity.startThisActivity(v.getContext(), dsClassroom.get(position).getHref());
        }
    };

    @Override
    public int getItemCount() {
        return null == dsClassroom ? 0 : dsClassroom.size();
    }


    static class OneViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_ds_classroom)
        ImageView ivDsClassroom;
        @BindView(R.id.tv_da_classroom_title)
        TextView tvDaClassroomTitle;

        OneViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
