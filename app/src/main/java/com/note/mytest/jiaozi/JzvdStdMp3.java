package com.note.mytest.jiaozi;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.note.jiaozivideoplayer.JzvdStd;
import com.note.mytest.R;


/**
 * 这个本质上就是播放的时候不隐藏缩略图
 */
public class JzvdStdMp3 extends JzvdStd {

    public JzvdStdMp3(Context context) {
        super(context);
    }

    public JzvdStdMp3(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public int getLayoutId() {
        return R.layout.jz_layout_standard_mp3;
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.thumb &&
                (state == STATE_PLAYING ||
                        state == STATE_PAUSE)) {
            onClickUiToggle();
        } else if (v.getId() == R.id.fullscreen) {

        } else {
            super.onClick(v);
        }
    }

    //changeUiTo 真能能修改ui的方法
    @Override
    public void changeUiToNormal() {
        super.changeUiToNormal();
    }

    @Override
    public void changeUiToPreparing() {
        super.changeUiToPreparing();
    }

    @Override
    public void changeUiToPlayingShow() {
        super.changeUiToPlayingShow();
        thumbImageView.setVisibility(View.VISIBLE);

    }

    @Override
    public void changeUiToPlayingClear() {
        super.changeUiToPlayingClear();
        thumbImageView.setVisibility(View.VISIBLE);
    }

    @Override
    public void changeUiToPauseShow() {
        super.changeUiToPauseShow();
        thumbImageView.setVisibility(View.VISIBLE);
    }

    @Override
    public void changeUiToPauseClear() {
        super.changeUiToPauseClear();
        thumbImageView.setVisibility(View.VISIBLE);
    }

    @Override
    public void changeUiToComplete() {
        super.changeUiToComplete();
    }

    @Override
    public void changeUiToError() {
        super.changeUiToError();
    }
}
