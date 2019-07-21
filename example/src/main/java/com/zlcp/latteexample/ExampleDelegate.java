package com.zlcp.latteexample;

import android.os.Bundle;
import android.view.View;

import com.zlcp.lattecore.delegates.LatteDelegate;

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

    }
}
