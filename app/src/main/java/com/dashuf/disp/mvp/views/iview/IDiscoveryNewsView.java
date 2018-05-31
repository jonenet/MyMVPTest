package com.dashuf.disp.mvp.views.iview;

import com.dashuf.disp.mvp.model.entity.DiscoveryNewsBean;
import com.jess.arms.mvp.IView;


public interface IDiscoveryNewsView extends IView {

    void onPageDetailResult(DiscoveryNewsBean dataBean);
}
