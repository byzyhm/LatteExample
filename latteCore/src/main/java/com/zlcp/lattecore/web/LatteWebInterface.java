package com.zlcp.lattecore.web;

import android.webkit.JavascriptInterface;

import com.alibaba.fastjson.JSON;
import com.zlcp.lattecore.util.log.LogUtils;
import com.zlcp.lattecore.web.event.Event;
import com.zlcp.lattecore.web.event.EventManager;

/**
 * 作者：zl_freedom
 * 时间：2019/8/1 12:49
 * Note：用来和原生进行交互
 */
public class LatteWebInterface {
    private final WebFragment FRAGMENT;

    public LatteWebInterface(WebFragment fragment) {
        this.FRAGMENT = fragment;
    }

    //简单工厂方法
    static LatteWebInterface create(WebFragment fragment) {
        return new LatteWebInterface(fragment);
    }

    @SuppressWarnings("unused")
    @JavascriptInterface
    public String event(String params) {
        final String action = JSON.parseObject(params).getString("action");
        final Event event = EventManager.getInstance().createEvent(action);
        LogUtils.e("WEB_EVENT", params);
        if (event != null) {
            event.setAction(action);
            event.setFragment(FRAGMENT);
            event.setContext(FRAGMENT.getContext());
            event.setUrl(FRAGMENT.getUrl());
            return event.execute(params);
        }
        return null;
    }
}
