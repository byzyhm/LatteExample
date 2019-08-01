package com.zlcp.latteec.main.discover;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.blankj.utilcode.util.BarUtils;
import com.zlcp.lattecore.fragments.bottom.BottomItemFragment;
import com.zlcp.lattecore.web.WebFragmentImpl;
import com.zlcp.latteec.R;

/**
 * 作者：zl_freedom
 * 时间：2019/8/1 12:27
 * Note：
 */
public class DiscoverFragment extends BottomItemFragment {
    @Override
    public Object setLayout() {
        return R.layout.fragment_discover;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View root) {
        Toolbar mToolbar = $(R.id.tb_discover);
        mToolbar.setPadding(0, BarUtils.getStatusBarHeight(), 0, 0);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        final WebFragmentImpl fragment = WebFragmentImpl.create("index.html");
        //        final WebFragmentImpl fragment = WebFragmentImpl.create("https://www.csdn.net/");
        fragment.setTopFragment(this.getParentFragments());
        getSupportDelegate().loadRootFragment(R.id.web_discovery_container, fragment);
    }
}
