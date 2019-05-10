package com.dashuf.disp.mvp.views.home;

import android.database.DataSetObserver;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;

import com.alibaba.android.vlayout.RecyclablePagerAdapter;


public abstract class HomeLooperAdapter extends RecyclablePagerAdapter<RecyclerView.ViewHolder> {

    private ViewPager mViewPager;

    HomeLooperAdapter(RecyclerView.Adapter<RecyclerView.ViewHolder> adapter, RecyclerView.RecycledViewPool pool, ViewPager mViewPager) {
        super(adapter, pool);
//        this.mViewPager = mViewPager;
    }


    @Override
    public int getCount() {
        return Character.MAX_VALUE;
//        return this.getRealCount() <= 1 ? this.getRealCount() : Character.MAX_VALUE;
    }


    @Override
    public int getItemViewType(int position) {
        return 0;
    }


//    private ArrayList<View> mViewList = new ArrayList<>();

//    public void notifyDataSetChanged() {
//        super.notifyDataSetChanged();
//        this.mViewList.clear();
//    }

    public void registerDataSetObserver(@NonNull DataSetObserver observer) {
        super.registerDataSetObserver(observer);
        if (this.getRealCount() != 0) {
            int half = 100 / 2;
            int start = half - half % this.getRealCount();
//            this.mViewPager.getViewPager().setCurrentItem(start, false);
        }
    }


    protected abstract int getRealCount();


}
