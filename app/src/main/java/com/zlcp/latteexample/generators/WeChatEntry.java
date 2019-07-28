package com.zlcp.latteexample.generators;

import com.zlcp.latteannotations.annotations.EntryGenerator;
import com.zlcp.lattecore.wechat.template.WXEntryTemplate;

/**
 * 作者：zl_freedom
 * 时间：2019/7/28 16:38
 * Note：
 */
@EntryGenerator(
        packageName = "com.zlcp.latteexample",
        entryTemplate =  WXEntryTemplate.class
)
public interface WeChatEntry {
}
