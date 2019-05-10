package com.dashuf.disp.mvp.views.base;

import com.dashuf.disp.mvp.presenter.BaseRecyclerPresenter;
import com.jess.arms.base.BaseSimpleFragment;
import com.jess.arms.mvp.IView;


public abstract class BaseRecyclerFragment<T> extends BaseSimpleFragment<BaseRecyclerPresenter> implements IView {


//    @BindView(R.id.base_recycler_view)
//    protected RecyclerView baseRecyclerView;
//    @BindView(R.id.base_refresh_layout)
//    protected SmartRefreshLayout baseRefreshLayout;
//    @BindView(R.id.base_load_view)
//    protected XLoadingView baseLoadView;
//
//    protected int mStartPage = 1;
//    protected int mCurrentPage;
//    private Gson mGson;
//
//    protected RecyclerView.Adapter adapter;
//    protected boolean isRefresh = true;
//    protected T headItemBean;
//    private AppComponent appComponent;
//
//
//    @Override
//    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return inflater.inflate(layoutId(), container, false);
//    }
//
//
//    protected int layoutId() {
//        if (hasLoadMore()) {
//            return R.layout.fragment_base_loadmore_recycler;
//        } else {
//            return R.layout.fragment_base_refresh_recycler;
//        }
//    }
//
//    protected boolean hasLoadMore() {
//        return true;
//    }
//
//    protected boolean hasHead() {
//        return false;
//    }
//
//
//    @Override
//    public void initData(@Nullable Bundle savedInstanceState) {
//        beforeInitData();
//        mGson = appComponent.gson();
//        RecyclerView.LayoutManager layoutManager = getLayoutManager();
//        adapter = getAdapter();
//        baseRecyclerView.setLayoutManager(layoutManager);
//
//        baseRecyclerView.setAdapter(adapter);
//        baseRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
//            @Override
//            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
//                refresh();
//            }
//        });
//        baseRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
//            @Override
//            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
//                if (hasLoadMore()) {
//                    loadMore();
//                } else {
//                    baseRefreshLayout.finishLoadMoreWithNoMoreData();
//                }
//            }
//        });
//
//
//        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
//            @Override
//            public void onItemClick(int position) {
//                if (position >= 0 && position < adapter.getCount()) {
//                    onAdapterItemClick(adapter.getItem(position));
//                }
//            }
//        });
//
//        baseLoadView.setOnRetryClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                if (adapter.getCount() == 0) {
//                    baseLoadView.showLoading();
//                }
//                refresh();
//            }
//        });
//        if (adapter.getCount() == 0) {
//            baseLoadView.showLoading();
//        }
//
//        if (hasLoadMore()) {
//            ClassicsFooter footerView = mRootView.findViewById(R.id.base_foot_view);
//            footerView.setVisibility(View.VISIBLE);
//            setNoMoreTips(getNoMoreTips());
//        }
//        refresh();
//    }
//
//    protected void beforeInitData() {
//
//    }
//
//
//    protected void loadMore() {
//        isRefresh = false;
//        getPageList(mCurrentPage);
//    }
//
//    protected void setEmptyText(String emptyText) {
//        baseLoadView.setEmptyText(emptyText);
//    }
//
//    protected void setEmptyImg(int emptyImg) {
//        baseLoadView.setEmptyImg(emptyImg);
//    }
//
//    protected void setErrorText(String errorText) {
//        baseLoadView.setErrorText(errorText);
//    }
//
//    protected void setErrorImg(int errorImg) {
//        baseLoadView.setErrorImg(errorImg);
//    }
//
//    public String getNoMoreTips() {
//        return "";
//    }
//
//    public void setNoMoreTips(String noMoreTips) {
//        if (hasLoadMore()) {
//            ((ClassicsFooter) mRootView.findViewById(R.id.base_foot_view)).setNoMoreDataText(!TextUtils.isEmpty(noMoreTips) ? noMoreTips : getString(com.scwang.smartrefresh.layout.R.string.srl_footer_nothing));
//        }
//    }
//
//    public void refresh() {
//        isRefresh = true;
//        getPageList(mStartPage);
//    }
//
//    protected void onAdapterItemClick(T itemBean) {
//
//    }
//
//    public abstract void getPageList(int currentPage);
//
//
//    @Override
//    public void setData(@Nullable Object data) {
//
//    }
//
//    @Override
//    public void showLoading() {
//
//    }
//
//    @Override
//    public void hideLoading() {
//
//    }
//
//    @Override
//    public void showMessage(@NonNull String message) {
//        Preconditions.checkNotNull(message);
//        ArmsUtils.snackbarText(message);
//
//        baseRefreshLayout.finishRefresh();
//        if (adapter.getCount() == 0) {
//            baseLoadView.showError();
//            baseRefreshLayout.setEnableRefresh(false);
//            baseRefreshLayout.setEnableLoadMore(false);
//        } else {
//            baseLoadView.showContent();
//        }
//    }
//
//    @Nullable
//    @Override
//    public BaseRecyclerPresenter obtainPresenter() {
//        appComponent = ArmsUtils.obtainAppComponentFromContext(mContext);
//        return new BaseRecyclerPresenter(appComponent, this);
//    }
//
//    @Override
//    public void launchActivity(@NonNull Intent intent) {
//        Preconditions.checkNotNull(intent);
//        ArmsUtils.startActivity(intent);
//    }
//
//
//    @Override
//    public void handleMessage(Message msg) {
//        baseRefreshLayout.finishRefresh();
//        baseRefreshLayout.setEnableRefresh(true);
//        baseRefreshLayout.setEnableLoadMore(true);
//        if (msg.what == Api.TAG_ONE) {
//            String response = (String) msg.obj;
//            Type type = getType();
//            if (hasLoadMore()) {
//                handleMoreResult(response, type);
//            } else {
//                handlerNoMoreResult(response, type);
//            }
//        }
//    }
//
//    private void handlerNoMoreResult(String response, Type type) {
//        ResultBean<List<T>> resultBean = mGson.fromJson(response, type);
//        if (null != resultBean && resultBean.isSucceed()) {
//            List<T> list = resultBean.getData();
//            if (null != list) {
//                if (hasHead() && list.size() > 0) {
//                    headItemBean = list.remove(0);
//                }
//                onRefreshSuccess();
//                adapter.clear();
//                adapter.addAll(list);
//
//                if (adapter.getCount() == 0) {
//                    baseLoadView.showEmpty();
//                } else {
//                    baseLoadView.showContent();
//                }
//            }
//        } else {
//            baseLoadView.showError();
//        }
//    }
//
//    private void handleMoreResult(String response, Type type) {
//        ResultBean<PageResult<T>> resultBean = mGson.fromJson(response, type);
//        if (null != resultBean && resultBean.isSucceed()) {
//            PageResult<T> pageResult = resultBean.getData();
//            if (null != pageResult) {
//                int currentPage = pageResult.getCurrentPage();
//                List<T> list = pageResult.getList();
//                if (null != list) {
//                    if (isRefresh) {
//                        if (hasHead() && list.size() > 0) {
//                            headItemBean = list.remove(0);
//                        }
//                        onRefreshSuccess();
//                        adapter.clear();
//                        adapter.addAll(list);
//                    } else {
//                        adapter.addAll(list);
//                    }
//                    if (pageResult.isLastPage()) {
//                        baseRefreshLayout.finishLoadMoreWithNoMoreData();
//                    } else {
//                        baseRefreshLayout.setNoMoreData(false);
//                        baseRefreshLayout.finishLoadMore(true);
//                    }
//                    mCurrentPage = currentPage + 1;
//                }
//            }
//            if (adapter.getCount() == 0) {
//                baseLoadView.showEmpty();
//            } else {
//                baseLoadView.showContent();
//            }
//        } else {
//            baseLoadView.showError();
//        }
//    }
//
//    protected void onRefreshSuccess() {
//
//    }
//
//    public abstract RecyclerView.LayoutManager getLayoutManager();
//
//    public abstract RecyclerArrayAdapter<T> getAdapter();
//
//    public abstract Type getType();
}
