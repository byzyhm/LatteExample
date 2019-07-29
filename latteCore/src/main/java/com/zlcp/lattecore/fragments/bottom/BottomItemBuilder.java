package com.zlcp.lattecore.fragments.bottom;

import java.util.LinkedHashMap;

/**
 * 作者：zl_freedom
 * 时间：2019/7/29 23:46
 * Note：fragment和底部导航Item的绑定
 */
public class BottomItemBuilder {
    private final LinkedHashMap<BottomTabBean, BottomItemFragment> ITEMS = new LinkedHashMap<>();

    public static BottomItemBuilder builder() {
        return new BottomItemBuilder();
    }

    public BottomItemBuilder addItem(BottomTabBean bean, BottomItemFragment fragment) {
        ITEMS.put(bean, fragment);
        return this;
    }

    public BottomItemBuilder addItems(LinkedHashMap<BottomTabBean, BottomItemFragment> items) {
        ITEMS.putAll(items);
        return this;
    }

    public final LinkedHashMap<BottomTabBean, BottomItemFragment> build() {
        return ITEMS;
    }


}
