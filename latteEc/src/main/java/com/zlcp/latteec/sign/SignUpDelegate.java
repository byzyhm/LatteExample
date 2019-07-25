package com.zlcp.latteec.sign;

import android.app.Activity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;

import com.google.android.material.textfield.TextInputEditText;
import com.zlcp.lattecore.delegates.LatteDelegate;
import com.zlcp.lattecore.net.RestClient;
import com.zlcp.lattecore.net.callback.ISuccess;
import com.zlcp.lattecore.util.log.LogUtils;
import com.zlcp.latteec.R;
import com.zlcp.latteec.R2;

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者：zl_freedom
 * 时间：2019/7/25 14:42
 * 功能描述：
 */
public class SignUpDelegate extends LatteDelegate {
    @BindView(R2.id.edit_sign_up_name)
    TextInputEditText mName = null;
    @BindView(R2.id.edit_sign_up_email)
    TextInputEditText mEmail = null;
    @BindView(R2.id.edit_sign_up_phone)
    TextInputEditText mPhone = null;
    @BindView(R2.id.edit_sign_up_password)
    TextInputEditText mPassword = null;
    @BindView(R2.id.edit_sign_up_re_password)
    TextInputEditText mRePassword = null;

    public ISignListener mISignListener = null;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ISignListener) {
            mISignListener = (ISignListener) activity;
        }
    }

    @OnClick(R2.id.btn_sign_up)
    void onClickSignUp() {
        if (checkForm()) {
            RestClient.builder()
                    .url("http://mock.fulingjie.com/mock-android/data/user_profile.json")
                    .params("name", mName.getText().toString())
                    .params("email", mEmail.getText().toString())
                    .params("phone", mPhone.getText().toString())
                    .params("password", mPassword.getText().toString())
                    .success(new ISuccess() {
                        @Override
                        public void onSuccess(String response) {
                            LogUtils.json("USER_PROFILE", response);
                            SignHandler.onSignUp(response, mISignListener);
                        }
                    })
                    .loader(getContext())
                    .build()
                    .post();
        }
    }

    private boolean checkForm() {
        final String name = mName.getText().toString();
        final String email = mEmail.getText().toString();
        final String phone = mPhone.getText().toString();
        final String password = mPassword.getText().toString();
        final String rePassword = mRePassword.getText().toString();

        boolean isPass = true;

        if (name.isEmpty()) {
            mName.setError("请输入姓名");
            isPass = false;
        } else {
            mName.setError(null);
        }

        if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmail.setError("错误的邮箱格式");
            isPass = false;
        } else {
            mEmail.setError(null);
        }

        if (phone.isEmpty() || phone.length() != 11) {
            mPhone.setError("手机号码错误");
            isPass = false;
        } else {
            mPhone.setError(null);
        }

        if (password.isEmpty() || password.length() < 6) {
            mPassword.setError("请填写至少6位数密码");
            isPass = false;
        } else {
            mPassword.setError(null);
        }

        if (rePassword.isEmpty() || rePassword.length() < 6 || !(rePassword.equals(password))) {
            mRePassword.setError("两次密码输入不一致");
            isPass = false;
        } else {
            mRePassword.setError(null);
        }

        return isPass;
    }


    @Override
    public Object setLayout() {
        return R.layout.delegate_sign_up;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

    }
}
