package com.zlcp.lattecore.web.client;

import android.graphics.Bitmap;
import android.os.Handler;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.zlcp.lattecore.app.Latte;
import com.zlcp.lattecore.ui.loader.FragmentLoader;
import com.zlcp.lattecore.util.log.LogUtils;
import com.zlcp.lattecore.web.IPageLoadListener;
import com.zlcp.lattecore.web.WebFragment;
import com.zlcp.lattecore.web.route.Router;

/**
 * 作者：zl_freedom
 * 时间：2019/8/1 12:59
 * Note：
 */
public class WebViewClientImpl extends WebViewClient {
    private final WebFragment FRAGMENT;
    private IPageLoadListener mIPageLoadListener = null;
    private static final Handler HANDLER = Latte.getHandler();

    public void setPageLoadListener(IPageLoadListener listener) {
        this.mIPageLoadListener = listener;
    }

    public WebViewClientImpl(WebFragment fragment) {
        this.FRAGMENT = fragment;
    }

    /**
     *  拦截关键方法，当点击WebView链接时，我们打开新的fragment加载
     *  true: 由我们接管处理（由原生来接管了）
     *  false: 由WebView处理它的事件
     */
    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        LogUtils.e("shouldOverrideUrlLoading", url);
        return Router.getInstance().handleWebUrl(FRAGMENT, url);
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
        if (mIPageLoadListener != null) {
            mIPageLoadListener.onLoadStart();
        }
        FragmentLoader.showLoading(view.getContext());
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        if (mIPageLoadListener != null) {
            mIPageLoadListener.onLoadEnd();
        }
        HANDLER.postDelayed(new Runnable() {
            @Override
            public void run() {
                FragmentLoader.stopLoading();
            }
        }, 500);
    }
}
