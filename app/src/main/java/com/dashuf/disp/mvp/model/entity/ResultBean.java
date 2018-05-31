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
    private String errorCode;
    private String message;
    private boolean succeed;
    private T data;

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSucceed() {
        return succeed;
    }

    public void setSucceed(boolean succeed) {
        this.succeed = succeed;
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
                "errorCode='" + errorCode + '\'' +
                ", message='" + message + '\'' +
                ", succeed=" + succeed +
                ", data=" + data +
                '}';
    }

    public boolean isSuccess() {
        return succeed;
    }
}
