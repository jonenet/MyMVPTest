package com.dashuf.disp.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;

import com.jude.rollviewpager.Util;
import com.jude.rollviewpager.hintview.ShapeHintView;

/**
 * Created by ex-zhoulai on 2018/4/27.
 */

public class MyPointHintView extends ShapeHintView {

    private int focusColor;
    private int normalColor;

    public MyPointHintView(Context context, int focusColor, int normalColor) {
        super(context);
        this.focusColor = focusColor;
        this.normalColor = normalColor;
    }

    public Drawable makeFocusDrawable() {
        GradientDrawable dot_focus = new GradientDrawable();
        dot_focus.setColor(this.focusColor);
        dot_focus.setCornerRadius((float) Util.dip2px(this.getContext(), 4.0F));
        dot_focus.setSize(Util.dip2px(this.getContext(), 7.0F), Util.dip2px(this.getContext(), 7.0F));
        return dot_focus;
    }

    public Drawable makeNormalDrawable() {
        GradientDrawable dot_normal = new GradientDrawable();
        dot_normal.setColor(this.normalColor);
        dot_normal.setCornerRadius((float) Util.dip2px(this.getContext(), 4.0F));
        dot_normal.setSize(Util.dip2px(this.getContext(), 7.0F), Util.dip2px(this.getContext(), 7.0F));
        return dot_normal;
    }

}
