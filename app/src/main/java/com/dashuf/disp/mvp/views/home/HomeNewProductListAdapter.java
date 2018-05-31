package com.dashuf.disp.mvp.views.home;

import android.content.Context;
import android.view.ViewGroup;

import com.dashuf.disp.mvp.model.entity.HomeBean;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

/**
 * Created by Mr.Jude on 2015/7/18.
 */
public class HomeNewProductListAdapter extends RecyclerArrayAdapter<HomeBean.NewProductBean> {


    public HomeNewProductListAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new HomeNewProductListViewHolder(parent);
    }
}
