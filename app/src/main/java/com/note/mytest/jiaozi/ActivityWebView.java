package com.note.mytest.jiaozi;

import android.os.Bundle;

import android.view.MenuItem;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.AbsoluteLayout;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.note.jiaozivideoplayer.JZUtils;
import com.note.jiaozivideoplayer.Jzvd;
import com.note.jiaozivideoplayer.JzvdStd;
import com.note.mytest.R;


/**
 * Created by Nathen on 16/10/13.
 */

public class ActivityWebView extends AppCompatActivity {
    WebView mWebView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        mWebView = findViewById(R.id.webview);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.addJavascriptInterface(new JZCallBack(), "jzvd");
        mWebView.loadUrl("file:///android_asset/jzvd.html");
    }

    @Override
    public void onBackPressed() {
        if (Jzvd.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Jzvd.releaseAllVideos();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public class JZCallBack {

        @JavascriptInterface
        public void adViewJiaoZiVideoPlayer(final int width, final int height, final int top, final int left, final int index) {
            runOnUiThread(() -> {
                if (index == 0) {
                    JzvdStd jzvdStd = new JzvdStd(ActivityWebView.this);
                    jzvdStd.setUp(VideoConstant.videoUrlList[1], "饺子骑大马",
                            Jzvd.SCREEN_NORMAL);
                    Glide.with(ActivityWebView.this)
                            .load(VideoConstant.videoThumbList[1])
                            .into(jzvdStd.thumbImageView);
                    ViewGroup.LayoutParams ll = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    AbsoluteLayout.LayoutParams layoutParams = new AbsoluteLayout.LayoutParams(ll);
                    layoutParams.y = JZUtils.dip2px(ActivityWebView.this, top);
                    layoutParams.x = JZUtils.dip2px(ActivityWebView.this, left);
                    layoutParams.height = JZUtils.dip2px(ActivityWebView.this, height);
                    layoutParams.width = JZUtils.dip2px(ActivityWebView.this, width);

                    LinearLayout linearLayout = new LinearLayout(ActivityWebView.this);
                    linearLayout.addView(jzvdStd);
                    mWebView.addView(linearLayout, layoutParams);
                } else {
                    JzvdStd jzvdStd = new JzvdStd(ActivityWebView.this);
                    jzvdStd.setUp(VideoConstant.videoUrlList[2], "饺子失态了",
                            Jzvd.SCREEN_NORMAL);
                    Glide.with(ActivityWebView.this)
                            .load(VideoConstant.videoThumbList[2])
                            .into(jzvdStd.thumbImageView);
                    ViewGroup.LayoutParams ll = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    AbsoluteLayout.LayoutParams layoutParams = new AbsoluteLayout.LayoutParams(ll);
                    layoutParams.y = JZUtils.dip2px(ActivityWebView.this, top);
                    layoutParams.x = JZUtils.dip2px(ActivityWebView.this, left);
                    layoutParams.height = JZUtils.dip2px(ActivityWebView.this, height);
                    layoutParams.width = JZUtils.dip2px(ActivityWebView.this, width);

                    LinearLayout linearLayout = new LinearLayout(ActivityWebView.this);
                    linearLayout.addView(jzvdStd);
                    mWebView.addView(linearLayout, layoutParams);
                }

            });

        }
    }
}
