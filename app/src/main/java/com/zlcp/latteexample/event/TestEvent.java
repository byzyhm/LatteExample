package com.zlcp.latteexample.event;

import android.webkit.WebView;
import android.widget.Toast;

import com.zlcp.lattecore.web.event.Event;

/**
 * 作者：zl_freedom
 * 时间：2019/8/2 01:25
 * Note：
 */
public class TestEvent extends Event {
    @Override
    public String execute(String params) {
        //js事件传递给原生事件
        Toast.makeText(getContext(), "您单机了js事件" + getAction(), Toast.LENGTH_SHORT).show();
        //原生事件传递给js
        if (getAction().equals("test")) {
            final WebView webView = getWebView();
            webView.post(new Runnable() {
                @Override
                public void run() {
                    webView.evaluateJavascript("nativeCall();", null);
                }
            });
        }
        return null;
    }
}
