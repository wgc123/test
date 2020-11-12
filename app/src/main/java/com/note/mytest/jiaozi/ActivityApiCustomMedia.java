package com.note.mytest.jiaozi;

import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.note.jiaozivideoplayer.JZDataSource;
import com.note.jiaozivideoplayer.JZMediaSystem;
import com.note.jiaozivideoplayer.Jzvd;
import com.note.jiaozivideoplayer.JzvdStd;
import com.note.mytest.R;

import java.io.IOException;



/**
 * Created by Nathen on 2017/11/23.
 */

public class ActivityApiCustomMedia extends AppCompatActivity {
    JzvdStd jzvdStd;
    Handler handler = new Handler();//这里其实并不需要handler，为了防止播放中切换播放器引擎导致的崩溃，实际使用时一般不会遇到，可以随时调用JZVideoPlayer.setMediaInterface();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api_custom_mediaplayer);

        jzvdStd = findViewById(R.id.videoplayer);

        JZDataSource jzDataSource = null;
        try {
            jzDataSource = new JZDataSource(getAssets().openFd("local_video.mp4"));
            jzDataSource.title = "饺子快长大";
        } catch (IOException e) {
            e.printStackTrace();
        }
        jzvdStd.setUp(jzDataSource, JzvdStd.SCREEN_NORMAL, JZMediaSystemAssertFolder.class);


//        jzvdStd.setUp("http://jzvd.nathen.cn/342a5f7ef6124a4a8faf00e738b8bee4/cf6d9db0bd4d41f59d09ea0a81e918fd-5287d2089db37e62345123a1be272f8b.mp4"
//                , "饺子快长大", JzvdStd.SCREEN_NORMAL, new JZMediaIjk(jzvdStd));

        Glide.with(this)
                .load("http://jzvd-pic.nathen.cn/jzvd-pic/1bb2ebbe-140d-4e2e-abd2-9e7e564f71ac.png")
                .into(jzvdStd.thumbImageView);

    }


    public void clickChangeToIjkplayer(View view) {
        Jzvd.releaseAllVideos();
        jzvdStd.setUp("http://jzvd.nathen.cn/342a5f7ef6124a4a8faf00e738b8bee4/cf6d9db0bd4d41f59d09ea0a81e918fd-5287d2089db37e62345123a1be272f8b.mp4"
                , "饺子快长大", JzvdStd.SCREEN_NORMAL, JZMediaIjk.class);
        jzvdStd.startVideo();
        Toast.makeText(this, "Change to Ijkplayer", Toast.LENGTH_SHORT).show();
    }

    public void clickChangeToSystem(View view) {
        Jzvd.releaseAllVideos();
        jzvdStd.setUp("http://jzvd.nathen.cn/342a5f7ef6124a4a8faf00e738b8bee4/cf6d9db0bd4d41f59d09ea0a81e918fd-5287d2089db37e62345123a1be272f8b.mp4"
                , "饺子快长大", JzvdStd.SCREEN_NORMAL, JZMediaSystem.class);
        jzvdStd.startVideo();
        Toast.makeText(this, "Change to MediaPlayer", Toast.LENGTH_SHORT).show();
    }

    public void clickChangeToExo(View view) {
        Jzvd.releaseAllVideos();
        jzvdStd.setUp("http://jzvd.nathen.cn/342a5f7ef6124a4a8faf00e738b8bee4/cf6d9db0bd4d41f59d09ea0a81e918fd-5287d2089db37e62345123a1be272f8b.mp4"
                , "饺子快长大", JzvdStd.SCREEN_NORMAL, JZMediaExo.class);
        jzvdStd.startVideo();
        Toast.makeText(this, "Change to ExoPlayer", Toast.LENGTH_SHORT).show();
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

}
