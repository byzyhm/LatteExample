package com.zlcp.latteexample;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.zlcp.lattecore.activities.ProxyActivity;
import com.zlcp.lattecore.app.Latte;
import com.zlcp.lattecore.fragments.LatteFragment;
import com.zlcp.latteec.launcher.LauncherFragment;
import com.zlcp.latteec.main.EcBottomFragment;
import com.zlcp.latteec.sign.ISignListener;
import com.zlcp.latteui.launcher.ILauncherListener;
import com.zlcp.latteui.launcher.OnLauncherFinishTag;

import qiu.niorgai.StatusBarCompat;

public class MainActivity extends ProxyActivity
        implements ISignListener, ILauncherListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Latte.getConfigurator().withActivity(this);
        StatusBarCompat.translucentStatusBar(this, true);
    }

    @Override
    public LatteFragment setRootFragment() {
        return new LauncherFragment();
    }

    @Override
    public void onSignInSuccess() {
        Toast.makeText(this, "登录成功", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSignUpSuccess() {
        Toast.makeText(this, "MainActivity提示注册成功", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onLauncherFinish(OnLauncherFinishTag tag) {
        switch (tag) {
            case SIGNED:
                Toast.makeText(this, "您已登陆了哟！", Toast.LENGTH_LONG).show();
                getSupportDelegate().startWithPop(new EcBottomFragment());     //登录后跳主页
                break;
            case NOT_SIGNED:
                Toast.makeText(this, "亲，您还没有登录！", Toast.LENGTH_LONG).show();
                getSupportDelegate().startWithPop(new EcBottomFragment());     //没登录也跳主页
//                getSupportDelegate().start(new SignInFragment());//没登录先直接跳转登录页面
                break;
            default:
                break;
        }
    }
}
