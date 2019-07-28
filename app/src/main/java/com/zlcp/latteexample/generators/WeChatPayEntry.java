package com.zlcp.latteexample.generators;

import com.zlcp.latteannotations.annotations.PayEntryGenerator;
import com.zlcp.lattecore.wechat.template.WXPayEntryTemplate;

/**
 * 作者：zl_freedom
 * 时间：2019/7/28 16:38
 * Note：
 */
@PayEntryGenerator(
        packageName = "com.zlcp.latteexample",
        payEntryTemplate = WXPayEntryTemplate.class
)
public interface WeChatPayEntry {
}
