package com.zlcp.latteui.launcher;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;

/**
 * 作者：zl_freedom
 * 时间：2019/7/24 19:15
 * 功能描述：
 */
public class LauncherHolderCreator implements CBViewHolderCreator {
    @Override
    public LauncherHolder createHolder() {
        return new LauncherHolder();
    }
}
