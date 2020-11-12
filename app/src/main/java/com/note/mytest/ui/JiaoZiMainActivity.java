package com.note.mytest.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.note.jiaozivideoplayer.Jzvd;
import com.note.mytest.R;
import com.note.mytest.jiaozi.ActivityApi;
import com.note.mytest.jiaozi.ActivityDirectPlay;
import com.note.mytest.jiaozi.ActivityListView;
import com.note.mytest.jiaozi.ActivityTinyWindow;
import com.note.mytest.jiaozi.ActivityWebView;
import com.note.mytest.jiaozi.MyJzvdStd;
import com.note.testlibrary.tool.BaseActivity;

import butterknife.BindView;

/**
 * @author wgc
 */
public class JiaoZiMainActivity extends BaseActivity {
    @BindView(R.id.jz_video)
    MyJzvdStd myJzvdStd;

    @Override
    protected int initLayout() {
        return R.layout.activity_jiao_zi_main;
    }

    @Override
    protected void initData() {
        myJzvdStd.setUp("http://jzvd.nathen.cn/342a5f7ef6124a4a8faf00e738b8bee4/cf6d9db0bd4d41f59d09ea0a81e918fd-5287d2089db37e62345123a1be272f8b.mp4"
                , "饺子快长大");
        Glide.with(this).load("http://jzvd-pic.nathen.cn/jzvd-pic/1bb2ebbe-140d-4e2e-abd2-9e7e564f71ac.png").into(myJzvdStd.thumbImageView);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Jzvd.releaseAllVideos();
    }

    @Override
    public void onBackPressed() {
        if (Jzvd.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    public void clickApi(View view) {
        startActivity(new Intent(JiaoZiMainActivity.this, ActivityApi.class));
    }

    public void clickListView(View view) {
        startActivity(new Intent(JiaoZiMainActivity.this, ActivityListView.class));
    }

    public void clickTinyWindow(View view) {
        startActivity(new Intent(JiaoZiMainActivity.this, ActivityTinyWindow.class));
    }

    public void clickDirectPlay(View view) {
        startActivity(new Intent(JiaoZiMainActivity.this, ActivityDirectPlay.class));
    }

    public void clickWebView(View view) {
        startActivity(new Intent(JiaoZiMainActivity.this, ActivityWebView.class));
    }
}
