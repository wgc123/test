package com.note.testlibrary.tool;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : wgc
 * @e-mail : 786722510@qq.com
 * @date : 2020/9/29 16:04
 * @desc : 二维码申请相机和图库权限
 */
public class CheckPermissionUtils {
    private CheckPermissionUtils() {
    }

    /**
     * 需要申请的权限
     */
    private static String[] permissions = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA
    };

    /**
     * 检测权限
     * @param context
     * @return
     */
    public static String[] checkPermission(Context context){
        //存储未申请的权限
        List<String> data = new ArrayList<>();
        for (String permission : permissions) {
            int checkSelfPermission = ContextCompat.checkSelfPermission(context, permission);
            //未申请
            if(checkSelfPermission == PackageManager.PERMISSION_DENIED){
                data.add(permission);
            }
        }
        return data.toArray(new String[data.size()]);
    }
}
