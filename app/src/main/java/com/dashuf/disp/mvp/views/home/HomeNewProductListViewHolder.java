package com.dashuf.disp.mvp.views.home;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dashuf.disp.R;
import com.dashuf.disp.mvp.model.entity.HomeBean;
import com.jess.arms.http.imageloader.glide.ImageConfigImpl;
import com.jess.arms.utils.ArmsUtils;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;


/**
 * Created by Mr.Jude on 2015/2/22.
 */
public class HomeNewProductListViewHolder extends BaseViewHolder<HomeBean.NewProductBean> {


    ImageView productFace;
    TextView productName;
    TextView productTag;
    TextView productTag2;
    TextView productContent;
    TextView productDate;
    TextView productDateValue;
    TextView btnPush;

    public HomeNewProductListViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_brand_product);
        productFace = $(R.id.product_face);
        productName = $(R.id.product_name);
        productTag = $(R.id.product_tag);
        productTag2 = $(R.id.product_tag2);
        productContent = $(R.id.product_content);
        productDate = $(R.id.product_date);
        productDateValue = $(R.id.product_date_value);
        btnPush = $(R.id.btn_push);
    }

    @Override
    public void setData(final HomeBean.NewProductBean data) {

//        Glide.with(getContext()).
//                load(data.getImg()).
////                    placeholder(R.drawable.dangkr_no_picture_small).
////                    error(R.drawable.dangkr_no_picture_small).
//        fitCenter().into(productFace);

        productName.setText(data.getMainTitle());
        ArmsUtils.obtainAppComponentFromContext(getContext()).imageLoader().loadImage(getContext(),
                ImageConfigImpl
                        .builder()
                        .url(data.getImg())
                        .errorPic(R.drawable.place_holder_product_list)
                        .placeholder(R.drawable.place_holder_product_list)
                        .imageView(productFace)
                        .build());

        if (data.getTitle1() != null && !TextUtils.isEmpty(data.getTitle1())) {
            productTag.setVisibility(View.VISIBLE);
            productTag.setText(data.getTitle1());
            if (data.getTitle1Color() != null && data.getTitle1Color().startsWith("#")) {
                productTag.setTextColor(Color.parseColor(data.getTitle1Color()));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    productTag.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(data.getTitle1Color())));
                }
            }
        } else {
            productTag.setVisibility(View.GONE);
        }
        if (data.getTitle2() != null && !TextUtils.isEmpty(data.getTitle2())) {
            productTag2.setVisibility(View.VISIBLE);
            productTag2.setText(data.getTitle2());
            if (data.getTitle2Color() != null && data.getTitle2Color().startsWith("#")) {
                productTag2.setTextColor(Color.parseColor(data.getTitle2Color()));
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    productTag2.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(data.getTitle2Color())));
                }
            }
        } else {
            productTag2.setVisibility(View.GONE);
        }


        if (data.getViceTitle() != null) {
            productContent.setText(data.getViceTitle());
        } else {
            productContent.setText("");
        }
        if (data.getTitle3() != null) {
            productDate.setText(data.getTitle3());
        } else {
            productDate.setText("");
        }
        if (data.getTitle4() != null) {
            productDateValue.setText(data.getTitle4());
        } else {
            productDateValue.setText("");
        }
    }

}
