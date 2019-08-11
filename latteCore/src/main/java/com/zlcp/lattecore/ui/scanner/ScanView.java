package com.zlcp.lattecore.ui.scanner;

import android.content.Context;
import android.util.AttributeSet;

import me.dm7.barcodescanner.core.IViewFinder;
import me.dm7.barcodescanner.zbar.ZBarScannerView;

/**
 * 作者：zl_freedom
 * 时间：2019/8/12 01:07
 * Note：
 */
public class ScanView extends ZBarScannerView {
    public ScanView(Context context) {
        super(context, null);
    }

    public ScanView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    @Override
    protected IViewFinder createViewFinderView(Context context) {
        return new LatteViewFinderView(context);
    }
}
