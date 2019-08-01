package com.zlcp.lattecore.web;

import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * 作者：zl_freedom
 * 时间：2019/8/1 12:44
 * Note：
 */
public interface IWebViewInitializer {
    WebView initWebView(WebView webView);
    //针对浏览器本省的控制
    WebViewClient initWebViewClient();
    //针对页面的控制
    WebChromeClient initWebChromeClient();
}
