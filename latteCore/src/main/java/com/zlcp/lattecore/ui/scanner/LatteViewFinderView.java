package com.zlcp.lattecore.ui.scanner;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;

import me.dm7.barcodescanner.core.ViewFinderView;

/**
 * 作者：zl_freedom
 * 时间：2019/8/12 01:08
 * Note：
 */
public class LatteViewFinderView extends ViewFinderView {
    public LatteViewFinderView(Context context) {
        this(context, null);
    }

    public LatteViewFinderView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        mSquareViewFinder = true;   //正方形
        mBorderPaint.setColor(Color.GREEN);
        mLaserPaint.setColor(Color.GREEN);

    }
}
