package com.dashuf.disp.mvp.views.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.LinearLayoutHelper;
import com.dashuf.disp.R;
import com.dashuf.disp.mvp.model.entity.HomeNotificationBean;
import com.jess.arms.utils.ArmsUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 首页广告
 */

public class HomeAdvAdapter extends DelegateAdapter.Adapter<HomeAdvAdapter.ViewHolder> {

    private Context context;
    private List<HomeNotificationBean.ContentBean> notificationList;

    public HomeAdvAdapter(Context context, List<HomeNotificationBean.ContentBean> notificationList) {
        this.context = context;
        this.notificationList = notificationList;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        return new LinearLayoutHelper();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_home_adv, parent, false);
        return new ViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, (50 * (ArmsUtils.getScreenWidth(context) * 2) / 700));
        holder.itemView.setLayoutParams(layoutParams);
        if (null == notificationList || notificationList.isEmpty()) {
            holder.itemView.setVisibility(View.GONE);
        } else {
            holder.itemView.setVisibility(View.VISIBLE);
            holder.vfAdvertisement.removeAllViews();
//        if (notificationList.isEmpty()) {
//            HomeNotificationBean.ContentBean contentBean = new HomeNotificationBean.ContentBean();
//            contentBean.setSummary("111111111111111111");
//            notificationList.add(contentBean);
        }
        for (int i = 0; i < notificationList.size(); i++) {
            View adView = LayoutInflater.from(context).inflate(R.layout.view_advertisement, null, true);
            TextView tv = adView.findViewById(R.id.tv_ad1);
            tv.setText(String.format("客户%s", notificationList.get(i).getSummary()));
            adView.setTag(position);
            adView.setOnClickListener(listener);
            holder.vfAdvertisement.addView(adView);
        }
//        }

        if (notificationList != null && notificationList.size() == 1) {
            holder.vfAdvertisement.setAutoStart(false);
        }
    }


    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
//            if (BaseActivity.isLogin()) {
//                int position = (int) view.getTag();
//                String url = notificationList.get(position).getJummpingUrl();
//                if (url != null && url.startsWith("disp://")) {
//                    view.getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
//                } else if (url != null && url.startsWith("http")) {
//                    WebviewActivity.startThisActivity(context, url);
//                } else {
//                    LoanHistoryListActivity.startThisActivity(context, 0);
//                }
//            } else {
//                BaseActivity.reLogin((Activity) context, null);
//            }
        }
    };

    @Override
    public int getItemCount() {
        return null == notificationList || notificationList.isEmpty() ? 0 : 1;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.vf_advertisement)
        ViewFlipper vfAdvertisement;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}


