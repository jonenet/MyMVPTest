package com.dashuf.disp.mvp.views.discovery;

/**
 * Created by ex-zhoulai on 2018/6/1.
 */

//public class DiscoveryHotSpotsFragment extends BaseRecyclerFragment<DiscoveryItemBean> {
//
//    private String id;
//
//    @Override
//    protected void beforeInitData() {
//        super.beforeInitData();
//        Bundle arguments = getArguments();
//        if (null != arguments) {
//            id = arguments.getString(AppConstant.ID);
//        }
//        baseRecyclerView.setPadding(0, ArmsUtils.dip2px(mContext, 10), 0, ArmsUtils.dip2px(mContext, 10));
//    }
//
//    @Override
//    public String getNoMoreTips() {
//        return "更多精彩尽请期待!";
//    }
//
//    @Override
//    public void getPageList(int currentPage) {
//        Map<String, String> params = new HashMap<>();
//        params.put("columnId", id);
//        params.put("isPage", "1");
//        params.put("currentPage", String.valueOf(currentPage));
//        params.put("pageSize", "10");
//        assert mPresenter != null;
//        mPresenter.getDiscoveryItemList(Message.obtain(this, params));
//    }
//
//
//    @Override
//    public RecyclerView.LayoutManager getLayoutManager() {
//        return new LinearLayoutManager(mContext);
//    }
//
//    @Override
//    public RecyclerArrayAdapter<DiscoveryItemBean> getAdapter() {
//        return new DiscoveryHotSpotsAdapter(mContext);
//    }
//
//    @Override
//    public Type getType() {
//        return new TypeToken<ResultBean<PageResult<DiscoveryItemBean>>>() {
//        }.getType();
//    }
//
//    @Override
//    public void onAdapterItemClick(DiscoveryItemBean itemBean) {
//        if (null != itemBean) {
////            WebviewActivity.startThisActivity(mBaseActivity, itemBean.getHref());
//        }
//    }
//
//
//}
