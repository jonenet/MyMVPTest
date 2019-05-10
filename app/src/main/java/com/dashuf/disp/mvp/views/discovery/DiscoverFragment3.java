package com.dashuf.disp.mvp.views.discovery;

/**
 * Created by ex-zhoulai on 2018/5/29.
 */

//public class DiscoverFragment3 extends BaseSimpleFragment<DiscoveryPresenter> implements IView {
//
//    @BindView(R.id.toolbar)
//    TextView toolbar;
//    @BindView(R.id.tab_layout_discovery)
//    TabLayout tabLayoutDiscovery;
//    @BindView(R.id.view_pager_discovery)
//    ViewPager viewPagerDiscovery;
//    @BindView(R.id.load_view_discovery_news)
//    XLoadingView loadViewDiscoveryNews;
//
//    private BaseViewPagerAdapter adapter;
//    private List<BaseViewPagerAdapter.PagerInfo> fragmentList = new ArrayList<>();
//
//
//    @Override
//    public void handleMessage(Message msg) {
//        if (msg.what == Api.TAG_ONE) {
//            List<DiscoveryTopTabBean> discoveryTopTabBeanList = (List<DiscoveryTopTabBean>) msg.obj;
//            if (null != discoveryTopTabBeanList) {
//                fragmentList = getFragmentList(discoveryTopTabBeanList);
////            adapter.notifyDataSetChanged();
//                adapter.setFragments(fragmentList);
//                if (discoveryTopTabBeanList.size() > 4) {
//                    tabLayoutDiscovery.setTabMode(TabLayout.MODE_SCROLLABLE);
//                } else {
//                    tabLayoutDiscovery.setTabMode(TabLayout.MODE_FIXED);
//                }
//            }
//
//        }
//        showError(false);
//    }
//
//    @Nullable
//    @Override
//    public DiscoveryPresenter obtainPresenter() {
//        return new DiscoveryPresenter(ArmsUtils.obtainAppComponentFromContext(mContext), this);
//    }
//
//
//    @Override
//    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return LayoutInflater.from(mContext).inflate(R.layout.fragment_discovery, container, false);
//    }
//
//    @Override
//    public void initData(@Nullable Bundle savedInstanceState) {
//        toolbar.setTextColor(Color.BLACK);
//        toolbar.setText(R.string.bottom_tab_3);
//        StatusBarUtil.StatusBarDarkMode(mContext);
//        loadViewDiscoveryNews.showLoading();
//        loadViewDiscoveryNews.setOnRetryClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                requestPageList();
//            }
//        });
//
//        viewPagerDiscovery.setOffscreenPageLimit(2);
//        adapter = new BaseViewPagerAdapter(getContext(), getChildFragmentManager(), fragmentList);
//        viewPagerDiscovery.setAdapter(adapter);
//        tabLayoutDiscovery.setupWithViewPager(viewPagerDiscovery);
//        requestPageList();
//    }
//
//    public void requestPageList() {
//        Map<String, String> params = new HashMap<>();
//        params.put("isRootColumnLike", "1");
//        params.put("rootCode", "fxmk");
//        params.put("level", "1");
//        mPresenter.requestDiscoveryPageList(Message.obtain(DiscoverFragment3.this, params));
//    }
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
//        showError(true);
//    }
//
//    @Override
//    public void launchActivity(@NonNull Intent intent) {
//        Preconditions.checkNotNull(intent);
//        ArmsUtils.startActivity(intent);
//    }
//
//    private List<BaseViewPagerAdapter.PagerInfo> getFragmentList(List<DiscoveryTopTabBean> result) {
//        fragmentList.clear();
//        for (int i = 0; i < result.size(); i++) {
//            DiscoveryTopTabBean dataBean = result.get(i);
//            Bundle bundle = new Bundle();
//            bundle.putString(AppConstant.ID, dataBean.getId());
//            BaseViewPagerAdapter.PagerInfo pagerInfo = new BaseViewPagerAdapter.PagerInfo(dataBean.getName(), getFragment(dataBean.getCode()), bundle);
//            fragmentList.add(pagerInfo);
//        }
//        return fragmentList;
//    }
//
//    public Class getFragment(String type) {
//        Class clazz;
//        switch (type) {
//            case "zxzx":   //最新资讯
//                clazz = DiscoveryNewsFragment.class;
//                break;
//            case "dskt":   //大神课堂
//                clazz = DiscoveryDSClassroomFragment.class;
//                break;
//            case "rdzx":   //热点资讯
//                clazz = DiscoveryHotInfoFragment.class;
//                break;
//            case "cpjl":    //产品交流
//                clazz = DiscoveryProductExchangeFragment.class;
//                break;
//            case "rdhd":   //热点活动
//                clazz = DiscoveryHotSpotsFragment.class;
//                break;
//            default:
//                clazz = DiscoveryNewsFragment.class;
//                break;
//        }
////        return clazz;
//        return clazz;
//    }
//
//
//    public void showError(boolean showError) {
//        if (showError && fragmentList.isEmpty()) {
//            loadViewDiscoveryNews.showError();
//        } else {
//            loadViewDiscoveryNews.showContent();
//        }
//    }
//}
