package com.dashuf.disp.mvp.views.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.dashuf.disp.R;
import com.dashuf.disp.global.AppConstant;
import com.dashuf.disp.mvp.model.entity.RegisterBean;
import com.dashuf.disp.mvp.presenter.RegisterPresenter;
import com.jess.arms.base.App;
import com.jess.arms.base.BaseSimpleActivity;
import com.jess.arms.mvp.IView;
import com.jess.arms.mvp.Message;
import com.jess.arms.utils.ArmsUtils;
import com.jess.arms.utils.Preconditions;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class RegisterActivity extends BaseSimpleActivity<RegisterPresenter> implements IView {

    @BindView(R.id.et_mobile)
    MaterialEditText etMobile;
    @BindView(R.id.et_password)
    MaterialEditText etPassword;
    @BindView(R.id.et_repassword)
    MaterialEditText etRePassword;

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_register; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }

    @Override
    @Nullable
    public RegisterPresenter obtainPresenter() {
        return new RegisterPresenter(((App) getApplication()).getAppComponent(), this);
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(@NonNull String message) {
        Preconditions.checkNotNull(message);
        ArmsUtils.snackbarText(message);
    }

    @Override
    public void handleMessage(@NonNull Message message) {
        Preconditions.checkNotNull(message);
        if (message.what == 1) {
            // 结束当前页面，跳转到登陆页面
            RegisterBean registerBean = (RegisterBean) message.obj;
            launchActivity(new Intent(this, LoginActivity.class).putExtra(AppConstant.USERNAME, registerBean.getUsername()));
        }
    }


    @Override
    public void launchActivity(@NonNull Intent intent) {
        Preconditions.checkNotNull(intent);
        ArmsUtils.startActivity(intent);
    }

    @Override
    public Activity getActivity() {
        return this;
    }


    @Override
    public void killMyself() {
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.sign_in_button)
    public void onViewClicked() {
        Map<String, String> params = new HashMap<>();
        params.put("username", etMobile.getText().toString());
        params.put("etPassword", etPassword.getText().toString());
        params.put("repassword", etRePassword.getText().toString());
        mPresenter.register(Message.obtain(this, params));
    }
}
