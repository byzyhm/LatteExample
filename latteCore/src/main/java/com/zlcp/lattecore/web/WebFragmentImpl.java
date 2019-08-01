package com.zlcp.lattecore.web;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.zlcp.lattecore.web.chromeclient.WebChromeClientImpl;
import com.zlcp.lattecore.web.client.WebViewClientImpl;
import com.zlcp.lattecore.web.route.RouteKeys;
import com.zlcp.lattecore.web.route.Router;

/**
 * 作者：zl_freedom
 * 时间：2019/8/1 12:53
 * Note：
 */
public class WebFragmentImpl extends WebFragment {

    private IPageLoadListener mIPageLoadListener = null;

    public static WebFragmentImpl create(String url) {
        final Bundle args = new Bundle();
        args.putString(RouteKeys.URL.name(), url);
        final WebFragmentImpl fragment = new WebFragmentImpl();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public IWebViewInitializer setInitializer() {
        return this;
    }

    @Override
    public Object setLayout() {
        return getWebView();
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View root) {
        if (getUrl() != null) {
            //用原生的方式模仿Web跳转，并进行页面加载
            Router.getInstance().loadPage(this, getUrl());
        }
    }


    @Override
    public WebView initWebView(WebView webView) {
        //设置一下WebView属性
        return new WebViewInitializer().createWebView(webView);
    }
    //给外界提供设置listener方法
    public void setPageLoadListener(IPageLoadListener listener){
        this.mIPageLoadListener = listener;
    }

    @Override
    public WebViewClient initWebViewClient() {
        final WebViewClientImpl client = new WebViewClientImpl(this);
        client.setPageLoadListener(mIPageLoadListener);
        return client;
    }

    @Override
    public WebChromeClient initWebChromeClient() {
        return new WebChromeClientImpl();
    }
}
