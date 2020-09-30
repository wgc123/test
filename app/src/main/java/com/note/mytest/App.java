package com.note.mytest;

import android.app.Application;

import com.uuzuche.lib_zxing.activity.ZXingLibrary;

/**
 * @author : wgc
 * @e-mail : 786722510@qq.com
 * @date : 2020/9/29 15:55
 * @desc :
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        ZXingLibrary.initDisplayOpinion(this);
    }
}
