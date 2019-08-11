package com.zlcp.latteec.main.personal.settings;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.Toolbar;

import com.alibaba.fastjson.JSON;
import com.zlcp.lattecore.fragments.LatteFragment;
import com.zlcp.lattecore.net.RestClient;
import com.zlcp.lattecore.net.callback.ISuccess;
import com.zlcp.latteec.R;

import static com.blankj.utilcode.util.BarUtils.getStatusBarHeight;

public class AboutFragment extends LatteFragment {
    @Override
    public Object setLayout() {
        return R.layout.fragment_about;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View root) {
        final AppCompatTextView textView = $(R.id.tv_info);
        final Toolbar mToolbar = $(R.id.tb_about);
        mToolbar.setPadding(0, getStatusBarHeight(), 0, 0);
        RestClient.builder()
                .url("about.php")
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        final String info = JSON.parseObject(response).getString("data");
                        textView.setText(info);
                    }
                })
                .build()
                .get();
    }
}
