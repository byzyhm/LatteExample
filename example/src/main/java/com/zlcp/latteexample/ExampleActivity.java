package com.zlcp.latteexample;

import android.os.Bundle;
import android.widget.Toast;

import com.zlcp.lattecore.activities.ProxyActivity;
import com.zlcp.lattecore.app.Latte;
import com.zlcp.lattecore.delegates.LatteDelegate;
import com.zlcp.latteec.launcher.ILauncherListener;
import com.zlcp.latteec.launcher.LauncherDelegate;
import com.zlcp.latteec.launcher.LauncherScrollDelegate;
import com.zlcp.latteec.launcher.OnLauncherFinishTag;
import com.zlcp.latteec.sign.ISignListener;
import com.zlcp.latteec.sign.SignUpDelegate;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import qiu.niorgai.StatusBarCompat;

public class ExampleActivity extends ProxyActivity implements ISignListener, ILauncherListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final ActionBar actionBar = getSupportActionBar();
        Latte.getConfigurator().withActivity(this);
        StatusBarCompat.translucentStatusBar(this, true);
    }

    @Override
    public LatteDelegate setRootDelegate() {
//        return new ExampleDelegate();
//        return new LauncherDelegate();
//        return new LauncherScrollDelegate();
        return new SignUpDelegate();
    }

    @Override
    public void onSignUpSuccess() {
        Toast.makeText(this, "注册成功", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSignInSuccess() {
        Toast.makeText(this, "登录成功", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onLauncherFinish(OnLauncherFinishTag tag) {
        switch (tag) {
            case SIGNED:
                Toast.makeText(this, "您已登陆了哟！", Toast.LENGTH_LONG).show();
                break;
            case NOT_SINGED:
                Toast.makeText(this, "亲，您还没有登录！", Toast.LENGTH_LONG).show();
                break;
            default:
                break;
        }
    }
}
