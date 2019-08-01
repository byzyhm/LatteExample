package com.zlcp.latteexample;

import android.app.Application;

import androidx.multidex.MultiDex;

import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.zlcp.lattecore.app.Latte;
import com.zlcp.latteec.database.DatabaseManager;
import com.zlcp.latteec.icon.FontEcModule;
import com.zlcp.latteexample.event.TestEvent;


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
//                .withApiHost("http://192.168.0.109/RestServer/data/")
                .withApiHost("http://mock.fulingjie.com/mock-android/api/")
                .withWeChatAppId("wxb9ab1d354462b149")                    //微信开放平台Id
                .withWeChatAppSecret("8b40af539a76953a518ee2c988588827")
                .withJavascriptInterface("latte")
                .withWebEvent("test", new TestEvent())                   //Js和原生交互
//                .withWebEvent("share", new ShareEvent())
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
