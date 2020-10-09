package com.note.testlibrary.tool;

import android.app.Application;
import android.view.Gravity;

import com.hjq.toast.IToastStyle;
import com.hjq.toast.ToastUtils;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

/**
 * @author : wgc
 * @e-mail : 786722510@qq.com
 * @date : 2020/10/9 9:58
 * @desc : Application 初始化
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // 二维码初始化
        ZXingLibrary.initDisplayOpinion(this);

        // 初始化Toast并设置位置
        ToastUtils.init(this);
        ToastUtils.setGravity(Gravity.BOTTOM, 0, 0);
        // 自定义Toast样式
        ToastUtils.initStyle(new ToastBlackStyle(this));
    }
}
