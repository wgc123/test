package com.note.mytest.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.note.mytest.R;
import com.note.mytest.adapter.FruitAdapter;
import com.note.mytest.bean.Fruit;
import com.note.testlibrary.tool.BaseActivity;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * @author wgc
 * Material Design UI
 */
public class MaterialDesignActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.drawerLayout)
    DrawerLayout drawerLayout;
    @BindView(R.id.navView)
    NavigationView navigationView;
    @BindView(R.id.recycler)
    RecyclerView recyclerView;
    @BindView(R.id.swipe)
    SmartRefreshLayout smartRefreshLayout;

    List<Fruit> list = new ArrayList<>();


    @Override
    protected int initLayout() {
        return R.layout.activity_material_design;
    }

    @Override
    protected void initData() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setSubtitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.mipmap.ic_launcher);
        // 滑动菜单
        navigationView.setCheckedItem(R.id.navCall);
        navigationView.setNavigationItemSelectedListener(item -> {
            drawerLayout.closeDrawers();
            return true;
        });
        initFruit();
        GridLayoutManager manager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(manager);
        FruitAdapter adapter = new FruitAdapter(this, list);
        recyclerView.setAdapter(adapter);
        smartRefreshLayout.setOnRefreshListener(refreshLayout -> initFruit());
        smartRefreshLayout.autoRefresh();
    }

    private void initFruit(){
        list.clear();
        Fruit fruit = new Fruit("图片1图片1图片1图片1图片1图片1图片1图片1图片1图片1图片1图片1图片1图片1图片1图片1图片1图片1图片1图片1图片1图片1图片1图片1图片1",R.drawable.image1);
        list.add(fruit);
        Fruit fruit2 = new Fruit("图片2图片2图片2图片2图片2图片2图片2图片2图片2图片2图片2图片2图片2图片2图片2图片2图片2图片2图片2图片2图片2图片2图片2图片2图片2图片2图片2图片2",R.drawable.image2);
        list.add(fruit2);
        Fruit fruit3 = new Fruit("图片3图片3图片3图片3图片3图片3图片3图片3图片3图片3图片3图片3图片3图片3图片3图片3图片3图片3图片3图片3图片3图片3",R.drawable.image3);
        list.add(fruit3);
        Fruit fruit4 = new Fruit("图片4图片4图片4图片4图片4图片4图片4图片4图片4图片4图片4图片4图片4图片4图片4图片4图片4图片4图片4图片4图片4图片4图片4图片4图片4图片4图片4图片4图片4",R.drawable.image4);
        list.add(fruit4);
        Fruit fruit5 = new Fruit("图片5图片5图片5图片5图片5图片5图片5图片5图片5图片5图片5图片5图片5图片5图片5图片5图片5图片5图片5图片5图片5图片5图片5图片5图片5",R.drawable.image5);
        list.add(fruit5);
        Fruit fruit6 = new Fruit("图片6图片6图片6图片6图片6图片6图片6图片6图片6图片6图片6图片6图片6图片6图片6图片6图片6图片6图片6图片6图片6图片6图片6图片6图片6图片6图片6图片6图片6图片6图片6",R.drawable.image6);
        list.add(fruit6);
        Fruit fruit7 = new Fruit("图片7图片7图片7图片7图片7图片7图片7图片7图片7图片7图片7图片7图片7图片7图片7图片7图片7图片7图片7图片7图片7图片7图片7图片7图片7图片7图片7图片7图片7图片7图片7",R.drawable.image7);
        list.add(fruit7);
        Fruit fruit8 = new Fruit("图片8图片8图片8图片8图片8图片8图片8图片8图片8图片8图片8图片8图片8图片8图片8图片8图片8图片8图片8图片8图片8图片8图片8图片8图片8图片8图片8图片8图片8图片8图片8图片8图片8图片8图片8图片8图片8图片8图片8图片8图片8图片8图片8",R.drawable.image8);
        list.add(fruit8);
        Fruit fruit9 = new Fruit("图片9图片9图片9图片9图片9图片9图片9图片9图片9图片9图片9图片9图片9图片9图片9图片9图片9图片9图片9图片9图片9图片9图片9图片9图片9图片9图片9图片9图片9图片9图片9图片9图片9图片9图片9图片9图片9图片9图片9图片9图片9图片9图片9图片9图片9图片9图片9图片9图片9图片9图片9图片9图片9",R.drawable.image9);
        list.add(fruit9);
        smartRefreshLayout.finishRefresh();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar, menu);
        return true;
    }

    @SuppressLint("WrongConstant")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.backUp:
                Toast.makeText(this, "backup", Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete:
                Toast.makeText(this, "delete", Toast.LENGTH_SHORT).show();
                break;
            case R.id.setting:
                Toast.makeText(this, "setting", Toast.LENGTH_SHORT).show();
                break;
            case android.R.id.home:
                drawerLayout.openDrawer(Gravity.START);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
