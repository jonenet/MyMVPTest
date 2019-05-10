package com.dashuf.disp.mvp.views.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.InputType;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dashuf.disp.R;
import com.dashuf.disp.mvp.presenter.LoginPrePresenter;
import com.dashuf.disp.mvp.views.HomeActivity;
import com.jess.arms.base.App;
import com.jess.arms.base.BaseSimpleActivity;
import com.jess.arms.mvp.IView;
import com.jess.arms.mvp.Message;
import com.jess.arms.utils.ArmsUtils;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.rengwuxian.materialedittext.validation.METValidator;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.OnClick;
import timber.log.Timber;

/**
 * Created by ex-zhoulai on 2018/5/28.
 */

public class LoginActivity extends BaseSimpleActivity<LoginPrePresenter> implements IView {

    private static final String TAG = "LoginActivity";

    @BindView(R.id.mobile)
    MaterialEditText mobile;
    @BindView(R.id.password)
    MaterialEditText password;
    @BindView(R.id.forget_pwd_button)
    TextView forgetPwdButton;
    @BindView(R.id.sign_in_button)
    Button signInButton;
    @BindView(R.id.ll_account)
    LinearLayout ll_account;
    @BindView(R.id.iv_hide_pwd)
    ImageView iv_hide_pwd;

    boolean hidePwd;


    @Override
    public void handleMessage(Message msg) {
        if (msg.what == 1) {
            launchActivity(new Intent(this, HomeActivity.class));
        }
    }


    @Nullable
    @Override
    public LoginPrePresenter obtainPresenter() {
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        return new LoginPrePresenter(((App) getApplicationContext()).getAppComponent(), this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.content_login_2;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        SpannableString sps = new SpannableString(getResources().getString(R.string.action_agreement_all));
        sps.setSpan(new UnderlineSpan(), 6, 22, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        sps.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorPrimary)), 6, 22, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); //设置背景色为青色
//        tvTip.setText(sps);
        METValidator inviteCodeValidator = new METValidator("请输入11位手机号") {
            @Override
            public boolean isValid(@NonNull CharSequence text, boolean isEmpty) {
                return checkCellPhone(text.toString());

            }
        };
        mobile.addValidator(inviteCodeValidator);
        mobile.setValidateOnFocusLost(true);

        METValidator otpValidator1 = new METValidator("请输入密码") {
            @Override
            public boolean isValid(@NonNull CharSequence text, boolean isEmpty) {
                return !isEmpty;

            }
        };
        password.addValidator(otpValidator1);
        password.setValidateOnFocusLost(false);
        password.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEND || (event != null && event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) {
                //防止按钮触发两次提交事件，仅在按钮up时提交，down时需返回true，否则不执行up。
                if (event != null && event.getAction() == KeyEvent.ACTION_DOWN) {
                    return true;
                } else if (event != null && event.getAction() == KeyEvent.ACTION_UP) {
                    login();
                    return true;
                }
                login();
                return true;
            }
            return false;
        });

    }

    private void login() {
        if (mobile.validate() && password.validate() ) {
            Map<String, String> params = new HashMap<>();
            params.put("username", mobile.getText().toString());
            params.put("password", password.getText().toString());
            mPresenter.doLogin(Message.obtain(this, params));

        }
    }


    /**
     * 校验手机号码规范性
     *
     * @param userName 手机号
     * @return true/false
     */
    boolean checkCellPhone(String userName) {
        String mobileFormat = "^1[34578]\\d{9}$";
        Pattern pattern = Pattern.compile(mobileFormat);
        return pattern.matcher(userName).matches();
    }


    @OnClick({R.id.iv_hide_pwd, R.id.iv_cancel, R.id.forget_pwd_button, R.id.sign_in_button})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_hide_pwd:
                if (hidePwd) {
                    password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    iv_hide_pwd.setImageResource(R.drawable.ico_hide_mony_white);
                } else {
                    password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    iv_hide_pwd.setImageResource(R.drawable.ico_show_mony);
                }
                password.setSelection(password.length());
                hidePwd = !hidePwd;
                break;
            case R.id.iv_cancel:
                killMyself();
                break;

            case R.id.sign_in_button:
                break;
        }
    }






    @Override
    protected void onStart() {
        super.onStart();
        Timber.i("TIME#onStart");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Timber.i("TIME#onStop");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Timber.i("TIME#onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Timber.i("TIME#onPause");
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Timber.i("TIME#onCreate");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Timber.i("TIME#onDestroy");
    }

    @Override
    public boolean useEventBus() {
        return true;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {
        Log.i(TAG, "hideLoading ");
    }

    @Override
    public void showMessage(@NonNull String message) {
        ArmsUtils.snackbarText(message);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        startActivity(intent);
    }

    @Override
    public void killMyself() {
        finish();
    }

    @Override
    public Activity getActivity() {
        return this;
    }
}
