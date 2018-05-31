package com.dashuf.disp.mvp.views.iview;

import com.dashuf.disp.mvp.model.entity.HomeBean;
import com.jess.arms.mvp.IView;

/**
 * Created by ex-zhoulai on 2018/5/31.
 */

public interface IHomeView extends IView {

     void onHomeResult(HomeBean result);

}
