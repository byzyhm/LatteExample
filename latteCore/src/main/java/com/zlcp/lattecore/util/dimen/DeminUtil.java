package com.zlcp.lattecore.util.dimen;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.zlcp.lattecore.app.Latte;

/**
 * 作者：zl_freedom
 * 时间：2019/7/23 15:54
 * 功能描述：
 */
public class DeminUtil {
    public static int getScreenWidth(){
        final Resources resources = Latte.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }

    public static int getScreenHeight(){
        final Resources resources = Latte.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }
}
