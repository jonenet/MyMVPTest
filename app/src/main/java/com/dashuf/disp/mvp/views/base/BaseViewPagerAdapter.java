package com.dashuf.disp.mvp.views.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 * Copyright  : Copyright (c) 2016
 * Company    :
 * Author     : jone
 * Date       : 2018/3/9 10:25
 */

public class BaseViewPagerAdapter extends FragmentPagerAdapter {
    private final Context context;
    private List<PagerInfo> mInfoList;
    private Fragment mCurFragment;

    private ArrayList<Fragment> mFragmentList;
    private FragmentManager mFragmentManager;

    public BaseViewPagerAdapter(Context context, FragmentManager fm, List<PagerInfo> infoList) {
        super(fm);
        this.context = context;
        mInfoList = infoList;
        this.mFragmentManager = fm;
        setFragments(infoList);
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        super.setPrimaryItem(container, position, object);
        if (object instanceof Fragment) {
            mCurFragment = (Fragment) object;
        }
    }

    public Fragment getCurFragment() {
        return mCurFragment;
    }

    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return null == mFragmentList ? 0 : mFragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mInfoList.get(position).title;
    }


    public void setFragments(List<PagerInfo> infoList) {
        if (this.mFragmentList != null) {
            FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
            for (Fragment f : this.mFragmentList) {
                fragmentTransaction.remove(f);
            }
            fragmentTransaction.commit();
            mFragmentManager.executePendingTransactions();
        }
        ArrayList<Fragment> fragmentList = new ArrayList<>();
        for (int i = 0, size = infoList.size(); i < size; i++) {
            PagerInfo info = infoList.get(i);
            fragmentList.add(FragmentFactory.getFragment(context, info.clx.getName(), info.args));
        }
        this.mFragmentList = fragmentList;
        notifyDataSetChanged();

    }

    @Override
    public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
    }


    public static class PagerInfo {
        private String title;
        private Class<?> clx;
        private Bundle args;

        public PagerInfo(String title, Class<?> clx, Bundle args) {
            this.title = title;
            this.clx = clx;
            this.args = args;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Class<?> getClx() {
            return clx;
        }

        public void setClx(Class<?> clx) {
            this.clx = clx;
        }

        public Bundle getArgs() {
            return args;
        }

        public void setArgs(Bundle args) {
            this.args = args;
        }
    }

}
