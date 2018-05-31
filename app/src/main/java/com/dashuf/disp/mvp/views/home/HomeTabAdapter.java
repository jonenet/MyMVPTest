package com.dashuf.disp.mvp.views.home;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.vlayout.DelegateAdapter;
import com.alibaba.android.vlayout.LayoutHelper;
import com.alibaba.android.vlayout.layout.GridLayoutHelper;
import com.dashuf.disp.R;
import com.dashuf.disp.mvp.model.entity.HomeTabBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ex-zhoulai on 2018/3/19.
 */

public class HomeTabAdapter extends DelegateAdapter.Adapter<HomeTabAdapter.HomeTabHolder> {

    private Context context;
    private List<HomeTabBean> homeTabBeanList;
    private static View.OnClickListener homeTabLinstener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            int position = (int) v.getTag();
            switch (position) {
                case 0:
//                    TalkDataBusiness.addRecord(mBaseActivity, "10-04", "点击菜单进度查询");
//                    if (BaseActivity.isLogin()) {
//                        LoanHistoryListActivity.startThisActivity(mBaseActivity, 0);
//                    } else {
//                        BaseActivity.reLogin(mBaseActivity, null);
//                    }
                    break;
                case 1:
////                    TalkDataBusiness.addRecord(mBaseActivity, "10-05", "点击菜单待办事项");
////                    if (BaseActivity.isLogin()) {
////                        DateNotifacationListActivity.startThisActivity(mBaseActivity);
////                    } else {
////                        BaseActivity.reLogin((Activity) mBaseActivity, null);
////                    }
//                    onClickChatCheckPermission();
////                    CreditReportUploadResultActivity.startThisActivity(getContext(),null);
                    break;
                case 2:
////                    TalkDataBusiness.addRecord(mBaseActivity, "10-07", "点击智能评估");
//                    if (BaseActivity.isLogin()) {
//                        BrandListActivity.startThisActivity(mBaseActivity);
//                    } else {
//                        BaseActivity.reLogin(mBaseActivity, null);
//                    }
                    break;
                case 3:
//                    TalkDataBusiness.addRecord(mBaseActivity, "19-01", "点击组建团队");
//                    if (BaseActivity.isLogin()) {
//                        WebviewActivity.startThisActivity(mBaseActivity, ApiConstant.getTeamIndex());
//                    } else {
//                        BaseActivity.reLogin(mBaseActivity, null);
//                    }
//
                    break;
//            }
            }
        }
    };


    public HomeTabAdapter(Context context, List<HomeTabBean> homeTabBeanList) {
        this.context = context;
        this.homeTabBeanList = homeTabBeanList;
    }

    @Override
    public LayoutHelper onCreateLayoutHelper() {
        GridLayoutHelper gridLayoutHelper = new GridLayoutHelper(4, 4);
        int margin = (int) context.getResources().getDimension(R.dimen.activity_horizontal_margin);
        gridLayoutHelper.setPadding(10, margin, 10, margin);
        gridLayoutHelper.setBgColor(Color.WHITE);
        return gridLayoutHelper;
    }


    @Override
    public HomeTabHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_home_tab, parent, false);
        return new HomeTabHolder(rootView);
    }

    @Override
    public void onBindViewHolder(HomeTabHolder holder, int position) {
        HomeTabBean homeTabBean = homeTabBeanList.get(position);
        holder.ivHomeTabImg.setImageResource(homeTabBean.getImgResId());
        holder.tvHomeTabTitle.setText(homeTabBean.getTabTitle());
        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(homeTabLinstener);

        if (homeTabBean.getMsgSize() > 0) {
            holder.ivHomeTabDot.setVisibility(View.VISIBLE);
        } else {
            holder.ivHomeTabDot.setVisibility(View.GONE);
        }

        holder.itemView.setTag(position);
    }

    @Override
    public int getItemCount() {
        return null == homeTabBeanList ? 0 : homeTabBeanList.size();
    }


    static class HomeTabHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_home_tab_title)
        TextView tvHomeTabTitle;
        @BindView(R.id.iv_home_tab_img)
        ImageView ivHomeTabImg;
        @BindView(R.id.iv_home_tab_dote)
        ImageView ivHomeTabDot;

        HomeTabHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
