package com.dashuf.disp.mvp.views.discovery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.dashuf.disp.R;
import com.dashuf.disp.mvp.model.entity.DiscoveryItemBean;
import com.dashuf.disp.utils.GlideRoundTransform;
import com.jess.arms.utils.ArmsUtils;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Description:
 * Author     : jone
 * Date       : 2018/3/15 16:55
 */

public class DiscoveryHotSpotsAdapter extends RecyclerArrayAdapter<DiscoveryItemBean> {

    public DiscoveryHotSpotsAdapter(Context context) {
        super(context);
    }


    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_discovery_hot_spots_pager, viewGroup, false));
    }

    static class ViewHolder extends BaseViewHolder<DiscoveryItemBean> {

        @BindView(R.id.iv_hot_spots)
        ImageView ivHotSpots;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


        @Override
        public void setData(DiscoveryItemBean data) {
            super.setData(data);
            Glide.with(getContext()).
                    load(data.getCoverImageUrl()).
//                    placeholder(R.drawable.place_holder_brand_card).
//                    error(R.drawable.place_holder_brand_card).
//        bitmapTransform(new GlideRoundTransform(getContext(), ArmsUtils.dip2px(getContext(), 5), GlideRoundTransform.CornerType.ALL)).
//            fitCenter().
        into(ivHotSpots);
//            Glide.with(getContext()).load(data.getCoverImageUrl()).into(ivHotSpots);
        }
    }


}
