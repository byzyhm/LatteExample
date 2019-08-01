package com.zlcp.lattecore.web.event;

import com.zlcp.lattecore.util.log.LogUtils;

/**
 * 作者：zl_freedom
 * 时间：2019/8/1 13:43
 * Note：
 */
public class UndefineEvent extends Event {
    @Override
    public String execute(String params) {
        LogUtils.e("UndefineEvent", params);
        return null;
    }
}
