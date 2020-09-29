package com.note.mytest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;

import com.note.testlibrary.tool.MyService;
import com.note.testlibrary.tool.WGCLogUtils;
import com.note.testlibrary.tool.WGCSharedPreferencesUtils;

/**
 *    @author : wgc
 *    @e-mail : 786722510@qq.com
 *    @date   : ${DATE} ${TIME}
 *    @desc   :
 */
public class MainActivity extends AppCompatActivity {

    MyService.MyBinder myBinder;
    /**
     * 创建ServiceConnection的匿名类
     */
    private ServiceConnection connection = new ServiceConnection() {

        //重写onServiceConnected()方法和onServiceDisconnected()方法
        //在Activity与Service建立关联和解除关联的时候调用
        @Override
        public void onServiceDisconnected(ComponentName name) {
        }

        //在Activity与Service解除关联的时候调用
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //实例化Service的内部类myBinder
            //通过向下转型得到了MyBinder的实例
            myBinder = (MyService.MyBinder) service;
            //在Activity调用Service类的方法
            myBinder.service_connect_Activity();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.start_service).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //构建启动服务的Intent对象
                Intent startIntent = new Intent(MainActivity.this, MyService.class);
                //调用startService()方法-传入Intent对象,以此启动服务
                startService(startIntent);
            }
        });

        findViewById(R.id.stop_service).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //构建启动服务的Intent对象
                Intent startIntent = new Intent(MainActivity.this, MyService.class);
                //调用startService()方法-传入Intent对象,以此启动服务
                stopService(startIntent);
            }
        });

        findViewById(R.id.bindService).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //构建绑定服务的Intent对象
                Intent bindIntent = new Intent(MainActivity.this, MyService.class);
                //调用bindService()方法,以此停止服务
                bindService(bindIntent,connection,BIND_AUTO_CREATE);
                //参数说明
                //第一个参数:Intent对象
                //第二个参数:上面创建的Serviceconnection实例
                //第三个参数:标志位
                //这里传入BIND_AUTO_CREATE表示在Activity和Service建立关联后自动创建Service
                //这会使得MyService中的onCreate()方法得到执行，但onStartCommand()方法不会执行
            }
        });

        findViewById(R.id.unbindService).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //调用unbindService()解绑服务
                //参数是上面创建的Serviceconnection实例
                unbindService(connection);
            }
        });

        findViewById(R.id.btn_shared_preferences).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              String show =
                      WGCSharedPreferencesUtils.getInstance(MainActivity.this).getSharedPreferencesParam("test", "name");
                WGCLogUtils.i("获取存储信息：" + show);
            }
        });

        findViewById(R.id.btn_database).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,DatabaseActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        WGCSharedPreferencesUtils.getInstance(MainActivity.this).getSharedPreferencesConfig("test", "name", "11111");
    }
}
