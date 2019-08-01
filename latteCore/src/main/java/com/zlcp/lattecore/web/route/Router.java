package com.zlcp.lattecore.web.route;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.webkit.URLUtil;
import android.webkit.WebView;

import androidx.core.content.ContextCompat;

import com.zlcp.lattecore.fragments.LatteFragment;
import com.zlcp.lattecore.web.WebFragment;
import com.zlcp.lattecore.web.WebViewFragment;

/**
 * 作者：zl_freedom
 * 时间：2019/8/1 13:04
 * Note：
 */
public class Router {
    private Router() {
    }

    //线程安全的惰性单例
    private static final class Holder {
        private static final Router INSTANCE = new Router();
    }

    public static Router getInstance() {
        return Holder.INSTANCE;
    }

    public final boolean handleWebUrl(WebFragment fragment, String url) {
        //如果是电话协议
        if (url.contains("tel:")) {
            callPhone(fragment.getContext(), url);
        }

        final LatteFragment topFragment = fragment.getTopFragment();
        final WebViewFragment secondActivity = WebViewFragment.newInstance(url);
        topFragment.getSupportDelegate().start(secondActivity);
        return true;
    }

    private void loadWebPage(WebView webView, String url) {
        if (webView != null) {
            webView.loadUrl(url);
        } else {
            throw new NullPointerException("WebView is null !");
        }
    }

    //加载本地网页
    private void loadLocalPage(WebView webView, String url) {
        loadWebPage(webView, "file:///android_asset/" + url);
    }


    private void loadPage(WebView webView, String url) {
        if (URLUtil.isNetworkUrl(url) || URLUtil.isAssetUrl(url)) {
            loadWebPage(webView, url);
        } else {
            loadLocalPage(webView, url);
        }
    }

    public void loadPage(WebFragment fragment, String url) {
        loadPage(fragment.getWebView(), url);
    }

    //打电话
    private void callPhone(Context context, String url) {
        final Intent intent = new Intent(Intent.ACTION_DIAL);
        final Uri data = Uri.parse(url);
        intent.setData(data);
        ContextCompat.startActivity(context, intent, null);
    }
}
