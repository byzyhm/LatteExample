package com.zlcp.lattecore.delegates;

//import android.os.Bundle;
//import android.support.annotation.NonNull;
//import android.support.annotation.Nullable;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import me.yokeyword.fragmentation_swipeback.SwipeBackFragment;

/**
 * 作者：zl_freedom
 * 时间：2019/7/21 10:57
 * 功能描述：
 */
public abstract class BaseDelegate extends SwipeBackFragment {
    public abstract Object setLayout();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = null;
        if (setLayout() instanceof Integer) {
            rootView = inflater.inflate((Integer) setLayout(), container, false);
        } else if (setLayout() instanceof View) {
            rootView = (View) setLayout();
        }
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
