package com.dashuf.disp.mvp.model.entity;

/**
 * Created by ex-zhoulai on 2018/4/4.
 */

public class HomeTabBean {

    private int imgResId;
    private int msgSize;
    private String tabTitle;

    public HomeTabBean(int imgResId, int msgSize, String tabTitle) {
        this.imgResId = imgResId;
        this.msgSize = msgSize;
        this.tabTitle = tabTitle;
    }

    public int getImgResId() {
        return imgResId;
    }

    public void setImgResId(int imgResId) {
        this.imgResId = imgResId;
    }

    public int getMsgSize() {
        return msgSize;
    }

    public void setMsgSize(int msgSize) {
        this.msgSize = msgSize;
    }

    public String getTabTitle() {
        return tabTitle;
    }

    public void setTabTitle(String tabTitle) {
        this.tabTitle = tabTitle;
    }
}
