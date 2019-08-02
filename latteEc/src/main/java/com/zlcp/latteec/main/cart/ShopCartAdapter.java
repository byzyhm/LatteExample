package com.zlcp.latteec.main.cart;

import android.graphics.Color;
import android.view.Gravity;
import android.view.View;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.content.ContextCompat;

import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.joanzapata.iconify.widget.IconTextView;
import com.zlcp.lattecore.app.Latte;
import com.zlcp.lattecore.net.RestClient;
import com.zlcp.lattecore.net.callback.ISuccess;
import com.zlcp.latteec.R;
import com.zlcp.latteui.recycler.MultipleFields;
import com.zlcp.latteui.recycler.MultipleItemEntity;
import com.zlcp.latteui.recycler.MultipleViewHolder;

import java.util.List;

/**
 * 作者：zl_freedom
 * 时间：2019/8/2 17:14
 * Note：
 */
public class ShopCartAdapter extends BaseMultiItemQuickAdapter<MultipleItemEntity, MultipleViewHolder> {

    private boolean mIsSelectedAll = true;
    private ICartItemListener mCartItemListener;
    private double mTotalPrice = 0.00;

    private static final RequestOptions OPTIONS = new RequestOptions()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .centerCrop()
            .dontAnimate();

    public ShopCartAdapter(List<MultipleItemEntity> data) {
        super(data);
        //初始化总价
        for (MultipleItemEntity entity : data) {
            final double price = entity.getField(ShopCartItemFields.PRICE);
            final int count = entity.getField(ShopCartItemFields.COUNT);
            final double total = price * count;
            mTotalPrice += total;
        }
        //添加购物类型布局 只包含一种
        addItemType(ShopCartItemType.SHOP_CART_ITEM, R.layout.item_shop_cart);
    }

    public void setIsSelectedAll(boolean isSelectedAll) {
        this.mIsSelectedAll = isSelectedAll;
    }

    public void setCartItemListener(ICartItemListener listener) {
        this.mCartItemListener = listener;
    }

    public double getTotalPrice() {
        return mTotalPrice;
    }

    @Override
    protected void convert(MultipleViewHolder helper, final MultipleItemEntity item) {
        switch (helper.getItemViewType()) {
            case ShopCartItemType.SHOP_CART_ITEM:
                //取出所有值
                final int id = item.getField(MultipleFields.ID);
                final String thumb = item.getField(MultipleFields.IMAGE_URL);
                final String title = item.getField(ShopCartItemFields.TITLE);
                final String desc = item.getField(ShopCartItemFields.DESC);
                final int count = item.getField(ShopCartItemFields.COUNT);
                final double price = item.getField(ShopCartItemFields.PRICE);
                //取出所有控件
                final AppCompatImageView imgThumb = helper.getView(R.id.image_item_shop_cart);
                final AppCompatTextView tvTitle = helper.getView(R.id.tv_item_shop_cart_title);
                final AppCompatTextView tvDesc = helper.getView(R.id.tv_item_shop_cart_desc);
                final AppCompatTextView tvPrice = helper.getView(R.id.tv_item_shop_cart_price);
                final IconTextView iconMinus = helper.getView(R.id.icon_item_minus);
                final IconTextView iconPlus = helper.getView(R.id.icon_item_plus);
                final AppCompatTextView tvCount = helper.getView(R.id.tv_item_shop_cart_count);
                final IconTextView iconIsSelected = helper.getView(R.id.icon_item_shop_cart);
                //赋值
                tvTitle.setText(title);
                tvDesc.setText(desc);
                tvCount.setText(String.valueOf(count));
                tvPrice.setText(String.valueOf(price));
                Glide.with(mContext)
                        .load(thumb)
                        .apply(OPTIONS)
                        .into(imgThumb);
                //在左侧勾勾渲染之前改变全选与否状态
                item.setField(ShopCartItemFields.IS_SELECTED, mIsSelectedAll);
                final boolean isSelected = item.getField(ShopCartItemFields.IS_SELECTED);
                //根据数据状态显示左侧勾勾
                if (isSelected) {
                    iconIsSelected.setTextColor(
                            ContextCompat.getColor(Latte.getApplicationContext(), R.color.app_main));
                } else {
                    iconIsSelected.setTextColor(Color.GRAY);
                }
                //添加左侧勾勾点击事件
                iconIsSelected.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final boolean currentSelected = item.getField(ShopCartItemFields.IS_SELECTED);
                        if (currentSelected) {
                            iconIsSelected.setTextColor(Color.GRAY);
                            item.setField(ShopCartItemFields.IS_SELECTED, false);
                        } else {
                            iconIsSelected.setTextColor(ContextCompat.getColor(Latte.getApplicationContext(), R.color.app_main));
                            item.setField(ShopCartItemFields.IS_SELECTED, true);
                        }
                    }
                });
                //商品减事件
                iconMinus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final int currentCount = item.getField(ShopCartItemFields.COUNT);
                        if (Integer.parseInt(tvCount.getText().toString()) > 1) {
                            RestClient.builder()
                                    .url("shop_cart_count.php")
                                    .loader(mContext)
                                    .params("count", currentCount)
                                    .success(new ISuccess() {
                                        @Override
                                        public void onSuccess(String response) {
                                            int countNum = Integer.parseInt(tvCount.getText().toString());
                                            countNum--;
                                            tvCount.setText(String.valueOf(countNum));
                                            if (mCartItemListener != null) {
                                                mTotalPrice = mTotalPrice - price;//总价
                                                final double itemTotal = countNum * price;//item总价
                                                mCartItemListener.onItemClick(itemTotal);
                                            }
                                        }
                                    })
                                    .build()
                                    .post();
                        } else {
                            ToastUtils.showShort("最少购买一件商品哦o！");
                            ToastUtils.setGravity(Gravity.CENTER, 0, 0);
                        }
                    }
                });

                //商品加加事件
                iconPlus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final int currentCount = item.getField(ShopCartItemFields.COUNT);
                        RestClient.builder()
                                .url("shop_cart_count.php")
                                .loader(mContext)
                                .params("count", currentCount)
                                .success(new ISuccess() {
                                    @Override
                                    public void onSuccess(String response) {
                                        int countNum = Integer.parseInt(tvCount.getText().toString());
                                        countNum++;
                                        tvCount.setText(String.valueOf(countNum));
                                        if (mCartItemListener != null) {
                                            mTotalPrice = mTotalPrice + price;
                                            final double itemTotal = countNum * price;
                                            mCartItemListener.onItemClick(itemTotal);
                                        }
                                    }
                                })
                                .build()
                                .post();
                    }
                });
                break;
            default:
                break;
        }
    }
}
