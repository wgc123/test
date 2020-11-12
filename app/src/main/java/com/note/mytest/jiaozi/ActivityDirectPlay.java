package com.note.mytest.jiaozi;

import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.note.jiaozivideoplayer.Jzvd;
import com.note.jiaozivideoplayer.JzvdStd;
import com.note.mytest.R;


/**
 * Created by Nathen on 16/7/31.
 */
public class ActivityDirectPlay extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);
        setContentView(R.layout.activity_directly_play);
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

    public void clickFullScreen(View view) {
        JzvdStd.startFullscreenDirectly(this, JzvdStd.class, VideoConstant.videoUrlList[6], "饺子辛苦了");
    }

    public void clickTinyWindow(View view) {
        Toast.makeText(ActivityDirectPlay.this, "Comming Soon", Toast.LENGTH_SHORT).show();
    }

}
