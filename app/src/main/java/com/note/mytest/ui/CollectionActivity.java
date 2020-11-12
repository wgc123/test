package com.note.mytest.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.note.mytest.R;
import com.note.testlibrary.tool.BaseActivity;

import butterknife.BindView;

/**
 * list/set/map集合
 * @author wgc Collection使用
 */
public class CollectionActivity extends BaseActivity {

    @BindView(R.id.tv_collection)
    TextView tv_collection;
    @Override
    protected int initLayout() {
        return R.layout.activity_collection;
    }

    @Override
    protected void initData() {

    }
}
