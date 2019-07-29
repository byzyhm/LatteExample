package com.zlcp.latteec.main;

import android.graphics.Color;

import com.zlcp.lattecore.fragments.bottom.BaseBottomFragment;
import com.zlcp.lattecore.fragments.bottom.BottomItemBuilder;
import com.zlcp.lattecore.fragments.bottom.BottomItemFragment;
import com.zlcp.lattecore.fragments.bottom.BottomTabBean;
import com.zlcp.latteec.main.index.IndexFragment;
import com.zlcp.latteec.main.sort.SortFragment;

import java.util.LinkedHashMap;

/**
 * 作者：zl_freedom
 * 时间：2019/7/30 01:26
 * Note：
 */
public class EcBottomFragment extends BaseBottomFragment {
    @Override
    public LinkedHashMap<BottomTabBean, BottomItemFragment> setItems(BottomItemBuilder builder) {
        final LinkedHashMap<BottomTabBean, BottomItemFragment> items = new LinkedHashMap<>();
        items.put(new BottomTabBean("{fa-home}", "主页"), new IndexFragment());
        items.put(new BottomTabBean("{fa-sort}", "分类"), new SortFragment());
        items.put(new BottomTabBean("{fa-compass}", "发现"), new IndexFragment());
        items.put(new BottomTabBean("{fa-shopping-cart}", "购物车"), new IndexFragment());
        items.put(new BottomTabBean("{fa-user}", "我的"), new IndexFragment());
        return builder.addItems(items).build();
    }

    @Override
    public int setIndexFragment() {
        return 0;
    }

    @Override
    public int setClickColor() {
        return Color.parseColor("#ffff8800");
    }
}
