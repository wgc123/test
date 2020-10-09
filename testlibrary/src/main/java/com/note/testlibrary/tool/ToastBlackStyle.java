package com.note.testlibrary.tool;

import android.content.Context;

import com.hjq.toast.style.BaseToastStyle;

/**
 * @author : wgc
 * @e-mail : 786722510@qq.com
 * @date : 2020/10/9 9:56
 * @desc : 吐司样式
 */
public class ToastBlackStyle extends BaseToastStyle {

    public ToastBlackStyle(Context context) {
        super(context);
    }

    @Override
    public int getCornerRadius() {
        return dp2px(8);
    }

    @Override
    public int getBackgroundColor() {
        return 0X88000000;
    }

    @Override
    public int getTextColor() {
        return 0XEEFFFFFF;
    }

    @Override
    public float getTextSize() {
        return sp2px(14);
    }

    @Override
    public int getPaddingStart() {
        return dp2px(24);
    }

    @Override
    public int getPaddingTop() {
        return dp2px(16);
    }
}
