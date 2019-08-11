package com.zlcp.latteec.main.personal.settings;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blankj.utilcode.util.ToastUtils;
import com.zlcp.lattecore.fragments.LatteFragment;
import com.zlcp.lattecore.util.callback.CallbackManager;
import com.zlcp.lattecore.util.callback.CallbackType;
import com.zlcp.latteec.R;
import com.zlcp.latteec.main.personal.address.AddressFragment;
import com.zlcp.latteec.main.personal.list.ListAdapter;
import com.zlcp.latteec.main.personal.list.ListBean;
import com.zlcp.latteec.main.personal.list.ListItemType;

import java.util.ArrayList;
import java.util.List;

import static com.blankj.utilcode.util.BarUtils.getStatusBarHeight;

public class SettingsFragment extends LatteFragment {

    @Override
    public Object setLayout() {
        return R.layout.fragment_settings;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View root) {
        final Toolbar mToolbar = $(R.id.tb_settings);
        final RecyclerView mRecyclerView = $(R.id.rv_settings);

        mToolbar.setPadding(0, getStatusBarHeight(), 0, 0);

        final ListBean push = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_SWITCH)
                .setId(1)
                .setFragment(new AddressFragment())
                .setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @SuppressWarnings("unchecked")
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked){
                            CallbackManager.getInstance().getCallback(CallbackType.TAG_OPEN_PUSH).executeCallback(null);
                            ToastUtils.showShort("推送已打开");
                        } else {
                            CallbackManager.getInstance().getCallback(CallbackType.TAG_STOP_PUSH).executeCallback(null);
                            ToastUtils.showShort("推送已关闭");
                        }
                    }
                })
                .setText("消息推送")
                .build();

        final ListBean about = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setId(2)
                .setFragment(new AboutFragment())
                .setText("关于我们")
                .build();

        final List<ListBean> data = new ArrayList<>();
        data.add(push);
        data.add(about);

        //设置RecyclerView
        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        final ListAdapter adapter = new ListAdapter(data);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.addOnItemTouchListener(new SettingsClickListener(this));

    }
}
