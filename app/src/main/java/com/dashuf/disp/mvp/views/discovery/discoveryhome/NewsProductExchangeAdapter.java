package com.dashuf.disp.mvp.views.discovery.discoveryhome;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.dashuf.disp.R;
import com.dashuf.disp.mvp.model.entity.DiscoveryNewsBean;

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

public class NewsProductExchangeAdapter extends DelegateAdapter.Adapter<NewsProductExchangeAdapter.BannerViewHolder> {

    private Context context;
    private List<DiscoveryNewsBean.ProductExchangeBean> productExchange;

    public NewsProductExchangeAdapter(Context context, List<DiscoveryNewsBean.ProductExchangeBean> productExchange) {
        this.context = context;
        this.productExchange = productExchange;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        LinearLayoutHelper linearLayoutHelper = new LinearLayoutHelper();
        int margin = (int) context.getResources().getDimension(R.dimen.activity_horizontal_margin);
        linearLayoutHelper.setPadding(margin, margin, margin, margin);
        linearLayoutHelper.setBgColor(Color.WHITE);
        linearLayoutHelper.setMarginTop(20);
        return linearLayoutHelper;
    }

    @Override
    public BannerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BannerViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_news_product_exchange, parent, false));
    }

    @Override
    public void onBindViewHolder(BannerViewHolder holder, int position) {
        DiscoveryNewsBean.ProductExchangeBean productExchangeBean = productExchange.get(position);
        holder.tvProductExchangeTitle.setText(productExchangeBean.getTitle());
        holder.tvProductExchangeTitle.setText(productExchangeBean.getAbstractContent());
        if (position == productExchange.size() - 1) {
            holder.exchangeViewLine.setVisibility(View.GONE);
        } else {
            holder.exchangeViewLine.setVisibility(View.VISIBLE);
        }
        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(listener);
    }


    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int position = (int) v.getTag();
//            WebviewActivity.startThisActivity(v.getContext(), productExchange.get(position).getHref());
        }
    };

    @Override
    public int getItemCount() {
        return productExchange == null ? 0 : productExchange.size();
    }


    static class BannerViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_product_exchange_title)
        TextView tvProductExchangeTitle;
        @BindView(R.id.tv_product_exchange_content)
        TextView tvProductExchangeContent;
        @BindView(R.id.exchange_view_line)
        View exchangeViewLine;

        BannerViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
