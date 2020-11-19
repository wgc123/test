package com.note.mytest.jiaozi;

import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.note.jiaozivideoplayer.Jzvd;
import com.note.jiaozivideoplayer.JzvdStd;
import com.note.mytest.R;


/**
 * Created by Nathen on 2017/10/31.
 */

public class ActivityTinyWindow extends AppCompatActivity {

    JzvdStdTinyWindow jzvdStdTinyWindow;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tiny_window);

        jzvdStdTinyWindow = findViewById(R.id.jz_video);
        jzvdStdTinyWindow.setUp("http://jzvd.nathen.cn/342a5f7ef6124a4a8faf00e738b8bee4/cf6d9db0bd4d41f59d09ea0a81e918fd-5287d2089db37e62345123a1be272f8b.mp4", "饺子快长大"
                , JzvdStd.SCREEN_NORMAL);
        Glide.with(this)
                .load("http://jzvd-pic.nathen.cn/jzvd-pic/1bb2ebbe-140d-4e2e-abd2-9e7e564f71ac.png")
                .into(jzvdStdTinyWindow.thumbImageView);

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    public void clickTinyWindow(View view) {
        jzvdStdTinyWindow.gotoScreenTiny();
    }

    public void clickAutoTinyListViewRecyclerView(View view) {
        Toast.makeText(this, "comming soon", Toast.LENGTH_SHORT).show();
//        startActivity(new Intent(this, ActivityTinyWindowRecycleView.class));
    }

    public void clickAutoTinyListViewRecyclerViewMultiHolder(View view) {
        Toast.makeText(this, "comming soon", Toast.LENGTH_SHORT).show();
//        startActivity(new Intent(this, ActivityTinyWindowRecycleViewMultiHolder.class));
    }
}