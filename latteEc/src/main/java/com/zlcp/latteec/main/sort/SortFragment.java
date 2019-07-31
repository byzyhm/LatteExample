package com.zlcp.latteec.main.sort;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.blankj.utilcode.util.BarUtils;
import com.zlcp.lattecore.fragments.bottom.BottomItemFragment;
import com.zlcp.latteec.R;
import com.zlcp.latteec.main.sort.content.ContentFragment;
import com.zlcp.latteec.main.sort.list.VerticalListFragment;

/**
 * 作者：zl_freedom
 * 时间：2019/7/30 01:30
 * Note：
 */
public class SortFragment extends BottomItemFragment {
    @Override
    public Object setLayout() {
        return R.layout.fragment_sort;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View root) {
        Toolbar mToolbar = $(R.id.tb_sort);
        mToolbar.setPadding(0, BarUtils.getStatusBarHeight(), 0, 0);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        final VerticalListFragment listFragment = new VerticalListFragment();
        getSupportDelegate().loadRootFragment(R.id.vertical_list_container, listFragment);
        //设置右侧fragment,默认显示分类一
        getSupportDelegate().loadRootFragment(R.id.sort_content_container, ContentFragment.newInstance(1));
    }
}
