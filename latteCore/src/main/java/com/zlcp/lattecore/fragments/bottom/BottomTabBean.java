package com.zlcp.lattecore.fragments.bottom;

/**
 * 作者：zl_freedom
 * 时间：2019/7/29 23:45
 * Note：
 */
public class BottomTabBean {
    private final CharSequence ICON;
    private final CharSequence TITLE;

    public BottomTabBean(CharSequence icon, CharSequence title) {
        this.ICON = icon;
        this.TITLE = title;
    }

    public CharSequence getIcon() {
        return ICON;
    }

    public CharSequence getTitle() {
        return TITLE;
    }
}
