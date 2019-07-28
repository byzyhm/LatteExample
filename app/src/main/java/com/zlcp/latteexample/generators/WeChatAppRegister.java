package com.zlcp.latteexample.generators;

import com.zlcp.latteannotations.annotations.AppRegisterGenerator;
import com.zlcp.lattecore.wechat.template.AppRegisterTemplate;

/**
 * 作者：zl_freedom
 * 时间：2019/7/28 16:38
 * Note：
 */
@AppRegisterGenerator(
        packageName = "com.zlcp.latteexample",
        registerTemplate = AppRegisterTemplate.class
)
public interface WeChatAppRegister {
}
