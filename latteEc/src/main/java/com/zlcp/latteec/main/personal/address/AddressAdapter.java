package com.zlcp.latteec.main.personal.address;

import android.view.View;

import androidx.appcompat.widget.AppCompatTextView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.zlcp.lattecore.net.RestClient;
import com.zlcp.lattecore.net.callback.ISuccess;
import com.zlcp.latteec.R;
import com.zlcp.latteui.recycler.MultipleFields;
import com.zlcp.latteui.recycler.MultipleItemEntity;
import com.zlcp.latteui.recycler.MultipleViewHolder;

import java.util.List;

public class AddressAdapter extends BaseMultiItemQuickAdapter<MultipleItemEntity, MultipleViewHolder> {

    protected AddressAdapter(List<MultipleItemEntity> data) {
        super(data);
        addItemType(AddressItemType.ITEM_ADDRESS, R.layout.item_address);
    }

    @Override
    protected void convert(final MultipleViewHolder helper, MultipleItemEntity item) {
        switch (helper.getItemViewType()) {
            case AddressItemType.ITEM_ADDRESS:
                final String name = item.getField(MultipleFields.NAME);
                final String phone = item.getField(AddressItemFields.PHONE);
                final String address = item.getField(AddressItemFields.ADDRESS);
                final boolean isDefault = item.getField(MultipleFields.TAG);
                final int id = item.getField(MultipleFields.ID);

                final AppCompatTextView nameText = helper.getView(R.id.tv_address_name);
                final AppCompatTextView phoneText = helper.getView(R.id.tv_address_phone);
                final AppCompatTextView addressText = helper.getView(R.id.tv_address_address);
                final AppCompatTextView deleteTextView = helper.getView(R.id.tv_address_delete);
                deleteTextView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        RestClient.builder()
                                .url("address.php")
                                .params("id", id)
                                .success(new ISuccess() {
                                    @Override
                                    public void onSuccess(String response) {
                                        remove(helper.getLayoutPosition());
                                    }
                                })
                                .build()
                                .post();
                    }
                });

                nameText.setText(name);
                phoneText.setText(phone);
                addressText.setText(address);
                break;
            default:
                break;

        }
    }
}
