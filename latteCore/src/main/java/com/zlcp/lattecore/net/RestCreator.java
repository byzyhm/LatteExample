package com.zlcp.lattecore.net;

import com.zlcp.lattecore.app.ConfigType;
import com.zlcp.lattecore.app.Latte;

/**
 * 作者：zl_freedom
 * 时间：2019/7/22 10:12
 * 功能描述：
 */
public class RestCreator {

    /**
     * 构建全局Retrofit客户端
     */
    private static final class RetrofitHolder{
        private static final String BASE_URL = (String) Latte.getConfigurations().get(ConfigType.API_HOST);
    }
}
