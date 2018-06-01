package com.dashuf.disp.mvp.views.discovery;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.dashuf.disp.global.AppConstant;
import com.dashuf.disp.mvp.model.entity.DiscoveryItemBean;
import com.dashuf.disp.mvp.model.entity.PageResult;
import com.dashuf.disp.mvp.model.entity.ResultBean;
import com.dashuf.disp.mvp.views.base.BaseRecyclerFragment;
import com.google.gson.reflect.TypeToken;
import com.jess.arms.mvp.Message;
import com.jess.arms.utils.ArmsUtils;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ex-zhoulai on 2018/6/1.
 * 发现产品
 */

public class DiscoveryProductExchangeFragment  extends BaseRecyclerFragment<DiscoveryItemBean> {

    private String id;

    @Override
    protected void beforeInitData() {
        super.beforeInitData();
        Bundle arguments = getArguments();
        if (null != arguments) {
            id = arguments.getString(AppConstant.ID);
        }
        baseRecyclerView.setPadding(0, ArmsUtils.dip2px(mContext, 10), 0, ArmsUtils.dip2px(mContext, 10));
    }

    @Override
    public String getNoMoreTips() {
        return "更多精彩尽请期待!";
    }

    @Override
    public void getPageList(int currentPage) {
        Map<String, String> params = new HashMap<>();
        params.put("columnId", id);
        params.put("isPage", "1");
        params.put("currentPage", String.valueOf(currentPage));
        params.put("pageSize", "10");
        assert mPresenter != null;
        mPresenter.getDiscoveryItemList(Message.obtain(this, params));
    }

    @Override
    public RecyclerView.LayoutManager getLayoutManager() {
        return new LinearLayoutManager(mContext);
    }

    @Override
    public RecyclerArrayAdapter<DiscoveryItemBean> getAdapter() {
        return new DiscoveryHotInfoAdapter(mContext);
    }


    @Override
    public Type getType() {
        return new TypeToken<ResultBean<PageResult<DiscoveryItemBean>>>() {
        }.getType();
    }


}
