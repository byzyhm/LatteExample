package com.zlcp.latteec.main.sort.list;

import android.graphics.Color;
import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.zlcp.lattecore.fragments.LatteFragment;
import com.zlcp.latteec.R;
import com.zlcp.latteec.main.sort.SortFragment;
import com.zlcp.latteec.main.sort.content.ContentFragment;
import com.zlcp.latteui.recycler.ItemType;
import com.zlcp.latteui.recycler.MultipleFields;
import com.zlcp.latteui.recycler.MultipleItemEntity;
import com.zlcp.latteui.recycler.MultipleViewHolder;

import java.util.List;

import me.yokeyword.fragmentation.SupportHelper;

/**
 * 作者：zl_freedom
 * 时间：2019/7/31 18:35
 * Note：
 */
public class SortRecyclerAdapter extends
        BaseMultiItemQuickAdapter<MultipleItemEntity, MultipleViewHolder> {


    private final SortFragment FRAGMENT;
    private int mPrePosition = 0;

    protected SortRecyclerAdapter(List<MultipleItemEntity> data, SortFragment fragment) {
        super(data);
        this.FRAGMENT = fragment;
        //添加垂直菜单布局
        addItemType(ItemType.VERTICAL_MENU_LIST, R.layout.item_vertical_menu_list);
    }

    @Override
    protected void convert(final MultipleViewHolder helper, final MultipleItemEntity item) {
        switch (helper.getItemViewType()) {
            case ItemType.VERTICAL_MENU_LIST:
                final String text = item.getField(MultipleFields.TEXT);
                final boolean isClicked = item.getField(MultipleFields.TAG);

                final AppCompatTextView name = helper.getView(R.id.tv_vertical_item_name);
                final View line = helper.getView(R.id.view_line);

                final View itemView = helper.itemView;
                itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final int currentPosition = helper.getAdapterPosition();
                        if (mPrePosition != currentPosition) {
                            //还原上一个
                            getData().get(mPrePosition).setField(MultipleFields.TAG, false);
                            notifyItemChanged(mPrePosition);
                            //更新选中的item
                            item.setField(MultipleFields.TAG, true);
                            notifyItemChanged(currentPosition);
                            mPrePosition = currentPosition;
                            final int contentId = getData().get(currentPosition).getField(MultipleFields.ID);
                            showContent(contentId);
                        }
                    }
                });

                if (!isClicked) {
                    line.setVisibility(View.INVISIBLE);
                    name.setTextColor(ContextCompat.getColor(mContext, R.color.we_chat_black));
                    itemView.setBackgroundColor(ContextCompat.getColor(mContext, R.color.item_background));
                } else {
                    line.setVisibility(View.VISIBLE);
                    name.setTextColor(ContextCompat.getColor(mContext, R.color.app_main));
                    line.setBackgroundColor(ContextCompat.getColor(mContext, R.color.app_main));
                    itemView.setBackgroundColor(Color.WHITE);
                }
                helper.setText(R.id.tv_vertical_item_name, text);
                break;
            default:
                break;
        }
    }

    private void showContent(int contentId) {
        final ContentFragment fragment = ContentFragment.newInstance(contentId);
        switchContent(fragment);
    }

    private void switchContent(ContentFragment fragment) {
        final LatteFragment contentFragment =
                SupportHelper.findFragment(FRAGMENT.getChildFragmentManager(), ContentFragment.class);
        if (contentFragment != null) {
            contentFragment.getSupportDelegate().replaceFragment(fragment, false);
        }
    }
}
