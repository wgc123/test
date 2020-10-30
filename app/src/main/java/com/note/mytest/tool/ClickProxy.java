package com.note.mytest.tool;

import android.view.View;

import com.hjq.toast.ToastUtils;
import com.note.testlibrary.tool.WGCLogUtils;

/**
 * @author : wgc
 * @e-mail : 786722510@qq.com
 * @date : 2020/10/9 11:02
 * @desc : 解决重复点击
 */
public class ClickProxy implements View.OnClickListener {

    private View.OnClickListener origin;
    private long lastclick = 0;
    private long timems = 1000; //ms
    private IAgain mIAgain;

    public ClickProxy(View.OnClickListener origin, long timems, IAgain again) {
        this.origin = origin;
        this.mIAgain = again;
        this.timems = timems;
    }

    public ClickProxy(View.OnClickListener origin) {
        this.origin = origin;
    }

    @Override
    public void onClick(View v) {
        if (System.currentTimeMillis() - lastclick >= timems) {
            origin.onClick(v);
            lastclick = System.currentTimeMillis();
        } else {
            if (mIAgain != null) {
                ToastUtils.show("重复点击");
                mIAgain.onAgain();
            }
        }
    }

    /**
     * 再次点击
     */
    public interface IAgain {
        void onAgain();//重复点击
    }


    /**
     * 使用：
     *  findViewById(R.id.btn_drawable).setOnClickListener(new ClickProxy(new View.OnClickListener() {
     *             @Override
     *             public void onClick(View v) {
     *             }
     *         }));
     */
}
