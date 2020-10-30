package com.note.mytest.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.note.mytest.R;
import com.note.testlibrary.tool.BaseActivity;

import butterknife.BindView;

/**
 * @author wgc
 */
public class NextActivity extends BaseActivity {

    private int[] images = new int[]{R.drawable.pic1, R.drawable.pic3, R.drawable.pic4, R.drawable.pic7};
    private int index;
    @BindView(R.id.image)
    ImageView imageView;
    @BindView(R.id.next)
    TextView textView;

    @Override
    protected int initLayout() {
        return R.layout.activity_next;
    }

    @Override
    protected void initData() {
        this.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
        index = getIntent().getIntExtra("index",0);
        imageView.setImageResource(images[index]);
        textView.setText(textView.getText().toString()+index);

        final Intent intent = new Intent(this,NextActivity.class);
        intent.putExtra("index",++index);
        if(index==images.length){
            textView.setText("FINISH ACTIVITY");
        }
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(index==images.length){
                    finish();
                    NextActivity.this.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_right);
                }
            }
        });
    }



}
