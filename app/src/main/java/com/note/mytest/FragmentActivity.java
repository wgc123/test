package com.note.mytest;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.hjq.toast.ToastUtils;
import com.note.mytest.fragment.FXianFragment;
import com.note.mytest.fragment.TXLUFragment;
import com.note.mytest.fragment.WXinFragment;
import com.note.mytest.fragment.WoFragment;
import com.note.testlibrary.tool.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindArray;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * @author : wgc
 * @e-mail : 786722510@qq.com
 * @date : 2020/9/30 10:21
 * @desc :仿微信界面
 * */
public class FragmentActivity extends BaseActivity {

    @BindView(R.id.viewpager)
    ViewPager mViewPager;
    @BindArray(R.array.tab_array)
    String[] mTabTitles;
    @BindView(R.id.tab_weixin)
    TabView mTabWeixin;
    @BindView(R.id.tab_contact)
    TabView mTabContact;
    @BindView(R.id.tab_find)
    TabView mTabFind;
    @BindView(R.id.tab_profile)
    TabView mTabProfile;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private List<TabView> mTabViews = new ArrayList<>();
    private static final int INDEX_WEIXIN = 0;
    private static final int INDEX_CONTACT = 1;
    private static final int INDEX_FIND = 2;
    private static final int INDEX_PROFILE = 3;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_fragment;
    }

    @Override
    protected void initData() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("");
        mTabViews.add(mTabWeixin);
        mTabViews.add(mTabContact);
        mTabViews.add(mTabFind);
        mTabViews.add(mTabProfile);
        mViewPager.setOffscreenPageLimit(mTabTitles.length - 1);
        mViewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        mViewPager.addOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            /**
             * @param position 滑动的时候，position总是代表左边的View， position+1总是代表右边的View
             * @param positionOffset 左边View位移的比例
             * @param positionOffsetPixels 左边View位移的像素
             */
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                // 左边View进行动画
                mTabViews.get(position).setXPercentage(1 - positionOffset);
                // 如果positionOffset非0，那么就代表右边的View可见，也就说明需要对右边的View进行动画
                if (positionOffset > 0) {
                    mTabViews.get(position + 1).setXPercentage(positionOffset);
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_top_more, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_search:
                ToastUtils.show("搜索");
                break;
            case R.id.menu_group_chat:
                ToastUtils.show("发起群聊");
                break;
            case R.id.menu_add_friend:
                ToastUtils.show("添加朋友");
                break;
            case R.id.menu_scan:
                ToastUtils.show("扫一扫");
                break;
            case R.id.menu_receive_payment:
                ToastUtils.show("收付款");
                break;
            case R.id.menu_help:
                ToastUtils.show("帮助与反馈");
                break;
            default:
                break;
        }
        return true;
    }

    private void updateCurrentTab(int index) {
        for (int i = 0; i < mTabViews.size(); i++) {
            if (index == i) {
                mTabViews.get(i).setXPercentage(1);
            } else {
                mTabViews.get(i).setXPercentage(0);
            }
        }
    }

    @OnClick({R.id.tab_weixin, R.id.tab_contact, R.id.tab_find, R.id.tab_profile})
    public void onClickTab(View v) {
        switch (v.getId()) {
            case R.id.tab_weixin:
                mViewPager.setCurrentItem(INDEX_WEIXIN, false);
                updateCurrentTab(INDEX_WEIXIN);
                break;
            case R.id.tab_contact:
                mViewPager.setCurrentItem(INDEX_CONTACT, false);
                updateCurrentTab(INDEX_CONTACT);
                break;

            case R.id.tab_find:
                mViewPager.setCurrentItem(INDEX_FIND, false);
                updateCurrentTab(INDEX_FIND);
                break;

            case R.id.tab_profile:
                mViewPager.setCurrentItem(INDEX_PROFILE, false);
                updateCurrentTab(INDEX_PROFILE);
                break;
        }
    }

    /**
     * FragmentPagerAdapter
     */
    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return getTabFragment(i, mTabTitles[i]);
        }

        @Override
        public int getCount() {
            return mTabTitles.length;
        }
    }

    /**
     *
     * @param index 导航下标
     * @param title 导航
     * @return
     */
    private Fragment getTabFragment(int index, String title) {
        Fragment fragment = null;
        switch (index) {
            case INDEX_WEIXIN:
                fragment = new WXinFragment();
                break;

            case INDEX_CONTACT:
                fragment = new TXLUFragment();
                break;

            case INDEX_FIND:
                fragment = new FXianFragment();
                break;

            case INDEX_PROFILE:
                fragment = new WoFragment();
                break;
            default:
                break;
        }
        return fragment;
    }
}
