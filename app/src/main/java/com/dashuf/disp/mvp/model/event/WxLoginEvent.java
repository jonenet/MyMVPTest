package com.dashuf.disp.mvp.model.event;

/**
 * Created by ex-huanghai on 2018/3/12.
 */

public class WxLoginEvent {
    String wxCode;

    public String getWxCode() {
        return wxCode;
    }

    public void setWxCode(String wxCode) {
        this.wxCode = wxCode;
    }

    public WxLoginEvent(String wxCode) {
        this.wxCode = wxCode;
    }
}
