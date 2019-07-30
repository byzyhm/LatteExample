package com.zlcp.latteui.launcher;

import android.content.Context;
import android.view.View;

import com.bigkoo.convenientbanner.holder.Holder;

import androidx.appcompat.widget.AppCompatImageView;

/**
 * 作者：zl_freedom
 * 时间：2019/7/24 19:16
 * 功能描述：
 */
public class LauncherHolder implements Holder<Integer> {

    private AppCompatImageView mImageView = null;


    @Override
    public View createView(Context context) {
        return mImageView = new AppCompatImageView(context);
    }

    @Override
    public void UpdateUI(Context context, int position, Integer data) {
        mImageView.setBackgroundResource(data);
    }
}
