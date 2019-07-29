package com.zlcp.latteec.launcher;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.zlcp.lattecore.app.AccountManager;
import com.zlcp.lattecore.app.IUserChecker;
import com.zlcp.lattecore.fragments.LatteFragment;
import com.zlcp.lattecore.ui.launcher.ScrollLauncherTag;
import com.zlcp.lattecore.util.storage.LattePreference;
import com.zlcp.lattecore.util.timer.BaseTimerTask;
import com.zlcp.lattecore.util.timer.ITimerListener;
import com.zlcp.latteec.R;

import java.text.MessageFormat;
import java.util.Timer;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

/**
 * 作者：zl_freedom
 * 时间：2019/7/24 16:30
 * 功能描述：
 */
public class LauncherFragment extends LatteFragment implements ITimerListener {

    public AppCompatTextView mTvTimer = null;
    private Timer mTimer = null;
    private int mCount = 5;
    private ILauncherListener mILauncherListener = null;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        if (activity instanceof ILauncherListener) {
            mILauncherListener = (ILauncherListener) activity;
        }
    }

    private void onClickTimerView() {
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
            checkIsShowScroll();
        }
    }

    private void initTimer() {
        mTimer = new Timer();
        final BaseTimerTask task = new BaseTimerTask(this);
        mTimer.schedule(task, 0, 1000);
    }

    @Override
    public Object setLayout() {
        return R.layout.fragment_launcher;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View root) {
        initTimer();
        mTvTimer = $(R.id.tv_launcher_timer);
        mTvTimer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickTimerView();
            }
        });
    }

    public void checkIsShowScroll() {
        //如果不是第一次启动，就跳转轮播图启动页
        if (!LattePreference.getAppFlag(ScrollLauncherTag.HAS_FIRST_LAUNCHER_APP.name())) {
            getSupportDelegate().startWithPop(new LauncherScrollFragment());
        } else {
            //检查用户是否登录了
            AccountManager.checkAccount(new IUserChecker() {
                @Override
                public void onSignIn() {
                    if (mILauncherListener != null) {
                        mILauncherListener.onLauncherFinish(OnLauncherFinishTag.SIGNED);
                    }
                }

                @Override
                public void onNotSignIn() {
                    if (mILauncherListener != null) {
                        mILauncherListener.onLauncherFinish(OnLauncherFinishTag.NOT_SIGNED);
                    }
                }
            });
        }
    }

    @Override
    public void onDestroy() {
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
        super.onDestroy();
    }

    @Override
    public void onTimer() {
        getProxyActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mTvTimer != null) {
                    mTvTimer.setText(MessageFormat.format("跳过 {0}s", mCount));
                    mCount--;
                    if (mCount < 1) {
                        if (mTimer != null) {
                            mTimer.cancel();
                            mTimer = null;
                            //判断是否第一次登录
                            checkIsShowScroll();
                        }
                    }
                }
            }
        });
    }

    @Override
    public boolean onBackPressedSupport() {
        return true;
    }
}
