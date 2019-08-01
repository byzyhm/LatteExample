package com.zlcp.lattecore.web.event;

import android.content.Context;
import android.webkit.WebView;

import com.zlcp.lattecore.fragments.LatteFragment;
import com.zlcp.lattecore.web.WebFragment;

/**
 * 作者：zl_freedom
 * 时间：2019/8/1 13:41
 * Note：
 */
public abstract class Event implements IEvent {
    private Context mContext = null;
    private String mAction = null;
    private WebFragment mFragment = null;
    private String mUrl = null;
    private WebView mWebView = null;


    public Context getContext() {
        return mContext;
    }

    public void setContext(Context mContext) {
        this.mContext = mContext;
    }

    public String getAction() {
        return mAction;
    }

    public void setAction(String mAction) {
        this.mAction = mAction;
    }

    public LatteFragment getFragment() {
        return mFragment;
    }

    public void setFragment(WebFragment mFragment) {
        this.mFragment = mFragment;
    }

    public String getUrl() {
        return mUrl;
    }

    public void setUrl(String mUrl) {
        this.mUrl = mUrl;
    }

    public WebView getWebView() {
        return mFragment.getWebView();
    }

}
