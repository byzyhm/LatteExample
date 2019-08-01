package com.zlcp.lattecore.web;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.blankj.utilcode.util.BarUtils;
import com.zlcp.lattecore.R;
import com.zlcp.lattecore.fragments.LatteFragment;
import com.zlcp.lattecore.web.route.RouteKeys;

/**
 * 作者：zl_freedom
 * 时间：2019/8/1 23:46
 * Note：
 */
public class WebViewFragment extends LatteFragment {
    private String mUrl;

    public static WebViewFragment newInstance(String url) {
        Bundle args = new Bundle();
        args.putString(RouteKeys.URL.name(), url);
        WebViewFragment fragment = new WebViewFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        if (args != null) {
            mUrl = args.getString(RouteKeys.URL.name());
        }
    }

    @Override
    public Object setLayout() {
        return R.layout.fragment_web;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View root) {
        Toolbar mToolbar = $(R.id.tb_discover);
        mToolbar.setPadding(0, BarUtils.getStatusBarHeight(), 0, 0);
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        final WebFragmentImpl fragment = WebFragmentImpl.create(mUrl);
        //final WebFragmentImpl fragment = WebFragmentImpl.create("https://www.csdn.net/");
        fragment.setTopFragment(this);
        getSupportDelegate().loadRootFragment(R.id.web_discovery_container, fragment);
    }
}
