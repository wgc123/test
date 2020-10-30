package com.note.mytest.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.note.testlibrary.tool.WGCLogUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @author : wgc
 * @e-mail : 786722510@qq.com
 * @date : 2020/10/10 8:57
 * @desc : fragment
 */
public abstract class BaseFragment extends Fragment {

    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(initLayout(), container, false);
        unbinder = ButterKnife.bind(this,view);
        initData(view);
        return view;
    }

    /**
     * 加载布局文件
     * @param view
     * @return
     */
    public abstract int initLayout();

    public abstract void initData(View view);

    @Override
    public void onStart() {
        super.onStart();
        WGCLogUtils.d("BaseFragment onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        WGCLogUtils.d("BaseFragment onResume");
    }

    @Override
    public void onStop() {
        super.onStop();
        unbinder.unbind();
        WGCLogUtils.d("BaseFragment onStop");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        WGCLogUtils.d("BaseFragment onDestroy");
    }
}
