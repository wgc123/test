package com.note.mytest.jiaozi;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.note.jiaozivideoplayer.Jzvd;
import com.note.jiaozivideoplayer.JzvdStd;
import com.note.mytest.R;


/**
 * Created by Nathen on 2017/11/2.
 */

public class ActivityApiRotationVideoSize extends AppCompatActivity {

    JzvdStd myJzvdStd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api_rotation_videosize);

        myJzvdStd = findViewById(R.id.jz_video);
        myJzvdStd.setUp(VideoConstant.videoUrls[0][7], VideoConstant.videoTitles[0][7]
                , JzvdStd.SCREEN_NORMAL);
        Glide.with(this)
                .load(VideoConstant.videoThumbs[0][7])
                .into(myJzvdStd.thumbImageView);
        // The Point IS 或者这样写也可以
//        myJzvdStd.videoRotation = 180;
    }


    @Override
    protected void onPause() {
        super.onPause();
        Jzvd.releaseAllVideos();
        Jzvd.setVideoImageDisplayType(Jzvd.VIDEO_IMAGE_DISPLAY_TYPE_ADAPTER);
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

    public void clickRotationTo0(View view) {
        Jzvd.setTextureViewRotation(0);
    }

    public void clickRotationTo90(View view) {
        Jzvd.setTextureViewRotation(90);
    }

    public void clickRotationTo180(View view) {
        Jzvd.setTextureViewRotation(180);
    }

    public void clickRotationTo270(View view) {
        Jzvd.setTextureViewRotation(270);
    }

    public void clickVideoImageDiaplayAdapter(View view) {
        Jzvd.setVideoImageDisplayType(Jzvd.VIDEO_IMAGE_DISPLAY_TYPE_ADAPTER);
    }

    public void clickVideoImageDisplayFillParent(View view) {
        Jzvd.setVideoImageDisplayType(Jzvd.VIDEO_IMAGE_DISPLAY_TYPE_FILL_PARENT);
    }

    public void clickVideoImageDisplayFillCrop(View view) {
        Jzvd.setVideoImageDisplayType(Jzvd.VIDEO_IMAGE_DISPLAY_TYPE_FILL_SCROP);
    }

    public void clickVideoImageDiaplayOriginal(View view) {
        Jzvd.setVideoImageDisplayType(Jzvd.VIDEO_IMAGE_DISPLAY_TYPE_ORIGINAL);
    }
}
