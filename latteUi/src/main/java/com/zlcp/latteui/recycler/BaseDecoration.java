package com.zlcp.latteui.recycler;

import androidx.annotation.ColorInt;

import com.choices.divider.DividerItemDecoration;

/**
 * 作者：zl_freedom
 * 时间：2019/7/31 01:14
 * Note：
 */
public class BaseDecoration extends DividerItemDecoration {
    private BaseDecoration(@ColorInt int color, int size) {
        setDividerLookup(new DividerLookupImpl(color, size));
    }

    public static BaseDecoration create(@ColorInt int color, int size) {
        return new BaseDecoration(color, size);
    }

}
