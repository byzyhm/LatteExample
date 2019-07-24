package com.zlcp.lattecore.net.interceptors;

import com.zlcp.lattecore.util.LattePreference;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 作者：zl_freedom
 * 时间：2019/7/24 14:13
 * 功能描述：
 */
public class AddCookieInterceptor implements Interceptor {
    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {

        final Request.Builder builder = chain.request().newBuilder();
        /*普通方式*/
        String cookie = LattePreference.getCustomAppProfile("cookie");
        builder.addHeader("Cookie", cookie);

        /*RxJava方式*/
//        Observable.just(LattePreference.getCustomAppProfile("cookie"))
//                .subscribe(new Consumer<String>() {
//                    @Override
//                    public void accept(String cookie) throws Exception {
//                        builder.addHeader("Cookie",cookie);
//                    }
//                });

        return chain.proceed(builder.build());
    }
}
