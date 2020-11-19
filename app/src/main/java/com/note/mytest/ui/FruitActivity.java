package com.note.mytest.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.AndroidException;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.note.mytest.R;
import com.note.testlibrary.tool.BaseActivity;
import com.youth.banner.util.LogUtils;

import butterknife.BindView;

/**
 * @author wgc
 */
public class FruitActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fruitImageView)
    ImageView fruitImageView;
    @BindView(R.id.fruitContentText)
    TextView fruitContentText;
    @BindView(R.id.collapsingToolbarLayout)
    CollapsingToolbarLayout collapsingToolbarLayout;

    String fruitName;
    int fruitImageId;

    @Override
    protected int initLayout() {
        return R.layout.activity_fruit;
    }

    @Override
    protected void initData() {
        fruitImageId = getIntent().getIntExtra("fruitImageId",0);
        fruitName = getIntent().getStringExtra("fruitName");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle(fruitName);
        collapsingToolbarLayout.setTitle(fruitContentText.getText().toString());
        Glide.with(this).load(fruitImageId).into(fruitImageView);
        fruitContentText.setText(fruitName);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
