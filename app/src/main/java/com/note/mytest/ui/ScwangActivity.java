package com.note.mytest.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.note.mytest.App;
import com.note.mytest.R;
import com.note.mytest.adapter.MyPersonAdapter;
import com.note.mytest.bean.PersonBean;
import com.note.testlibrary.tool.BaseActivity;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author wgc
 *  下拉刷新
 */
public class ScwangActivity extends BaseActivity {

    @BindView(R.id.smart)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.recycler)
    RecyclerView recyclerView;
    private MyPersonAdapter adapter;
    private List<PersonBean> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_scwang;
    }

    @Override
    protected void initData() {
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                smartRefreshLayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
            }
        });
        smartRefreshLayout.autoRefresh();
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                smartRefreshLayout.finishLoadMore(3000);
            }
        });
        setData();

        LinearLayoutManager manager = new LinearLayoutManager(App.getContext());
        recyclerView.setLayoutManager(manager);
        adapter = new MyPersonAdapter(list);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void setData(){
        PersonBean personBean = new PersonBean();
        personBean.setName("1");
        list.add(personBean);

        PersonBean personBean2 = new PersonBean();
        personBean.setName("2");
        list.add(personBean2);

        PersonBean personBean3 = new PersonBean();
        personBean.setName("3");
        list.add(personBean3);

    }




}
