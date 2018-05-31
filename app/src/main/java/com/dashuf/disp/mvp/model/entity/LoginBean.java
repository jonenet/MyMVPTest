package com.dashuf.disp.mvp.model.entity;

/**
 * Created by ex-zhoulai on 2018/5/29.
 */

public class LoginBean {

    /**
     * firstLogin : N
     * message : 登录成功
     * token : P@cA9MHgjmsiv1FIfH8R2NgN
     */

    private String firstLogin;
    private String message;
    private String token;

    public String getFirstLogin() {
        return firstLogin;
    }

    public void setFirstLogin(String firstLogin) {
        this.firstLogin = firstLogin;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "LoginBean{" +
                "firstLogin='" + firstLogin + '\'' +
                ", message='" + message + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
