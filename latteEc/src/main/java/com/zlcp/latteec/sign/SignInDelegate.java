package com.zlcp.latteec.sign;

import android.app.Activity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;

import com.blankj.utilcode.util.LogUtils;
import com.google.android.material.textfield.TextInputEditText;
import com.zlcp.lattecore.delegates.LatteDelegate;
import com.zlcp.lattecore.net.RestClient;
import com.zlcp.lattecore.net.callback.ISuccess;
import com.zlcp.latteec.R;
import com.zlcp.latteec.R2;

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者：zl_freedom
 * 时间：2019/7/25 18:20
 * 功能描述：
 */
public class SignInDelegate extends LatteDelegate {

    @BindView(R2.id.edit_sign_in_email)
    TextInputEditText mEmail = null;
    @BindView(R2.id.edit_sign_in_password)
    TextInputEditText mPassword = null;

    public ISignListener mISignListener = null;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ISignListener) {
            mISignListener = (ISignListener) activity;
        }
    }

    //登录按钮
    @OnClick(R2.id.btn_sign_in)
    void onClickSignIn() {
        if (checkFrom()) {
            RestClient.builder()
                    .url("http://mock.fulingjie.com/mock-android/data/user_profile.json")
                    .params("email", mEmail.getText().toString())
                    .params("password", mPassword.getText().toString())
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            LogUtils.json("USER_PROFILE", response);
                            SignHandler.onSignIn(response, mISignListener);
//                            getSupportDelegate().startWithPop(new EcBottomFragment());
                        }
                    })
                    .loader(getContext())
                    .build()
                    .post();
        }
    }

    //没帐号，去注册按钮
    @OnClick(R2.id.tv_link_sign_up)
    void onClickLink() {
        getSupportDelegate().start(new SignUpDelegate(), SINGLETASK);
    }
    //微信登录按钮
    @OnClick(R2.id.icon_sign_in_wechat)
    void onClickWeChat() {

    }



    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_in;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }

    private boolean checkFrom() {
        final String email = mEmail.getText().toString();
        final String password = mPassword.getText().toString();

        boolean isPass = true;

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmail.setError("错误的邮箱格式");
            isPass = false;
        } else {
            mEmail.setError(null);
        }

        if (password.isEmpty() || password.length() < 6) {
            mPassword.setError("请填写至少6位数的密码");
            isPass = false;
        } else {
            mPassword.setError(null);
        }

        return isPass;
    }
}
