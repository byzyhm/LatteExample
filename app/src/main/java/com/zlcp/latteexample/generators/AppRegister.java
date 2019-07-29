package com.zlcp.latteexample.generators;


import com.zlcp.latteannotations.annotations.AppRegisterGenerator;
import com.zlcp.lattecore.wechat.template.AppRegisterTemplate;

@SuppressWarnings("unused")
@AppRegisterGenerator(
        packageName = "com.zlcp.latteexample",
        registerTemplate = AppRegisterTemplate.class
)
public interface AppRegister {

}
