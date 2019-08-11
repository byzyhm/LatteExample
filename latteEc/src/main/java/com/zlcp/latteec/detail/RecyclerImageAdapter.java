package com.zlcp.latteec.detail;


import androidx.appcompat.widget.AppCompatImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.zlcp.latteec.R;
import com.zlcp.latteui.recycler.ItemType;
import com.zlcp.latteui.recycler.MultipleFields;
import com.zlcp.latteui.recycler.MultipleItemEntity;
import com.zlcp.latteui.recycler.MultipleViewHolder;

import java.util.List;

public class RecyclerImageAdapter extends
        BaseMultiItemQuickAdapter<MultipleItemEntity, MultipleViewHolder> {

    private static final RequestOptions OPTIONS = new RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .dontAnimate();


    public RecyclerImageAdapter(List<MultipleItemEntity> data) {
        super(data);
        addItemType(ItemType.SINGLE_BIG_IMAGE, R.layout.item_image);
    }

    @Override
    protected void convert(MultipleViewHolder helper, MultipleItemEntity item) {
        final int type = helper.getItemViewType();
        switch (type) {
            case ItemType.SINGLE_BIG_IMAGE:
                final AppCompatImageView imageView = helper.getView(R.id.image_rv_item);
                final String url = item.getField(MultipleFields.IMAGE_URL);
                Glide.with(mContext)
                        .load(url)
                        .apply(OPTIONS)
                        .into(imageView);
                break;
            default:
                break;
        }
    }
}
