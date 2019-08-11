package com.zlcp.lattecore.ui.scanner;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.zlcp.lattecore.fragments.LatteFragment;
import com.zlcp.lattecore.util.callback.CallbackManager;
import com.zlcp.lattecore.util.callback.CallbackType;
import com.zlcp.lattecore.util.callback.IGlobalCallback;

import me.dm7.barcodescanner.zbar.Result;
import me.dm7.barcodescanner.zbar.ZBarScannerView;
import me.yokeyword.fragmentation.anim.FragmentAnimator;

/**
 * 作者：zl_freedom
 * 时间：2019/8/12 01:04
 * Note：
 */
public class ScannerFragment extends LatteFragment implements ZBarScannerView.ResultHandler {

    private ScanView mScanView = null;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mScanView == null) {
            mScanView = new ScanView(getContext());
        }
        mScanView.setAutoFocus(true);
        mScanView.setResultHandler(this);
    }


    @Override
    public Object setLayout() {
        return mScanView;
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, @NonNull View root) {

    }

    @Override
    public void onResume() {
        super.onResume();
        if (mScanView != null) {
            mScanView.startCamera();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mScanView != null) {
            mScanView.stopCameraPreview();
            mScanView.stopCamera();
        }
    }


    @Override
    public void handleResult(Result result) {
        @SuppressWarnings("unchecked") final IGlobalCallback<String> callback = CallbackManager
                .getInstance()
                .getCallback(CallbackType.ON_SCAN);
        if (callback != null) {
            callback.executeCallback(result.getContents());
        }
        getSupportDelegate().pop();
    }

    @Override
    public FragmentAnimator onCreateFragmentAnimator() {
        return null;
    }
}
