package com.zlcp.latteec.main;

import android.graphics.Color;


import com.zlcp.lattecore.fragments.bottom.BaseBottomFragment;
import com.zlcp.lattecore.fragments.bottom.BottomItemBuilder;
import com.zlcp.lattecore.fragments.bottom.BottomItemFragment;
import com.zlcp.lattecore.fragments.bottom.BottomTabBean;
import com.zlcp.latteec.main.cart.ShopCartFragment;
import com.zlcp.latteec.main.discover.DiscoverFragment;
import com.zlcp.latteec.main.index.IndexFragment;
import com.zlcp.latteec.main.personal.PersonalFragment;
import com.zlcp.latteec.main.sort.SortFragment;

import java.util.LinkedHashMap;

/**
 * Created by zl_freedom
 * Note:
 */

public class EcBottomFragment extends BaseBottomFragment {

    @Override
    public LinkedHashMap<BottomTabBean, BottomItemFragment> setItems(BottomItemBuilder builder) {
        final LinkedHashMap<BottomTabBean, BottomItemFragment> items = new LinkedHashMap<>();
        items.put(new BottomTabBean("{fa-home}","主页"), new IndexFragment());
        items.put(new BottomTabBean("{fa-sort}","分类"), new SortFragment());
        items.put(new BottomTabBean("{fa-compass}","发现"), new DiscoverFragment());
        items.put(new BottomTabBean("{fa-shopping-cart}","购物车"), new ShopCartFragment());
        items.put(new BottomTabBean("{fa-user}","我的"), new PersonalFragment());
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
