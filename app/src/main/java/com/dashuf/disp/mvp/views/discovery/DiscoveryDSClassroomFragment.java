package com.dashuf.disp.mvp.views.discovery;

/**
 * Created by ex-zhoulai on 2018/5/31.
 */

//public class DiscoveryDSClassroomFragment extends BaseRecyclerFragment<DiscoveryItemBean> {
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
//        return new GridLayoutManager(getContext(), 2);
//    }
//
//    @Override
//    public RecyclerArrayAdapter<DiscoveryItemBean> getAdapter() {
//        return new DiscoveryDSClassroomAdapter(mContext);
//    }
//
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
////            WebviewActivity.startThisActivity(mContext, itemBean.getHref());
//        }
//    }
//
//
//}
