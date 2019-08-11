package com.zlcp.latteec.main.index.search;


import androidx.appcompat.widget.AppCompatTextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.zlcp.latteec.R;
import com.zlcp.latteui.recycler.MultipleFields;
import com.zlcp.latteui.recycler.MultipleItemEntity;
import com.zlcp.latteui.recycler.MultipleViewHolder;

import java.util.List;

public class SearchAdapter extends BaseMultiItemQuickAdapter<MultipleItemEntity, MultipleViewHolder> {

    public SearchAdapter(List<MultipleItemEntity> data) {
        super(data);
        addItemType(SearchItemType.ITEM_SEARCH, R.layout.item_search);
    }

    @Override
    protected void convert(MultipleViewHolder helper, MultipleItemEntity item) {
        switch (helper.getItemViewType()) {
            case SearchItemType.ITEM_SEARCH:
                final AppCompatTextView tvSearchItem = helper.getView(R.id.tv_search_item);
                final String history = item.getField(MultipleFields.TEXT);
                tvSearchItem.setText(history);
                break;
            default:
                break;
        }
    }
}
