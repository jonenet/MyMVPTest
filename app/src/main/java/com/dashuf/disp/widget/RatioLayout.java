package com.dashuf.disp.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;

/**
 * Description:
 * Author     : jone
 * Date       : 2018/3/15 17:01
 */
public class RatioLayout extends FrameLayout {

    private float ratio = 2f;

    public RatioLayout(Context context) {
        this(context, null);
    }


    public RatioLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RatioLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    //onMeasure--onLayout--onDraw
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        //1 宽度大小   2宽度模式
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);

        int heightSize ;
        int hegihtMode = MeasureSpec.getMode(heightMeasureSpec);

        //Mode的几个值
        //AT_MOST:至多的模式，布局中wrap_content
        //EXACTLY：确定的模式,布局中写死多少dp或者match_parent
        //UNSPECIFIED：ScrollView或者ListView
        if (ratio != 0 && widthMode == MeasureSpec.EXACTLY && hegihtMode != MeasureSpec.EXACTLY) {
            int innerWidth = widthSize - getPaddingLeft() - getPaddingRight();
            heightSize = (int) (innerWidth / ratio);
            heightSize = heightSize + getPaddingTop() + getPaddingBottom();
            //如果这个控件有孩子，就不能简单的调用setMeasuredDimension
            //setMeasuredDimension(widthSize, heightSize);
            //重新生成measurespec
            heightMeasureSpec = MeasureSpec.makeMeasureSpec(heightSize, MeasureSpec.EXACTLY);
        }


        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


}