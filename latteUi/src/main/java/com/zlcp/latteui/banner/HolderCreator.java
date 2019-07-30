package com.zlcp.latteui.banner;

import com.bigkoo.convenientbanner.holder.CBViewHolderCreator;

/**
 * 作者：zl_freedom
 * 时间：2019/7/30 20:57
 * Note：
 */
public class HolderCreator implements CBViewHolderCreator<ImageHolder> {
    @Override
    public ImageHolder createHolder() {
        return new ImageHolder();
    }
}
