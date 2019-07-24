package com.zlcp.latteexample;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.zlcp.lattecore.app.Latte;
import com.zlcp.lattecore.delegates.LatteDelegate;
import com.zlcp.lattecore.net.RestClient;
import com.zlcp.lattecore.net.callback.IError;
import com.zlcp.lattecore.net.callback.IFailure;
import com.zlcp.lattecore.net.callback.ISuccess;
import com.zlcp.lattecore.net.rx.RxRestClient;

import androidx.annotation.Nullable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

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
//        testRestClient();
        testRxRestClient();
    }

    private void testRxRestClient(){
        RxRestClient.builder()
                .url("http://127.0.0.1/index")
//                .loader(getContext())
                .build()
                .get()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        Toast.makeText(Latte.getApplicationContext(), s, Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void testRestClient() {
        RestClient.builder()
//                .url("http://news.baidu.com/")
                .url("http://127.0.0.1/index")
                .loader(getContext())
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Log.d("HAHAHA", response);
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
