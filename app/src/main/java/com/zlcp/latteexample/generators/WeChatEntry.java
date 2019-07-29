package com.zlcp.latteexample.generators;


import com.zlcp.latteannotations.annotations.EntryGenerator;
import com.zlcp.lattecore.wechat.template.WXEntryTemplate;

@SuppressWarnings("unused")
@EntryGenerator(
        packageName = "com.zlcp.latteexample",
        entryTemplate =  WXEntryTemplate.class
)
public interface WeChatEntry {

}
