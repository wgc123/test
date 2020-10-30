package com.note.mytest.ui;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.hjq.toast.ToastUtils;
import com.note.mytest.R;
import com.note.mytest.bean.EventBean;
import com.note.testlibrary.tool.BaseActivity;
import com.note.testlibrary.tool.WGCLogUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author wgc
 * 使用
 */
public class EventBusActivity extends BaseActivity {


    @BindView(R.id.btn_st)
    Button btnSt;
    @BindView(R.id.btn_notifcation)
    Button btnNotifcation;
    @BindView(R.id.btn_update)
    Button btnUpdate;
    @BindView(R.id.tv_message)
    TextView tvMessage;

    @Override
    protected int initLayout() {
        return R.layout.activity_event_bus;
    }

    @Override
    protected void initData() {

    }

    /**
     * 注册eventbus
     */
    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    /**
     * 释放eventbus
     */
    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    /**
     * 接受传递消息
     * @param message
     */
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getMessage(EventBean message) {
        ToastUtils.show(message.message);
        WGCLogUtils.i(message.message);
        Log.i("event", message.message);
        tvMessage.setText(message.message);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_st, R.id.btn_notifcation, R.id.btn_update})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_st:
                EventBus.getDefault().post(EventBean.getInstance("我是在event界面传递过来的"));
                finish();
                break;
            case R.id.btn_notifcation:
                break;
            case R.id.btn_update:
                break;
        }
    }
}
