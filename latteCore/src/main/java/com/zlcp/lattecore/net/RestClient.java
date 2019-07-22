package com.zlcp.lattecore.net;

import com.zlcp.lattecore.net.callback.IError;
import com.zlcp.lattecore.net.callback.IFailure;
import com.zlcp.lattecore.net.callback.IRequest;
import com.zlcp.lattecore.net.callback.ISuccess;

import java.util.Map;

import okhttp3.RequestBody;

/**
 * 作者：zl_freedom
 * 时间：2019/7/22 09:38
 * 功能描述：
 */
public class RestClient {

    private final String URL;
    private final Map<String, Object> PARAMS;
    private final IRequest REQUEST;
    private final ISuccess SUCCESS;
    private final IFailure FAILURE;
    private final IError ERROR;
    private final RequestBody BODY;


    public RestClient(String URL,
                      Map<String, Object> PARAMS,
                      IRequest REQUEST,
                      ISuccess SUCCESS,
                      IFailure FAILURE,
                      IError ERROR,
                      RequestBody BODY) {
        this.URL = URL;
        this.PARAMS = PARAMS;
        this.REQUEST = REQUEST;
        this.SUCCESS = SUCCESS;
        this.FAILURE = FAILURE;
        this.ERROR = ERROR;
        this.BODY = BODY;
    }

    public static RestClientBuilder builder(){
        return new RestClientBuilder();
    }
}
