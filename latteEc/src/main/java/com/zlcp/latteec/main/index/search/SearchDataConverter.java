package com.zlcp.latteec.main.index.search;

import com.alibaba.fastjson.JSONArray;
import com.zlcp.lattecore.util.storage.LattePreference;
import com.zlcp.latteui.recycler.DataConverter;
import com.zlcp.latteui.recycler.MultipleFields;
import com.zlcp.latteui.recycler.MultipleItemEntity;

import java.util.ArrayList;

public class SearchDataConverter extends DataConverter {

    public static final String TAG_SEARCH_HISTORY = "search_history";

    @Override
    public ArrayList<MultipleItemEntity> convert() {
        final String jsonStl =
                LattePreference.getCustomAppProfile(TAG_SEARCH_HISTORY);
        if (!jsonStl.equals("")){
            final JSONArray array = JSONArray.parseArray(jsonStl);
            final int size = array.size();
            for (int i = 0; i<size;i++) {
                final String historyItemText = array.getString(i);
                final MultipleItemEntity entity = MultipleItemEntity.builder()
                        .setItemType(SearchItemType.ITEM_SEARCH)
                        .setField(MultipleFields.TEXT,historyItemText)
                        .build();
                ENTITIES.add(entity);
            }
        }
        return ENTITIES;
    }
}
