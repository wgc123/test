package com.note.mytest.jiaozi;

import android.os.Bundle;

import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.note.jiaozivideoplayer.Jzvd;
import com.note.mytest.R;


/**
 * Created by Nathen on 2017/11/1.
 */

public class ActivityTinyWindowRecycleView extends AppCompatActivity {
    RecyclerView recyclerView;
    AdapterRecyclerViewTiny adapterVideoList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview_content);

        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapterVideoList = new AdapterRecyclerViewTiny(this);
        recyclerView.setAdapter(adapterVideoList);
        recyclerView.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener() {
            @Override
            public void onChildViewAttachedToWindow(View view) {//这个的本质是gotoThisJzvd，不管他是不是原来的容器
                //如果这个容器中jzvd的url是currentJzvd的url，那么直接回到这里，不用管其他的
                JzvdStdTinyWindow jzvd = view.findViewById(R.id.videoplayer);
                JzvdStdTinyWindow currentJzvd = (JzvdStdTinyWindow) Jzvd.CURRENT_JZVD;
                if (jzvd != null && currentJzvd != null &&
                        jzvd.jzDataSource.containsTheUrl(Jzvd.CURRENT_JZVD.jzDataSource.getCurrentUrl())
                        && Jzvd.CURRENT_JZVD.state == Jzvd.STATE_PLAYING) {
                    ViewGroup vp = (ViewGroup) jzvd.getParent();
                    vp.removeAllViews();
                    ((ViewGroup) currentJzvd.getParent()).removeView(currentJzvd);
                    FrameLayout.LayoutParams lp = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                    vp.addView(currentJzvd, lp);
                    currentJzvd.setScreenNormal();
                    Jzvd.CONTAINER_LIST.pop();
                }
            }

            @Override
            public void onChildViewDetachedFromWindow(View view) {
                JzvdStdTinyWindow jzvd = view.findViewById(R.id.videoplayer);
                if (jzvd != null && Jzvd.CURRENT_JZVD != null &&
                        jzvd.jzDataSource.containsTheUrl(Jzvd.CURRENT_JZVD.jzDataSource.getCurrentUrl())
                        && Jzvd.CURRENT_JZVD.screen != Jzvd.SCREEN_TINY) {
                    if (Jzvd.CURRENT_JZVD.state == Jzvd.STATE_PAUSE) {
                        Jzvd.releaseAllVideos();
                    } else {
                        ((JzvdStdTinyWindow) Jzvd.CURRENT_JZVD).gotoScreenTiny();
                    }
                }
            }
        });

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
