package com.zlcp.lattecore.web;

import android.os.Bundle;
import android.webkit.WebView;

import androidx.annotation.Nullable;

import com.zlcp.lattecore.app.ConfigKeys;
import com.zlcp.lattecore.app.Latte;
import com.zlcp.lattecore.fragments.LatteFragment;
import com.zlcp.lattecore.web.route.RouteKeys;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * 作者：zl_freedom
 * 时间：2019/8/1 12:41
 * Note：
 */
public abstract class WebFragment extends LatteFragment implements IWebViewInitializer {
    private WebView mWebView = null;
    private final ReferenceQueue<WebView> WEB_VIEW_QUEUE = new ReferenceQueue<>();
    private String mUrl = null;
    private boolean mIsWebAvailable = false;
    private LatteFragment mTopFragment = null;

    public WebFragment() {
    }

    public abstract IWebViewInitializer setInitializer();

    //获取跳转后的参数

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        if (args != null) {
            mUrl = args.getString(RouteKeys.URL.name());
        }
        initWebView();
    }

    private void initWebView() {
        if (mWebView != null) {
            mWebView.removeAllViews();
            mWebView.destroy();
        } else {
            final IWebViewInitializer initializer = setInitializer();
            if (initializer != null) {
                //使用弱应用 WebView在xml中定义可能会有oom内存泄漏 使用new
                final WeakReference<WebView> webViewWeakReference =
                        new WeakReference<>(new WebView(getContext()), WEB_VIEW_QUEUE);
                mWebView = webViewWeakReference.get();
                mWebView = initializer.initWebView(mWebView);
                mWebView.setWebViewClient(initializer.initWebViewClient());
                mWebView.setWebChromeClient(initializer.initWebChromeClient());
                final String name = Latte.getConfiguration(ConfigKeys.JAVASCRIPT_INTERFACE);
                mWebView.addJavascriptInterface(LatteWebInterface.create(this), name);
                mIsWebAvailable = true;
            } else {
                throw new NullPointerException("Initializer is null!");
            }
        }
    }

    public void setTopFragment(LatteFragment fragment) {
        mTopFragment = fragment;
    }

    public LatteFragment getTopFragment() {
        if (mTopFragment == null) {
            mTopFragment = this;
        }
        return mTopFragment;
    }

    public WebView getWebView() {
        if (mWebView == null) {
            throw new NullPointerException("WebView IS NULL");
        }
        return mIsWebAvailable ? mWebView : null;
    }

    public String getUrl() {
        if (mUrl == null) {
            throw new NullPointerException("WebView IS NULL");
        }
        return mUrl;
    }

    //WebView跟随fragment生命周期
    @Override
    public void onPause() {
        super.onPause();
        if (mWebView != null) {
            mWebView.onPause();
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mWebView != null) {
            mWebView.onResume();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mIsWebAvailable = false;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mWebView != null) {
            mWebView.removeAllViews();
            mWebView.destroy();
            mWebView = null;
        }
    }
}
