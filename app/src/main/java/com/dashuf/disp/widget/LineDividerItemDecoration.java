package com.dashuf.disp.widget;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class LineDividerItemDecoration extends RecyclerView.ItemDecoration {
    private int mLeftSpace = 0;
    private int mTopSpace = 0;

    public LineDividerItemDecoration(int mLeftSpace, int mTopSpace) {
        this.mLeftSpace = mLeftSpace;
        this.mTopSpace = mTopSpace;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

//        //如果不是第一个，则设置top的值。
        if (parent.getChildAdapterPosition(view) != 0) {
            if (mLeftSpace > 0) {
                outRect.left = mLeftSpace;
            }
            if (mTopSpace > 0) {
                outRect.top = mTopSpace;
            }
            if (parent.getChildAdapterPosition(view) == parent.getAdapter().getItemCount() - 1) {
                outRect.right = mLeftSpace;
            }

        } else {
            if (mLeftSpace > 0) {
                outRect.left = mLeftSpace;
            }
        }
    }

}