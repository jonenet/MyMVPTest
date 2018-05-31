package com.dashuf.disp.mvp.views;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.dashuf.disp.R;
import com.dashuf.disp.mvp.views.discovery.DiscoverFragment3;
import com.dashuf.disp.mvp.views.home.HomeFragment6;
import com.dashuf.disp.mvp.views.home.MineFragment;
import com.dashuf.disp.mvp.views.home.ToolFragment;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.base.BaseSimpleActivity;
import com.jess.arms.mvp.IPresenter;
import com.jess.arms.mvp.Message;
import com.jess.arms.utils.StatusBarUtil;

import butterknife.BindView;

/**
 * Created by ex-zhoulai on 2018/5/29.
 */

public class HomeActivity extends BaseSimpleActivity {

    @BindView(R.id.fragment_container)
    FrameLayout fragmentContainer;
    @BindView(R.id.bottom_navigation)
    AHBottomNavigation bottomNavigation;
    @BindView(R.id.activity_main)
    RelativeLayout activityMain;
    private BaseFragment currentFragment;
    //    private HomeFragment6 homeFragment;
//    private MineFragment mineFragment;
    //    private BusinessGuideListFragment businessGuideListFragment;
//    private DiscoverFragment3 discoverFragment;
//    private ToolFragment toolFragment;
    private FragmentManager fragmentManager = getSupportFragmentManager();
    private FragmentTransaction transaction;

    public int currentTab = 0;
    private MenuItem msg;
    private MenuItem loan;

    private int guideResourceId = R.drawable.index_guide1;
    private HomeFragment6 homeFragment;
    private DiscoverFragment3 discoverFragment;
    private ToolFragment toolFragment;
    private MineFragment mineFragment;


    @Override
    public void handleMessage(Message msg) {

    }

    @Nullable
    @Override
    public IPresenter obtainPresenter() {
        return null;
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_home;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        StatusBarUtil.setTranslucentForImageView(this, R.color.transparent);
        initBottomNavigation();
        setFragments();
    }

    @Override
    public void killMyself() {
        finish();
    }

    private void initBottomNavigation() {
        // Create items
        AHBottomNavigationItem item0 = new AHBottomNavigationItem(R.string.bottom_tab_0, R.drawable.bottom_tab_0, R.color.white);
        AHBottomNavigationItem item1 = new AHBottomNavigationItem(R.string.bottom_tab_1, R.drawable.bottom_tab_1, R.color.white);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem(R.string.bottom_tab_2, R.drawable.bottom_tab_2, R.color.white);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem(R.string.bottom_tab_3, R.drawable.bottom_tab_3, R.color.white);
        AHBottomNavigationItem item4 = new AHBottomNavigationItem(R.string.bottom_tab_4, R.drawable.bottom_tab_4, R.color.white);

        //设置为中间大图标
        item0.setSuperIco(true);

        //设置不使用tint图标 并分别设置active inactive 图标
        item0.setUseTintDrawable(false);
        item1.setUseTintDrawable(false);
        item2.setUseTintDrawable(false);
        item3.setUseTintDrawable(false);
        item4.setUseTintDrawable(false);

        item0.setActiveDrawable(R.drawable.bottom_tab_0);
        item0.setInactiveDrawable(R.drawable.bottom_tab_0);

        item1.setActiveDrawable(R.drawable.bottom_tab_1_active);
        item1.setInactiveDrawable(R.drawable.bottom_tab_1);

        item2.setActiveDrawable(R.drawable.bottom_tab_2_active);
        item2.setInactiveDrawable(R.drawable.bottom_tab_2);

        item3.setActiveDrawable(R.drawable.bottom_tab_3_active);
        item3.setInactiveDrawable(R.drawable.bottom_tab_3);

        item4.setActiveDrawable(R.drawable.bottom_tab_4_active);
        item4.setInactiveDrawable(R.drawable.bottom_tab_4);

        // Add items
        bottomNavigation.addItem(item1);
        bottomNavigation.addItem(item2);
        bottomNavigation.addItem(item0);
        bottomNavigation.addItem(item3);
        bottomNavigation.addItem(item4);

        // Set background color
        bottomNavigation.setDefaultBackgroundColor(ContextCompat.getColor(this, R.color.white));

        // Disable the translation inside the CoordinatorLayout
        bottomNavigation.setBehaviorTranslationEnabled(false);

        // Change colors
        bottomNavigation.setInactiveColor(ContextCompat.getColor(this, R.color.gray_dark_bg));
        bottomNavigation.setAccentColor(ContextCompat.getColor(this, R.color.colorPrimary));

        // Force to tint the drawable (useful for font with icon for example)
        bottomNavigation.setForceTint(false);

        // Force the titles to be displayed (against Material Design guidelines!)
//        bottomNavigation.setForceTitlesDisplay(true);
        bottomNavigation.setTitleState(AHBottomNavigation.TitleState.ALWAYS_SHOW);

        // Use colored navigation with circle reveal effect
        bottomNavigation.setColored(false);

        // Set current item programmatically
        bottomNavigation.setCurrentItem(0);

    }


    private void setFragments() {
        transaction = fragmentManager.beginTransaction();
        if (homeFragment == null) {
            homeFragment = new HomeFragment6();
            transaction.add(R.id.fragment_container, homeFragment, "homeFragment");
        } else {
            transaction.show(homeFragment);
        }
        transaction.commit();
        currentFragment = homeFragment;
        bottomNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                if (!wasSelected) {
                    return showFragment(position);
                }
                return true;
            }
        });
    }


    private boolean showFragment(int position) {
        transaction = fragmentManager.beginTransaction();
        homeFragment = (HomeFragment6) fragmentManager.findFragmentByTag("homeFragment");
        toolFragment = (ToolFragment) fragmentManager.findFragmentByTag("toolFragment");
        discoverFragment = (DiscoverFragment3) fragmentManager.findFragmentByTag("discoverFragment");
        mineFragment = (MineFragment) fragmentManager.findFragmentByTag("mineFragment");

        switch (position) {
            case 0:
//                setTitle("评估神");
//                toolbar.setTitle("评估神");
                if (msg != null) {
                    msg.setVisible(true);
                }
                if (loan != null) {
                    loan.setVisible(false);
                }

                if (homeFragment == null) {
                    homeFragment = new HomeFragment6();
                    transaction.add(R.id.fragment_container, homeFragment, "homeFragment");
                }

//                float alpha = 0;
//                float scale = 0;
//                int height = (int) AppUtils.getActionBarHeight(this) + AppUtils.getStatusBarHeight(this);
//
//                if (homeFragment.overScrollY <= height) {
//                    scale = (float) homeFragment.overScrollY / height;
//                    alpha = (float) (255 * scale);
//                    // 随着滑动距离改变透明度
//                    // Log.e("al=","="+alpha);
//                } else {
//                    if (alpha < 255) {
//                        // 防止频繁重复设置相同的值影响性能
//                        alpha = 255;
//                    }
//                }
//                homeFragment.setActionBarAlpha(alpha);
                currentFragment = homeFragment;
                break;
            case 1:
//                StatusBarUtil.setTranslucentForImageView(this, R.color.transparent);
//                StatusBarUtil.StatusBarLightMode(this);
//                showActionBar(false, false);
                if (msg != null) {
                    msg.setVisible(false);
                }
                if (loan != null) {
                    loan.setVisible(false);
                }
                if (toolFragment == null) {
                    toolFragment = new ToolFragment();
                    transaction.add(R.id.fragment_container, toolFragment, "toolFragment");
                }
                currentFragment = toolFragment;
                break;
            case 2:
//                StatusBarUtil.setTranslucentForImageView(this, R.color.transparent);
//                StatusBarUtil.StatusBarLightMode(this);
//                TalkDataBusiness.addRecord(this, "10-07", "点击智能评估");

//                if (BaseActivity.isLogin()) {
//                    if (TextUtils.isEmpty(BaseActivity.assessmentSwitch)) {
//                        if (homeFragment != null) {
//                            homeFragment.requestSwith(true);
//                        }
//                    } else {
//                        homeFragment.openAssessment(BaseActivity.assessmentSwitch);
//                    }
//                    //TODO
//                } else {
//                    BaseActivity.reLogin(this, null);
//                }
                return false;
            case 3:
//                StatusBarUtil.setTranslucentForImageView(this, R.color.transparent);
//                StatusBarUtil.StatusBarLightMode(this);
                if (msg != null) {
                    msg.setVisible(false);
                }
                if (loan != null) {
                    loan.setVisible(false);
                }

                if (discoverFragment == null) {
                    discoverFragment = new DiscoverFragment3();
                    transaction.add(R.id.fragment_container, discoverFragment, "discoverFragment");
                }
                currentFragment = discoverFragment;
                break;
            case 4:
//                StatusBarUtil.StatusBarDarkMode(this, StatusBarUtil.StatusBarLightMode(this));
//                StatusBarUtil.setTranslucentForImageView(this, R.color.transparent);
                if (msg != null) {
                    msg.setVisible(false);
                }
                if (loan != null) {
                    loan.setVisible(false);
                }
                if (mineFragment == null) {
                    mineFragment = new MineFragment();
                    transaction.add(R.id.fragment_container, mineFragment, "mineFragment");
                }
                currentFragment = mineFragment;
                break;
            default:
                currentFragment = homeFragment;
                break;
        }

        if (homeFragment != null) {
            transaction.hide(homeFragment);
        }
        if (toolFragment != null) {
            transaction.hide(toolFragment);
        }

        if (discoverFragment != null) {
            transaction.hide(discoverFragment);
        }
        if (mineFragment != null) {
            transaction.hide(mineFragment);
        }
        transaction.show(currentFragment);

        currentTab = position;
        transaction.commitAllowingStateLoss();
        return true;
    }


}
