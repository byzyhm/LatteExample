package com.zlcp.lattecore.net;

import com.zlcp.lattecore.app.ConfigKeys;
import com.zlcp.lattecore.app.Latte;

import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * 作者：zl_freedom
 * 时间：2019/7/22 10:12
 * 功能描述：
 */
public class RestCreator {


    private static final class ParamsHolder {
        public static final WeakHashMap<String, Object> PARAMS = new WeakHashMap<>();
    }

    public static WeakHashMap<String, Object> getParams() {
        return ParamsHolder.PARAMS;
    }

    public static RestService getRestService() {
        return RestServiceHolder.REST_SERVICE;
    }

    /**
     * 构建全局Retrofit客户端
     */
//    private static final class RetrofitHolder {
//
//        private static final String BASE_URL = (String) Latte.getConfigurations().get(ConfigKeys.API_HOST);
//        private static final retrofit2.Retrofit RETROFIT_CLIENT = new retrofit2.Retrofit.Builder()
//                .baseUrl(BASE_URL)
////                .baseUrl("http://news.baidu.com/")
//                .client(OkHttpHolder.OK_HTTP_CLIENT)
//                .addConverterFactory(ScalarsConverterFactory.create())
//                .build();
//
//    }
    /**
     * 构建全局Retrofit客户端
     */
    private static final class RetrofitHolder {
        private static final String BASE_URL = Latte.getConfiguration(ConfigKeys.API_HOST.name());
        private static final Retrofit RETROFIT_CLIENT = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(OKHttpHolder.OK_HTTP_CLIENT)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
    }

    private static final class OKHttpHolder {
        private static final int TIME_OUT = 60;
        private static final OkHttpClient OK_HTTP_CLIENT = new OkHttpClient.Builder()
                .connectTimeout(TIME_OUT, TimeUnit.SECONDS)
                .build();
    }

    private static final class RestServiceHolder {
        private static final RestService REST_SERVICE =
                RetrofitHolder.RETROFIT_CLIENT.create(RestService.class);
    }
}
