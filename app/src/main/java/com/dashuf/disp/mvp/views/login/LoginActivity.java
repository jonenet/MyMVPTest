package com.dashuf.disp.mvp.views.login;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
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
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dashuf.disp.R;
import com.dashuf.disp.mvp.model.event.WxLoginEvent;
import com.dashuf.disp.mvp.presenter.LoginPrePresenter;
import com.dashuf.disp.mvp.views.HomeActivity;
import com.jess.arms.base.App;
import com.jess.arms.base.BaseSimpleActivity;
import com.jess.arms.mvp.IView;
import com.jess.arms.mvp.Message;
import com.jess.arms.utils.ArmsUtils;
import com.rengwuxian.materialedittext.MaterialEditText;
import com.rengwuxian.materialedittext.validation.METValidator;


import org.simple.eventbus.Subscriber;
import org.simple.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.OnClick;
import cn.xiaoneng.uiutils.ToastUtils;
import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import timber.log.Timber;

/**
 * Created by ex-zhoulai on 2018/5/28.
 */

public class LoginActivity extends BaseSimpleActivity<LoginPrePresenter> implements IView {

    private static final String TAG = "LoginActivity";

    @BindView(R.id.mobile)
    MaterialEditText mobile;
    @BindView(R.id.mobile_opt)
    MaterialEditText optMobile;
    @BindView(R.id.password)
    MaterialEditText password;
    @BindView(R.id.forget_pwd_button)
    TextView forgetPwdButton;
    @BindView(R.id.sign_in_button)
    Button signInButton;
    @BindView(R.id.tv_wxlogin)
    TextView tv_wxlogin;
    @BindView(R.id.ll_account)
    LinearLayout ll_account;
    @BindView(R.id.ll_opt)
    LinearLayout ll_opt;
    @BindView(R.id.opt_login)
    TextView opt_login;
    @BindView(R.id.tv_tip)
    AppCompatTextView tvTip;
    @BindView(R.id.password_opt)
    MaterialEditText password_opt;
    @BindView(R.id.tv_getOtp)
    TextView getOtp;
    @BindView(R.id.tv_voice_opt)
    TextView tv_voice_opt;
    @BindView(R.id.agreement_checkBox_opt)
    CheckBox agreement_checkBox_opt;
    @BindView(R.id.iv_hide_pwd)
    ImageView iv_hide_pwd;

    private final int LOGINTYPE_ACCONT = 0;
    private final int LOGINTYPE_OPT = 1;
    private int loginType = 0;
    private String user;
    private String pwd;
    public boolean inviteCode;
    public int videoPosition;
    boolean hidePwd;
    private String wxCode;
    /**
     * 倒计时器
     **/
    private Timer timer;
    private String tempContent = "";
    private int count = 61;
    public static boolean isFirst = false;


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
        Timber.i(tvTip + " " + sps);
        METValidator inviteCodeValidator = new METValidator("请输入11位手机号") {
            @Override
            public boolean isValid(@NonNull CharSequence text, boolean isEmpty) {
                if (checkCellPhone(text.toString())) {
                    return true;
                }

                return false;
            }
        };
        mobile.addValidator(inviteCodeValidator);
        optMobile.addValidator(inviteCodeValidator);
        METValidator otpValidator2 = new METValidator("请输入验证码") {
            @Override
            public boolean isValid(@NonNull CharSequence text, boolean isEmpty) {
                if (!isEmpty) {
                    return true;
                }

                return false;
            }
        };
        password_opt.addValidator(otpValidator2);
        mobile.setValidateOnFocusLost(true);

        METValidator otpValidator1 = new METValidator("请输入密码") {
            @Override
            public boolean isValid(@NonNull CharSequence text, boolean isEmpty) {
                if (!isEmpty) {
                    return true;
                }

                return false;
            }
        };
        password.addValidator(otpValidator1);
        password.setValidateOnFocusLost(false);
        password.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
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
            }
        });

    }

    private void login() {
        Observable.interval(0,2, TimeUnit.SECONDS).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                System.out.println("Along = " +aLong);
            }
        });
        if (mobile.validate() && password.validate() && (loginType == LOGINTYPE_ACCONT) ||
                optMobile.validate() && password_opt.validate() && (loginType == LOGINTYPE_OPT)) {
            Map<String, String> params = new HashMap<>();
            params.put("username", mobile.getText().toString());
            params.put("password", password.getText().toString());
            if (loginType == LOGINTYPE_ACCONT) {
                params.put("loginType", "001");
            } else {
                if (!agreement_checkBox_opt.isChecked()) {
                    ArmsUtils.makeText(this.getApplicationContext(), "请认真阅读用户协议后登录");
                    return;
                } else {
//                          mPresenter.doOptLogin(optMobile.getText().toString(), password_opt.getText().toString(), "002"
//                            , UserSettingProvider.provider().getUserLocationProvice()
//                            , UserSettingProvider.provider().getUserLocationCity()
//                            , UserSettingProvider.provider().getUserLocationCityId());
                    params.put("loginType", "002");
                }
            }
//            mPresenter.doLogin(Message.obtain(this, params));

        }
    }


    @Subscriber(mode = ThreadMode.MAIN)
    public void whenCityPickerClosed(WxLoginEvent event) {
        wxCode = event.getWxCode();
//        if (!BaseActivity.isLogin()) {
//            mPresenter.doWxLogin(wxCode);
//        }
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


    @OnClick({R.id.iv_hide_pwd, R.id.iv_cancel, R.id.tv_voice_opt, R.id.tv_tip, R.id.forget_pwd_button, R.id.sign_in_button, R.id.opt_login, R.id.tv_wxlogin, R.id.sign_in_button_opt, R.id.tv_getOtp})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_hide_pwd:
                if (hidePwd) {
                    password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    iv_hide_pwd.setImageResource(R.drawable.ico_hide_mony_white);
//                    Glide.with(this).load(R.drawable.ico_hide_mony).into(iv_hide_pwd);
                } else {
                    password.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    iv_hide_pwd.setImageResource(R.drawable.ico_show_mony);
//                    Glide.with(this).load(R.drawable.ico_show_mony).into(iv_hide_pwd);
                }
                password.setSelection(password.length());
                hidePwd = !hidePwd;
                break;
            case R.id.iv_cancel:
                killMyself();
                break;
            case R.id.tv_voice_opt:
                //语音短信

//                onClickCallCheckPermission();

                break;
            case R.id.tv_tip:
//                //用户协议
//                WebviewActivity.startThisActivity(mBaseActivity, ApiConstant.getAgreementUrl());
//                TalkDataBusiness.addRecord(mBaseActivity, "00-05", "用户协议");
                break;
            case R.id.forget_pwd_button:
                //找回密码
//                mPresenter.startForgetPwd(mobile.getText().toString());
//                TalkDataBusiness.addRecord(mBaseActivity, "00-04", "找回密码");
                break;
            case R.id.sign_in_button:
            case R.id.sign_in_button_opt:
                //登录  短信登录
                Log.d(TAG, "onClick: login()");
                login();
                break;
            case R.id.opt_login:
                //切换登录
                if (loginType == LOGINTYPE_ACCONT) {
                    loginType = LOGINTYPE_OPT;
                    ll_account.setVisibility(View.GONE);
                    ll_opt.setVisibility(View.VISIBLE);
                    opt_login.setText(getResources().getString(R.string.action_login_account));
                    Drawable drawable = getResources().getDrawable(R.drawable
                            .account_login);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    opt_login.setCompoundDrawables(null, drawable, null, null);
                } else {
                    loginType = LOGINTYPE_ACCONT;
                    ll_account.setVisibility(View.VISIBLE);
                    ll_opt.setVisibility(View.GONE);
                    opt_login.setText(getResources().getString(R.string.action_login_otp));
                    Drawable drawable = getResources().getDrawable(R.drawable
                            .opt_login);
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    opt_login.setCompoundDrawables(null, drawable, null, null);

                }
                break;
            case R.id.tv_wxlogin:
//                //微信登录
//                if (!AppContext.iwxapi.isWXAppInstalled()) {
//                    new Toastor(mBaseActivity).showToast("你还未安装微信客户端");
//                    return;
//                }
////                mShareAPI.doOauthVerify(LoginActivity.this, SHARE_MEDIA.WEIXIN, oauthVerify);
//                final SendAuth.Req req = new SendAuth.Req();
//                req.scope = "snsapi_userinfo";
//                req.state = "diandi_wx_login";
//                AppContext.iwxapi.sendReq(req);
                break;
            case R.id.tv_getOtp:
                //获取短信
                if (optMobile.validate()) {
                    startTimer();
//                    mPresenter.getOpt(optMobile.getText().toString());
                }

                break;
        }
    }


    /**
     * 启动计时器
     */
    private void startTimer() {
        getOtp.setEnabled(false);
        if (timer != null) {
            timer.cancel();
        }
        count = 61;
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(0);
            }
        }, 0, 1000);
    }

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            updateUI();
        }

    };


    /**
     * 更新界面
     **/
    private void updateUI() {
        count--;
        if (count <= -1 && timer != null) {
            timer.cancel();
            getOtp.setEnabled(true);
            getOtp.setText("再次获取验证码");
            tv_voice_opt.setClickable(true);
            tv_voice_opt.setEnabled(true);
            tv_voice_opt.setTextColor(getResources().getColor(R.color.colorPrimary));
            return;
        }
        tempContent = count + "秒后重发";
        getOtp.setText(tempContent);
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
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
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
