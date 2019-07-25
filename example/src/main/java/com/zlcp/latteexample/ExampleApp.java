package com.zlcp.latteexample;

import android.app.Application;

import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.zlcp.lattecore.app.ConfigKeys;
import com.zlcp.lattecore.app.Configurator;
import com.zlcp.lattecore.app.Latte;
import com.zlcp.lattecore.net.interceptors.AddCookieInterceptor;
import com.zlcp.lattecore.net.interceptors.DebugInterceptor;
import com.zlcp.latteec.icon.FontEcModule;


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
                .withIcon(new FontAwesomeModule())                       //Awesome风格图片
                .withIcon(new FontEcModule())                            //自定义IconText
                .withApiHost("http://news.baidu.com/")
                .withInterceptor(new AddCookieInterceptor())// 增加Cookie同步拦截器
                .withInterceptor(new DebugInterceptor("index",R.raw.test))
                .configure();
//        String url = (String) Configurator.getInstance().getLatteConfigs().get(ConfigKeys.API_HOST);
    }
}
