package com.zlcp.lattecore.net.interceptors;

import com.zlcp.lattecore.file.FileUtil;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import androidx.annotation.RawRes;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * 作者：zl_freedom
 * 时间：2019/7/24 00:53
 * 功能描述：
 */
public class DebugInterceptor extends BaseInterceptor {
    private final String DEBUG_URL;
    private final int DEBUG_RAW_ID;

    public DebugInterceptor(String debugUrl, int debugRawId) {
        this.DEBUG_URL = debugUrl;
        this.DEBUG_RAW_ID = debugRawId;
    }

    private Response getResponse(Chain chain, String json) {
        return new Response.Builder()
                .code(200)
                .addHeader("Content-type", "application/json")
                .body(ResponseBody.create(MediaType.parse("application/json"), json))
                .message("OK")
                .request(chain.request())
                .protocol(Protocol.HTTP_1_1)
                .build();
    }

    private Response debugResponse(Chain chain, @RawRes int rawId) {
        final String json = FileUtil.getRawFile(rawId);
        return getResponse(chain, json);
    }


    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        final String url = chain.request().url().toString();
        /**
         * url:http://127.0.0.1/index
         * DEBUG_URL:index
         */
        if (url.contains(DEBUG_URL)) {
            return debugResponse(chain, DEBUG_RAW_ID);
        }
        return chain.proceed(chain.request());
    }
}
