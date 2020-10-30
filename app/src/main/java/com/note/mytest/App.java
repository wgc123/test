package com.note.mytest;

import android.app.Application;
import android.content.Context;
import android.view.Gravity;

import com.hjq.toast.ToastUtils;
import com.note.mytest.okhttp.HttpHelper;
import com.note.mytest.okhttp.OkHttpLoader;
import com.note.testlibrary.tool.ToastBlackStyle;
import com.sunchen.netbus.NetStatusBus;
import com.uuzuche.lib_zxing.activity.ZXingLibrary;

/**
 * @author : wgc
 * @e-mail : 786722510@qq.com
 * @date : 2020/9/29 15:55
 * @desc :
 */
public class App extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
        ZXingLibrary.initDisplayOpinion(this);

        // 初始化Toast并设置位置
        ToastUtils.init(this);
        ToastUtils.setGravity(Gravity.BOTTOM, 0, 0);
        // 自定义Toast样式
        ToastUtils.initStyle(new ToastBlackStyle(this));

        //初始化网络监听
        NetStatusBus.getInstance().init(this);

        // okhttp 网络请求初始化
        HttpHelper.init(new OkHttpLoader());

    }

    public static Context getContext() {
        return context;
    }
}
