package com.zlcp.latteec.main.cart;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zlcp.latteui.recycler.DataConverter;
import com.zlcp.latteui.recycler.MultipleFields;
import com.zlcp.latteui.recycler.MultipleItemEntity;

import java.util.ArrayList;

/**
 * 作者：zl_freedom
 * 时间：2019/8/3 00:37
 * Note：
 */
public class ShopCartDataConverter extends DataConverter {
    @Override
    public ArrayList<MultipleItemEntity> convert() {
        final ArrayList<MultipleItemEntity> dataList = new ArrayList<>();
        final JSONArray dataArray = JSON.parseObject(getJsonData()).getJSONArray("data");
        final int size = dataArray.size();
        for (int i = 0; i < size; i++) {
            final JSONObject data = dataArray.getJSONObject(i);
            final String title = data.getString("title");
            final String desc = data.getString("desc");
            final int id = data.getInteger("id");
            final int count = data.getInteger("count");
            final double price = data.getDouble("price");
            final String thumb = data.getString("thumb");
            final MultipleItemEntity entity = MultipleItemEntity.builder()
                    .setField(MultipleFields.ITEM_TYPE, ShopCartItemType.SHOP_CART_ITEM)
                    .setField(MultipleFields.ID, id)
                    .setField(MultipleFields.IMAGE_URL, thumb)
                    .setField(ShopCartItemFields.TITLE, title)
                    .setField(ShopCartItemFields.PRICE, price)
                    .setField(ShopCartItemFields.COUNT, count)
                    .setField(ShopCartItemFields.DESC, desc)
                    .setField(ShopCartItemFields.IS_SELECTED, false)
                    .setField(ShopCartItemFields.POSITION, i)
                    .setField(ShopCartItemFields.TITLE, title)
                    .setField(ShopCartItemFields.TITLE, title)
                    .build();
            dataList.add(entity);
        }
        return dataList;
    }
}
