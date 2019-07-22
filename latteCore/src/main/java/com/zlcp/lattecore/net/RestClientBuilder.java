package com.zlcp.lattecore.net;

import com.zlcp.lattecore.net.callback.IError;
import com.zlcp.lattecore.net.callback.IFailure;
import com.zlcp.lattecore.net.callback.IRequest;
import com.zlcp.lattecore.net.callback.ISuccess;

import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.RequestBody;

/**
 * 作者：zl_freedom
 * 时间：2019/7/22 11:18
 * 功能描述：
 */
public class RestClientBuilder {
    private String mUrl;
    private Map<String, Object> mParams;
//    private static final Map<String, Object> PARAMS = RestCreator.PARAMS;
    private IRequest mIRequest;
    private ISuccess mISuccess;
    private IFailure mIFailure;
    private IError mIError;
    private RequestBody mBody;

    RestClientBuilder() {

    }

    public final RestClientBuilder url(String url) {
        this.mUrl = url;
        return this;
    }

    public final RestClientBuilder params(Map<String, Object> params) {
        this.mParams = params;
        return this;
    }

    public final RestClientBuilder params(String key, Object value) {
        if (mParams == null) {
            mParams = new WeakHashMap<>();
        }
        this.mParams.put(key, value);
        return this;
    }

    public final RestClientBuilder raw(String raw) {
        this.mBody = RequestBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);
        return this;
    }

    public final RestClientBuilder sucess(IRequest iRequest) {
        this.mIRequest = iRequest;
        return this;
    }

    public final RestClientBuilder sucess(ISuccess iSuccess) {
        this.mISuccess = iSuccess;
        return this;
    }

    public final RestClientBuilder failure(IFailure iFailure) {
        this.mIFailure = iFailure;
        return this;
    }

    public final RestClientBuilder error(IError iError) {
        this.mIError = iError;
        return this;
    }

    private Map<String, Object> checkParams() {
        if (mParams == null) {
            mParams = new WeakHashMap<>();
        }
        return mParams;
    }

    public final RestClient build() {
        return new RestClient(mUrl, mParams, mIRequest, mISuccess, mIFailure, mIError, mBody);
    }
}
