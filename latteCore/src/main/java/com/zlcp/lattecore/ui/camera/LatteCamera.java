package com.zlcp.lattecore.ui.camera;

import android.net.Uri;

import com.zlcp.lattecore.fragments.PermissionCheckFragment;
import com.zlcp.lattecore.util.file.FileUtil;

/**
 * 作者：zl_freedom
 * 时间：2019/8/12 01:32
 * Note：照相机调用类
 */
public class LatteCamera {
    public static Uri createCropFile() {
        //裁剪图片的地址
        return Uri.parse(
                FileUtil.createFile("crop_image",
                        FileUtil.getFileNameByTime("IMG", "jpg")).getPath());
    }

    public static void start(PermissionCheckFragment delegate) {
        new CameraHandler(delegate).beginCameraDialog();
    }
}
