package com.zlcp.latteexample;

import android.app.Application;

import androidx.annotation.Nullable;
import androidx.multidex.MultiDex;

import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.mob.MobSDK;
import com.zlcp.lattecore.app.Latte;
import com.zlcp.lattecore.net.interceptors.DebugInterceptor;
import com.zlcp.lattecore.util.callback.CallbackManager;
import com.zlcp.lattecore.util.callback.CallbackType;
import com.zlcp.lattecore.util.callback.IGlobalCallback;
import com.zlcp.latteec.database.DatabaseManager;
import com.zlcp.latteec.icon.FontEcModule;
import com.zlcp.latteexample.event.ShareEvent;
import com.zlcp.latteexample.event.TestEvent;

import cn.jpush.android.api.JPushInterface;


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
                .withWebEvent("share", new ShareEvent())
                //模拟首页数据（Android9.0真机不能访问网络了）
//                .withInterceptor(new DebugInterceptor("index.php", R.raw.index_data))
                .configure();
        MultiDex.install(this);
//        initStetho();
        //GreenDao初始化
        DatabaseManager.getInstance().initDao(this);

        //开启极光推送
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);

        //一键分享初始化
        MobSDK.init(this);

        //子模块关闭推送 反向控制主模块关闭推送 接口实现
        CallbackManager.getInstance()
                .addCallback(CallbackType.TAG_OPEN_PUSH, new IGlobalCallback() {
                    @Override
                    public void executeCallback(@Nullable Object args) {
                        if (JPushInterface.isPushStopped(Latte.getApplicationContext())) {
                            //开启极光推送
                            JPushInterface.setDebugMode(true);
                            JPushInterface.init(Latte.getApplicationContext());
                        }
                    }
                })
                .addCallback(CallbackType.TAG_STOP_PUSH, new IGlobalCallback() {
                    @Override
                    public void executeCallback(@Nullable Object args) {
                        if (!JPushInterface.isPushStopped(Latte.getApplicationContext())) {
                            JPushInterface.stopPush(Latte.getApplicationContext());
                        }
                    }
                });
        

    }

//    private void initStetho() {
//        Stetho.initialize(
//                Stetho.newInitializerBuilder(this)
//                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
//                .build());
//    }
}
