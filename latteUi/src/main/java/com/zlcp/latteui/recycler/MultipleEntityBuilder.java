package com.zlcp.latteui.recycler;

import java.util.LinkedHashMap;

/**
 * 作者：zl_freedom
 * 时间：2019/7/30 18:42
 * Note：
 */
public class MultipleEntityBuilder {
    public static final LinkedHashMap<Object, Object> FIELDS = new LinkedHashMap<>();

    public MultipleEntityBuilder() {
        //清除之前的数据
        FIELDS.clear();
    }

    public final MultipleEntityBuilder setItemType(int type) {
        FIELDS.put(MultipleFields.ITEM_TYPE, type);
        return this;
    }

    public final MultipleEntityBuilder setField(Object key, Object value) {
        FIELDS.put(key, value);
        return this;
    }

    public final MultipleEntityBuilder setFields(LinkedHashMap<?, ?> map) {
        FIELDS.putAll(map);
        return this;
    }

    public final MultipleItemEntity build() {
        return new MultipleItemEntity(FIELDS);
    }

}
