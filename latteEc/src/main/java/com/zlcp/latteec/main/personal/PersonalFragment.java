package com.zlcp.latteec.main.personal;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zlcp.lattecore.fragments.bottom.BottomItemFragment;
import com.zlcp.latteec.R;
import com.zlcp.latteec.main.personal.address.AddressFragment;
import com.zlcp.latteec.main.personal.list.ListAdapter;
import com.zlcp.latteec.main.personal.list.ListBean;
import com.zlcp.latteec.main.personal.list.ListItemType;
import com.zlcp.latteec.main.personal.order.OrderListFragment;
import com.zlcp.latteec.main.personal.profile.UserProfileFragment;
import com.zlcp.latteec.main.personal.settings.SettingsFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：zl_freedom
 * 时间：2019/8/3 04:43
 * Note：
 */
public class PersonalFragment extends BottomItemFragment {
    public static final String ORDER_TYPE = "ORDER_TYPE";
    private Bundle mArgs = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mArgs = new Bundle();
    }

    private void onClickAllOrder() {
        mArgs.putString(ORDER_TYPE, "all");
        startOrderListByType();
    }

    private void startOrderListByType() {
        final OrderListFragment fragment = new OrderListFragment();
        fragment.setArguments(mArgs);
        getParentFragments().getSupportDelegate().start(fragment);
    }

    @Override
    public Object setLayout() {
        return R.layout.fragment_personal;

    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View root) {
        final RecyclerView rvSettings = $(R.id.rv_personal_setting);
        $(R.id.tv_all_order).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickAllOrder();
            }
        });
        $(R.id.img_user_avatar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickAvatar();
            }
        });

        final ListBean address = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setId(1)
                .setFragment(new AddressFragment())
                .setText("收货地址")
                .build();

        final ListBean system = new ListBean.Builder()
                .setItemType(ListItemType.ITEM_NORMAL)
                .setId(2)
                .setFragment(new SettingsFragment())
                .setText("系统设置")
                .build();

        final List<ListBean> data = new ArrayList<>();
        data.add(address);
        data.add(system);

        final LinearLayoutManager manager = new LinearLayoutManager(getContext());
        rvSettings.setLayoutManager(manager);
        final ListAdapter adapter = new ListAdapter(data);
        rvSettings.setAdapter(adapter);
        rvSettings.addOnItemTouchListener(new PersonalClickListener(this));
    }

    private void onClickAvatar() {
        getParentFragments().getSupportDelegate().start(new UserProfileFragment());
    }
}
