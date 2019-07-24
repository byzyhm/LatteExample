package com.zlcp.latteec.launcher;

import android.os.Bundle;
import android.view.View;

import com.zlcp.lattecore.delegates.LatteDelegate;
import com.zlcp.lattecore.util.timer.BaseTimerTask;
import com.zlcp.lattecore.util.timer.ITimerListener;
import com.zlcp.latteec.R;
import com.zlcp.latteec.R2;

import java.text.MessageFormat;
import java.util.Timer;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * 作者：zl_freedom
 * 时间：2019/7/24 16:30
 * 功能描述：
 */
public class LauncherDelegate extends LatteDelegate implements ITimerListener {

    @BindView(R2.id.tv_launcher_timer)
    public AppCompatTextView mTvTimer = null;

    private Timer mTimer = null;
    private int mCount = 5;

    @OnClick(R2.id.tv_launcher_timer)
    void onClickTimerView() {

    }

    private void initTimer() {
        mTimer = new Timer();
        final BaseTimerTask task = new BaseTimerTask(this);
        mTimer.schedule(task, 0, 1000);
    }

    @Override
    public Object setLayout() {
        return R.layout.delegate_launcher;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        initTimer();
    }

    public void checkIsShowScroll(){

    }

    @Override
    public void onTimer() {
        getProxyActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mTvTimer != null) {
                    mTvTimer.setText(MessageFormat.format("跳过{0}s", mCount));
                    mCount--;
                    if (mCount < 0) {
                        if (mTimer != null) {
                            mTimer.cancel();
                            mTimer = null;
//                            判断是否第一次登录
                            checkIsShowScroll();
                        }
                    }

                }
            }
        });
    }
}
