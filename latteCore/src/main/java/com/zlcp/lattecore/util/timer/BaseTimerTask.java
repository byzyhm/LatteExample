package com.zlcp.lattecore.util.timer;

import java.util.TimerTask;

/**
 * 作者：zl_freedom
 * 时间：2019/7/24 16:33
 * 功能描述：
 */
public class BaseTimerTask extends TimerTask {

    private ITimerListener mITimerListener = null;

    public BaseTimerTask(ITimerListener timerListener) {
        this.mITimerListener = timerListener;
    }

    @Override
    public void run() {
        if (mITimerListener != null) {
            mITimerListener.onTimer();
        }
    }
}
