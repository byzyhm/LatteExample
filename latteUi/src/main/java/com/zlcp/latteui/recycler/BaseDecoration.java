package com.zlcp.latteui.recycler;


import androidx.annotation.ColorInt;

import com.choices.divider.DividerItemDecoration;

/**
 * Created by zl_freedom
 * Note:
 */

public class BaseDecoration extends DividerItemDecoration {

    private BaseDecoration(@ColorInt int color, int size) {
        setDividerLookup(new DividerLookupImpl(color, size));
    }

    public static BaseDecoration create(@ColorInt int color, int size) {
        return new BaseDecoration(color, size);
    }
}
