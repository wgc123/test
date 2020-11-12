package com.note.mytest.ui;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.hjq.toast.ToastUtils;
import com.note.mytest.R;
import com.note.mytest.tool.RedPacket;
import com.note.testlibrary.tool.BaseActivity;
import com.note.testlibrary.tool.WGCLogUtils;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MontyActivity extends BaseActivity {

    @BindView(R.id.input_monty)
    EditText inputMonty;
    @BindView(R.id.monty)
    Button monty;

    @Override
    protected int initLayout() {
        return R.layout.activity_monty;
    }

    @Override
    protected void initData() {

    }
    @OnClick(R.id.monty)
    public void onViewClicked() {
        RedPacket redPacket = new RedPacket();
        int money =Integer.parseInt(inputMonty.getText().toString()) ;
        List<Integer> redPackets = redPacket.splitRedPacket(money, 100);
        int sum = 0;
        for (Integer red : redPackets) {
            sum += red;
        }
        WGCLogUtils.i("" + sum);
    }
}
