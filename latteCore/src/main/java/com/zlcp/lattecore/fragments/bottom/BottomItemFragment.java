package com.zlcp.lattecore.fragments.bottom;

import android.widget.Toast;

import com.zlcp.lattecore.fragments.LatteFragment;

/**
 * 作者：zl_freedom
 * 时间：2019/7/29 23:46
 * Note：
 */
public abstract class BottomItemFragment extends LatteFragment {
    //再点一次退出程序时间设置
    private static final long WAIT_TIME = 2000L;
    private long TOUCH_TIME = 0;

    @Override
    public boolean onBackPressedSupport() {
        if (System.currentTimeMillis() - TOUCH_TIME < WAIT_TIME) {
            _mActivity.finish();
        } else {
            TOUCH_TIME = System.currentTimeMillis();
            Toast.makeText(_mActivity, "双击退出" + "小米易购", Toast.LENGTH_SHORT).show();
        }
        return true;
    }
}
