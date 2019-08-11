package com.zlcp.lattecore.ui.camera;

import android.net.Uri;

/**
 * 作者：zl_freedom
 * 时间：2019/8/12 01:41
 * Note：
 */
public class CameraImageBean {

    private Uri mPath = null;
    private static final CameraImageBean INSTANCE = new CameraImageBean();

    public static CameraImageBean getInstance(){
        return INSTANCE;
    }

    public Uri getPath() {
        return mPath;
    }

    public void setPath(Uri mPath) {
        this.mPath = mPath;
    }
}
