package com.jess.arms.widget.loadingview;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jess.arms.R;


/**
 * 简单实用的页面状态统一管理 ，加载中、无网络、无数据、出错等状态的随意切换
 */
public class XLoadingView extends FrameLayout {

    private int mEmptyViewResId;
    private int mErrorViewResId;
    private int mLoadingViewResId;
    private int mNoNetworkViewResId;
    private int mContentViewResId;
    private LayoutInflater mInflater;
    private OnClickListener mOnRetryClickListener;
    private SparseArray<View> sparseArray = new SparseArray<>();

    public static XLoadingViewConfig config = new XLoadingViewConfig();
    private String emptyText;
    private int emptyImg;
    private int errorImg;
    private String errorText;

    public static XLoadingViewConfig init() {
        return config;
    }

    public static XLoadingView wrap(Activity activity) {
        return wrap(((ViewGroup) activity.findViewById(android.R.id.content)).getChildAt(0));
    }

    public static XLoadingView wrap(Fragment fragment) {
        return wrap(fragment.getView());
    }

    public static XLoadingView wrap(View view) {
        if (view == null) {
            throw new RuntimeException("content view can not be null");
        }
        ViewGroup parent = (ViewGroup) view.getParent();
        if (parent == null) {
            throw new RuntimeException("parent view can not be null");
        }
        ViewGroup.LayoutParams lp = view.getLayoutParams();
        int index = parent.indexOfChild(view);
        parent.removeView(view);

        XLoadingView xLoadingView = new XLoadingView(view.getContext());
        parent.addView(xLoadingView, index, lp);
        xLoadingView.addView(view);
        xLoadingView.setContentView(view);
        return xLoadingView;
    }

    public XLoadingView(Context context) {
        this(context, null);
    }

    public XLoadingView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public XLoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mInflater = LayoutInflater.from(context);
        @SuppressLint("CustomViewStyleable") TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.bk_loadingView, defStyleAttr, 0);
        mEmptyViewResId = a.getResourceId(R.styleable.bk_loadingView_emptyView, config.getEmptyViewResId());
        mErrorViewResId = a.getResourceId(R.styleable.bk_loadingView_errorView, config.getErrorViewResId());
        mLoadingViewResId = a.getResourceId(R.styleable.bk_loadingView_loadingView, config.getLoadingViewResId());
        mNoNetworkViewResId = a.getResourceId(R.styleable.bk_loadingView_noNetworkView, config.getNoNetworkViewResId());
        a.recycle();
    }

    private void setContentView(View view) {
        mContentViewResId = view.getId();
        sparseArray.put(mContentViewResId, view);
    }

    public final void showEmpty() {
        show(mEmptyViewResId);
    }

    public final void showError() {
        show(mErrorViewResId);
    }

    public final void showLoading() {
        show(mLoadingViewResId);
    }

    public final void showNoNetwork() {
        show(mNoNetworkViewResId);
    }

    public final void showContent() {
        show(mContentViewResId);
    }

    private void show(int resId) {
        for (int i = 0; i < sparseArray.size(); i++) {
            sparseArray.valueAt(i).setVisibility(GONE);
        }
        layout(resId).setVisibility(VISIBLE);
    }

    private View layout(int resId) {
        if (null != sparseArray.get(resId)) {
            return sparseArray.get(resId);
        }
        View view = mInflater.inflate(resId, this, false);
        view.setVisibility(GONE);
        addView(view);
        sparseArray.put(resId, view);
        if (resId == mErrorViewResId) {
            View v = view.findViewById(R.id.retry);
            if (mOnRetryClickListener != null) {
                if (v != null)
                    v.setOnClickListener(mOnRetryClickListener);
                else
                    view.setOnClickListener(mOnRetryClickListener);
            }

            TextView tvError = view.findViewById(R.id.tv_network_error);
            ImageView ivError = view.findViewById(R.id.iv_network_error);
            if (!TextUtils.isEmpty(errorText) && null != tvError) {
                tvError.setText(errorText);
            }
            if (errorImg != 0 && null != ivError) {
                Glide.with(getContext()).load(errorImg).into(ivError);
            }

        } else if (resId == mNoNetworkViewResId) {
            View v = view.findViewById(R.id.retry);
            if (mOnRetryClickListener != null) {
                if (v != null)
                    v.setOnClickListener(mOnRetryClickListener);
                else
                    view.setOnClickListener(mOnRetryClickListener);
            }

            TextView tvError = view.findViewById(R.id.tv_network_error);
            ImageView ivError = view.findViewById(R.id.iv_network_error);
            if (!TextUtils.isEmpty(errorText) && null != tvError) {
                tvError.setText(errorText);
            }
            if (errorImg != 0 && null != ivError) {
                Glide.with(getContext()).load(errorImg).into(ivError);
            }

        } else if (resId == mEmptyViewResId) {
            TextView tvEmpty = view.findViewById(R.id.tv_empty);
            ImageView ivEmpty = view.findViewById(R.id.iv_empty);
            if (!TextUtils.isEmpty(emptyText) && null != tvEmpty) {
                tvEmpty.setText(emptyText);
            }
            if (emptyImg != 0 && null != ivEmpty) {
                Glide.with(getContext()).load(emptyImg).into(ivEmpty);
            }

        }
        return view;
    }

    public void setEmptyText(String emptyText) {
        this.emptyText = emptyText;
    }

    public void setEmptyImg(int emptyImg) {
        this.emptyImg = emptyImg;
    }

    public void setErrorText(String errorText) {
        this.errorText = errorText;
    }

    public void setErrorImg(int errorImg) {
        this.errorImg = errorImg;
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        if (getChildCount() == 0) {
            return;
        }
        if (getChildCount() > 1) {
            removeViews(1, getChildCount() - 1);
        }
        View view = getChildAt(0);
        setContentView(view);
        showLoading();
    }

    /**
     * 设置重试点击事件
     *
     * @param onRetryClickListener 重试点击事件
     */
    public void setOnRetryClickListener(OnClickListener onRetryClickListener) {
        this.mOnRetryClickListener = onRetryClickListener;
    }
}
