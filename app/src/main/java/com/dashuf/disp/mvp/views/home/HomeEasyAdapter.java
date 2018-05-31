package com.dashuf.disp.mvp.views.home;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.dashuf.disp.R;
import com.dashuf.disp.mvp.model.entity.HomeBean;
import com.dashuf.disp.widget.LineDividerItemDecoration;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.jess.arms.utils.ArmsUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by ex-zhoulai on 2018/3/19.
 */

public class HomeEasyAdapter extends DelegateAdapter.Adapter {

    private static final int TITLE = 201;
    private static final int LIST = 202;
    private final ImageLoader imageLoader;
    private Context context;
    private List<HomeBean.EasyProductBean> easyProductList;
    private EasyRecommendAdapter easyRecommendAdapter;

    public HomeEasyAdapter(Context context, List<HomeBean.EasyProductBean> easyProductList) {
        this.context = context;
        this.easyProductList = easyProductList;
        AppComponent appComponent = ArmsUtils.obtainAppComponentFromContext(context);
        imageLoader = appComponent.imageLoader();
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        //        linearLayoutHelper.setMarginTop(AppUtils.dip2px(context, 10));
        return new LinearLayoutHelper();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TITLE) {
            View viewTitle = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_home_title, parent, false);
            return new HolderTitle(viewTitle);
        } else {
            RecyclerView viewList = (RecyclerView) LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_home_new_recommend, parent, false);
            viewList.setLayoutManager(new LinearLayoutManager(parent.getContext(), LinearLayoutManager.HORIZONTAL, false));
            viewList.addItemDecoration(new LineDividerItemDecoration(ArmsUtils.dip2px(context, 10), 0));
            easyRecommendAdapter = new EasyRecommendAdapter();
            viewList.setAdapter(easyRecommendAdapter);
            return new HolderProduct(viewList);
        }
    }


    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return TITLE;
        } else {
            return LIST;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        RecyclerView.LayoutParams layoutParams;
        if (getItemViewType(position) == TITLE) {
//            layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (50 * (AppUtils.getPhoneWidth(holder.itemView.getContext()) * 2) / 700));
//            layoutParams.topMargin = AppUtils.dip2px(context, 10);
            HolderTitle holderTitle = (HolderTitle) holder;
            holderTitle.tvHomeTitle.setText("轻松推荐");

            holderTitle.tvHomeMore.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
//                    if (BaseActivity.isLogin()) {
//                        HomeMoreProductListActivity.startThisActivity(context);
//                    } else {
//                        BaseActivity.reLogin((Activity) context, null);
//                    }
                }
            });
        } else {
            layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            if (null != easyRecommendAdapter) {
                easyRecommendAdapter.notifyDataSetChanged();
            }
            holder.itemView.setLayoutParams(layoutParams);
        }
    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
//            if (BaseActivity.isLogin()) {
//                int position = (int) v.getTag();
//                if (position < easyProductList.size() && easyProductList.get(position) != null) {
//                    WebviewActivity.startThisActivity(v.getContext(), easyProductList.get(position).getLink());
//                }
//            } else {
//                BaseActivity.reLogin((Activity) context, null);
//            }
        }
    };

    @Override
    public int getItemCount() {
        return 2;
    }


    static class HolderTitle extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_home_title)
        TextView tvHomeTitle;
        @BindView(R.id.tv_home_more)
        TextView tvHomeMore;

        HolderTitle(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    static class HolderProduct extends RecyclerView.ViewHolder {
        public HolderProduct(View itemView) {
            super(itemView);
        }
    }

    class EasyRecommendAdapter extends RecyclerView.Adapter<EasyRecommendHolder> {

        @Override
        public EasyRecommendHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new EasyRecommendHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_easy_product, parent, false));
        }

        @Override
        public void onBindViewHolder(EasyRecommendHolder holder, int position) {
            int width = (ArmsUtils.getScreenWidth(context) - ArmsUtils.dip2px(context, 12) * 3 - ArmsUtils.dip2px(context, 30)) / 3;
            RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(width, width * 130 / 104);
            holder.itemView.setLayoutParams(layoutParams);

            HomeBean.EasyProductBean easyProductBean = easyProductList.get(position);

            imageLoader.loadImage(context,
                    ImageConfigImpl
                            .builder()
                            .url(easyProductBean.getImg())
                            .errorPic(R.drawable.place_holder_product_card)
                            .imageView(holder.image)
                            .build());

            holder.name.setText(easyProductBean.getTitle1());
            holder.amount.setText(easyProductBean.getTitle2());
            holder.title.setText(easyProductBean.getTitle3());

            holder.itemView.setTag(position);
            holder.itemView.setOnClickListener(listener);
        }

        @Override
        public int getItemCount() {
            return null == easyProductList || easyProductList.isEmpty() ? 0 : easyProductList.size();
        }

    }

    static class EasyRecommendHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.amount)
        TextView amount;
        @BindView(R.id.title)
        TextView title;

        public EasyRecommendHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
