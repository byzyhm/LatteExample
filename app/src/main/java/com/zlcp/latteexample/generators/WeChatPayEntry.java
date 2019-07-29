package com.zlcp.latteexample.generators;


import com.zlcp.latteannotations.annotations.PayEntryGenerator;
import com.zlcp.lattecore.wechat.template.WXPayEntryTemplate;

@SuppressWarnings("unused")
@PayEntryGenerator(
        packageName = "com.zlcp.latteexample",
        payEntryTemplate = WXPayEntryTemplate.class
)
public interface WeChatPayEntry {

}
