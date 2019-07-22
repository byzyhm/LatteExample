package com.zlcp.latteexample;

import android.app.Application;

import com.zlcp.lattecore.app.Latte;


/**
 * 作者：zl_freedom
 * 时间：2019/7/20 13:52
 * 功能描述：
 */
public class ExampleApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
//                .withI
                .configure();
    }
}
