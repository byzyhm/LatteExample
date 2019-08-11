package com.zlcp.latteec.main.personal.order;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.blankj.utilcode.util.ToastUtils;
import com.zlcp.lattecore.fragments.LatteFragment;
import com.zlcp.lattecore.util.callback.CallbackManager;
import com.zlcp.lattecore.util.callback.CallbackType;
import com.zlcp.lattecore.util.callback.IGlobalCallback;
import com.zlcp.latteec.R;
import com.zlcp.latteui.widget.AutoPhotoLayout;
import com.zlcp.latteui.widget.StarLayout;

import static com.blankj.utilcode.util.BarUtils.getStatusBarHeight;

public class OrderCommentFragment extends LatteFragment {

    private StarLayout mStarLayout = null;
    private AutoPhotoLayout mAutoPhotoLayout = null;

    void onClickSubmit() {
        ToastUtils.showShort("评分：" + mStarLayout.getStarCount());
    }

    @Override
    public Object setLayout() {
        return R.layout.fragment_order_comment;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View root) {
        Toolbar mToolbar = $(R.id.tb_order_comment);
        mStarLayout = $(R.id.custom_star_layout);
        mAutoPhotoLayout = $(R.id.custom_auto_photo_layout);
        mToolbar.setPadding(0, getStatusBarHeight(), 0, 0);
        $(R.id.top_tv_comment_commit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickSubmit();
            }
        });
        mAutoPhotoLayout.setFragment(this);
        CallbackManager.getInstance()
                .addCallback(CallbackType.ON_CROP, new IGlobalCallback<Uri>() {
                    @Override
                    public void executeCallback(@Nullable Uri args) {
                        mAutoPhotoLayout.onCropTarget(args);
                    }
                });

    }
}
