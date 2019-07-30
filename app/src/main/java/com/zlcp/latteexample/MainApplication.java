package com.zlcp.latteexample;

import android.app.Application;

import androidx.multidex.MultiDex;

import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.zlcp.lattecore.app.Latte;
import com.zlcp.lattecore.net.interceptors.AddCookieInterceptor;
import com.zlcp.lattecore.net.interceptors.DebugInterceptor;
import com.zlcp.latteec.database.DatabaseManager;
import com.zlcp.latteec.icon.FontEcModule;


/**
 * 作者：zl_freedom
 * 时间：2019/7/20 13:52
 * 功能描述：
 */
public class MainApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Latte.init(this)
                .withIcon(new FontAwesomeModule())                       //Awesome风格图片
                .withIcon(new FontEcModule())                            //自定义IconText
//                .withApiHost("http://news.baidu.com/")
                .withApiHost("http://mock.fulingjie.com/mock-android/api/")
//                .withInterceptor(new AddCookieInterceptor())// 增加Cookie同步拦截器
//                .withInterceptor(new DebugInterceptor("index", R.raw.test))
                .withWeChatAppId("Your apply App_id")                    //微信开放平台Id(申请了一个还在审核)
                .withWeChatAppSecret("Your apply Secret_id")
                .configure();
        MultiDex.install(this);
//        initStetho();
        DatabaseManager.getInstance().initDao(this);

    }

//    private void initStetho() {
//        Stetho.initialize(
//                Stetho.newInitializerBuilder(this)
//                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
//                .build());
//    }
}
