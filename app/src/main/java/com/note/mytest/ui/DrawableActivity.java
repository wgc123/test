package com.note.mytest.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

import com.note.mytest.R;
import com.note.mytest.tool.CircleImageDrawable;
import com.note.mytest.tool.RoundImageDrawable;
import com.note.testlibrary.tool.BaseActivity;

import butterknife.BindView;

/**
 * @author wgc 实现圆角、圆形
 */
public class DrawableActivity extends BaseActivity {


    @BindView(R.id.image_round)
    ImageView imageRound;

    @BindView(R.id.image_circle)
    ImageView imageCircle;


    @Override
    protected int initLayout() {
        return R.layout.activity_drawable;
    }

    @Override
    protected void initData() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.lss);
        imageRound.setImageDrawable(new RoundImageDrawable(bitmap));
        imageCircle.setImageDrawable(new CircleImageDrawable(bitmap));
    }
}
