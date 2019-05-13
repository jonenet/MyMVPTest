package com.dashuf.disp.mvp.model.entity;

/**
 * Created by ex-zhoulai on 2018/5/29.
 */

public class ResultBean<T> {

    /**
     * code : 0000
     * message : 成功
     * data : {"firstLogin":"N","message":"登录成功","token":"P@cA9MHgjmsiv1FIfH8R2NgN"}
     */
    private int errorCode;
    private String errorMsg;
    private T data;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResultBean{" +
                "errorCode=" + errorCode +
                ", errorMsg='" + errorMsg + '\'' +
                ", data=" + data +
                '}';
    }
}
