package com.note.mytest.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.note.mytest.R;
import com.note.testlibrary.tool.BaseActivity;

import butterknife.BindView;

/**
 * @author wgc
 * 加载html
 */
public class HtmlActivity extends BaseActivity {

    @BindView(R.id.web_view)
    WebView webView;

    @Override
    protected int initLayout() {
        return R.layout.activity_html;
    }

    @Override
    protected void initData() {
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        try {
            webView.loadUrl("http://www.baidu.com");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //重写onKeyDown，当浏览网页，WebView可以后退时执行后退操作。
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
