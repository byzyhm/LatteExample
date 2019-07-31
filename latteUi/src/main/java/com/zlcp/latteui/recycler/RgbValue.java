package com.zlcp.latteui.recycler;

import com.google.auto.value.AutoValue;

/**
 * 作者：zl_freedom
 * 时间：2019/7/31 11:47
 * Note：
 */
@AutoValue
public abstract class RgbValue {
    public abstract int red();

    public abstract int green();

    public abstract int blue();

    public static RgbValue create(int red, int green, int blue) {
        return new AutoValue_RgbValue(red, green, blue);
    }
}
