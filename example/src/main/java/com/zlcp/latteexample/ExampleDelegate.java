package com.zlcp.latteexample;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.zlcp.lattecore.delegates.LatteDelegate;
import com.zlcp.lattecore.net.RestClient;
import com.zlcp.lattecore.net.callback.IError;
import com.zlcp.lattecore.net.callback.IFailure;
import com.zlcp.lattecore.net.callback.ISuccess;

import androidx.annotation.Nullable;

/**
 * 作者：zl_freedom
 * 时间：2019/7/21 22:03
 * 功能描述：
 */
public class ExampleDelegate extends LatteDelegate {
    @Override
    public Object setLayout() {
        return R.layout.delegate_example;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {
        testRestClient();
    }

    private void testRestClient() {
        RestClient.builder()
                .url("http://news.baidu.com/")
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Toast.makeText(getContext(), response, Toast.LENGTH_LONG).show();
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {

                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String msg) {

                    }
                })
                .build()
                .get();
    }
}
