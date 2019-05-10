package com.dashuf.disp.mvp.views.home;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
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
import com.dashuf.disp.utils.GlideRoundTransform;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.http.imageloader.ImageLoader;
import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.jess.arms.utils.ArmsUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ex-zhoulai on 2018/3/19.
 * 首页热门推荐 adapter
 */

public class HomeNewRecommendAdapter extends DelegateAdapter.Adapter {

    private static final int TITLE = 101;
    private static final int LIST = 102;
    private Context context;
    private List<HomeBean.NewProductBean> hotProductList;
    private AppComponent mAppComponent;
    private ImageLoader mImageLoader;

    public HomeNewRecommendAdapter(Context context, List<HomeBean.NewProductBean> hotProductList) {
        this.context = context;
        this.hotProductList = hotProductList;
        mAppComponent = ArmsUtils.obtainAppComponentFromContext(context);
        mImageLoader = mAppComponent.imageLoader();
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        LinearLayoutHelper linearLayoutHelper = new LinearLayoutHelper();
        linearLayoutHelper.setMarginBottom(ArmsUtils.dip2px(context, 10));
//        linearLayoutHelper.setBgColor(Color.WHITE);
        return linearLayoutHelper;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == TITLE) {
            View viewTitle = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_home_title, parent, false);
            return new HolderTitle(viewTitle);
        } else {
            View viewList = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_brand_product, parent, false);
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
        if (getItemViewType(position) == LIST) {
            HomeBean.NewProductBean newProductBean = hotProductList.get(position - 1);
            HolderProduct holderProduct = (HolderProduct) holder;

//            Glide.with(context).load(newProductBean.getImg())
//                    .bitmapTransform(new GlideRoundTransform(context, ArmsUtils.dip2px(context, 4), GlideRoundTransform.CornerType.ALL))
//                    .placeholder(R.drawable.place_holder_product_list)
//                    .error(R.drawable.place_holder_product_list)
//                    .into(holderProduct.productFace);

            mImageLoader.loadImage(context,
                    ImageConfigImpl
                            .builder()
                            .transformation(new GlideRoundTransform(context, ArmsUtils.dip2px(context, 4), GlideRoundTransform.CornerType.ALL))
                            .url(newProductBean.getImg())
                            .imageView(holderProduct.productFace)
                            .build());

            holderProduct.productName.setText(newProductBean.getMainTitle());
            String title1 = newProductBean.getTitle1();
            String title2 = newProductBean.getTitle2();

            if (TextUtils.isEmpty(title1)) {
                holderProduct.productTag.setVisibility(View.GONE);
            } else {
                holderProduct.productTag.setVisibility(View.VISIBLE);

                if (newProductBean.getTitle1Color() != null && newProductBean.getTitle1Color().startsWith("#")) {
                    holderProduct.productTag.setTextColor(Color.parseColor(newProductBean.getTitle1Color()));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        holderProduct.productTag.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(newProductBean.getTitle1Color())));
                    }
                }
            }
            if (TextUtils.isEmpty(title2)) {
                holderProduct.productTag2.setVisibility(View.GONE);
            } else {
                holderProduct.productTag2.setVisibility(View.VISIBLE);
                if (newProductBean.getTitle2Color() != null && newProductBean.getTitle2Color().startsWith("#")) {
                    holderProduct.productTag2.setTextColor(Color.parseColor(newProductBean.getTitle2Color()));
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        holderProduct.productTag2.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(newProductBean.getTitle2Color())));
                    }
                }
            }

            holderProduct.productTag.setText(title1);
            holderProduct.productTag2.setText(title2);


            holderProduct.productContent.setText(newProductBean.getViceTitle());

            String sort = newProductBean.getSort();
            if (!TextUtils.isEmpty(sort)) {
                switch (sort) {
                    case "1":
                        holderProduct.productDate.setText(newProductBean.getTitle3());
                        holderProduct.productDateValue.setText(newProductBean.getTitle4());
                        break;
                    default:
                        holderProduct.productDate.setText(newProductBean.getTitle3());
                        holderProduct.productDateValue.setText(newProductBean.getTitle4());
                        break;
                }

            } else {
                holderProduct.productDate.setVisibility(View.GONE);
                holderProduct.productDateValue.setVisibility(View.GONE);
            }
            holder.itemView.setTag(position - 1);
            holder.itemView.setOnClickListener(listener);
            if (position == hotProductList.size()) {
                holderProduct.viewBottomLine.setVisibility(View.INVISIBLE);
            } else {
                holderProduct.viewBottomLine.setVisibility(View.VISIBLE);
            }

        } else {
            HolderTitle holderTitle = (HolderTitle) holder;
            holderTitle.tvHomeTitle.setText("新品推荐");
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
        }
    }

    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
//            if (BaseActivity.isLogin()) {
//                int position = (int) v.getTag();
//                if (position >= 0 && position < hotProductList.size() && hotProductList.get(position) != null) {
//                    WebviewActivity.startThisActivity(context, hotProductList.get(position).getLink());
//                }
//            } else {
//                BaseActivity.reLogin((Activity) context, null);
//            }
        }
    };

    @Override
    public int getItemCount() {
        return null == hotProductList || hotProductList.isEmpty() ? 0 : hotProductList.size() + 1;
//        return null == hotProductList || hotProductList.isEmpty() ? 0 : 10;
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
        @BindView(R.id.product_face)
        ImageView productFace;
        @BindView(R.id.product_name)
        TextView productName;
        @BindView(R.id.product_tag)
        TextView productTag;
        @BindView(R.id.product_tag2)
        TextView productTag2;
        @BindView(R.id.product_tag3)
        TextView productTag3;
        @BindView(R.id.product_content)
        TextView productContent;
        @BindView(R.id.product_date)
        TextView productDate;
        @BindView(R.id.product_date_value)
        TextView productDateValue;
        @BindView(R.id.btn_push)
        TextView btnPush;
        @BindView(R.id.view_bottom_line)
        View viewBottomLine;

        public HolderProduct(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

    }


}
