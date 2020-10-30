package com.note.mytest.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.TestLooperManager;
import android.text.style.DynamicDrawableSpan;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.hjq.permissions.OnPermission;
import com.hjq.permissions.Permission;
import com.hjq.permissions.XXPermissions;
import com.hjq.toast.ToastUtils;
import com.note.mytest.FragmentActivity;
import com.note.mytest.R;
import com.note.mytest.bean.EventBean;
import com.note.mytest.okhttp.Data;
import com.note.mytest.okhttp.HttpCallback;
import com.note.mytest.okhttp.HttpHelper;
import com.note.mytest.tool.ClickProxy;
import com.note.testlibrary.tool.MyService;
import com.note.testlibrary.tool.WGCLogUtils;
import com.note.testlibrary.tool.WGCSharedPreferencesUtils;
import com.sunchen.netbus.NetStatusBus;
import com.sunchen.netbus.annotation.NetSubscribe;
import com.sunchen.netbus.type.NetType;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import kotlin.LateinitKt;

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

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.tv_show);
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

        findViewById(R.id.btn_zxingg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ZxingActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btn_fragment).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, FragmentActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btn_permissions).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                XXPermissions.with(MainActivity.this)
                        .permission(Permission.CAMERA)
                        .permission(Permission.RECORD_AUDIO)
                        .permission(Permission.READ_PHONE_STATE)
                        .permission(Permission.WRITE_EXTERNAL_STORAGE)
                        .permission(Permission.READ_EXTERNAL_STORAGE)
                        .permission(Permission.Group.CALENDAR)
                        .request(new OnPermission() {
                            @Override
                            public void hasPermission(List<String> granted, boolean all) {
                                if (all) {
                                    WGCLogUtils.d("获取存储和拍照权限成功");
                                    ToastUtils.show("获取存储和拍照权限成功");
                                } else {
                                    WGCLogUtils.d("获取权限成功，部分权限未正常授予");
                                    ToastUtils.show("获取权限成功，部分权限未正常授予");
                                }
                            }

                            @Override
                            public void noPermission(List<String> denied, boolean never) {
                                if (never) {
                                    WGCLogUtils.d("被永久拒绝授权，请手动授予存储和拍照权限");
                                    ToastUtils.show("被永久拒绝授权，请手动授予");
                                    // 如果是被永久拒绝就跳转到应用权限系统设置页面
                                    XXPermissions.startPermissionActivity(MainActivity.this, denied);
                                } else {
                                    WGCLogUtils.d("获取存储和拍照权限失败");
                                    ToastUtils.show("获取存储和拍照权限失败");
                                }
                            }
                        });
            }
        });

        findViewById(R.id.btn_click).setOnClickListener(new ClickProxy(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.show("防止多次点击事件");
            }
        }));

        findViewById(R.id.btn_scwang).setOnClickListener(new ClickProxy(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ScwangActivity.class);
                startActivity(intent);
            }
        }));


        findViewById(R.id.btn_okhttp).setOnClickListener(new ClickProxy(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpHelper.obtain().get(URL_HTTP, null, new HttpCallback<Data>() {
                    @Override
                    public void onFailed(final String msg) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                textView.setText(msg);
                                ToastUtils.show(msg);
                            }
                        });
                    }

                    @Override
                    public void onSuccess(final Data data) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                textView.setText(data.toString());
                                ToastUtils.show(data.toString());
                            }
                        });
                    }
                });
            }
        }));

        findViewById(R.id.btn_database).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DatabaseActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.btn_html).setOnClickListener(new ClickProxy(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HtmlActivity.class);
                startActivity(intent);
            }
        }));

        findViewById(R.id.btn_drawable).setOnClickListener(new ClickProxy(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DrawableActivity.class);
                startActivity(intent);
            }
        }));

        findViewById(R.id.btn_eventbus).setOnClickListener(new ClickProxy(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(EventBean.getInstance("我是在main主界面传递过来的"));
                Intent intent = new Intent(MainActivity.this, EventBusActivity.class);
                startActivity(intent);
            }
        }));

    }

    /**
     * 接受传递消息
     *
     * @param message
     */
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void getMessage(EventBean message) {
        ToastUtils.show(message.message);
        WGCLogUtils.i(message.message);
        Log.i("event", message.message);
        textView.setText(message.message);
    }

    private static final String URL_HTTP = "https://www.wanandroid.com/wxarticle/chapters/json";

    @Override
    protected void onResume() {
        super.onResume();
        WGCSharedPreferencesUtils.getInstance(MainActivity.this).getSharedPreferencesConfig("test", "name", "11111");
    }

    /**
     * 注册监听网络状态
     */
    @Override
    protected void onStart() {
        super.onStart();
        NetStatusBus.getInstance().register(this);
        EventBus.getDefault().register(this);
    }

    /**
     * 销毁监听网络状态
     */
    @Override
    protected void onStop() {
        super.onStop();
        NetStatusBus.getInstance().unregister(this);
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * 监听网络
     * @param netType 会返回当前的网络类型为 NetType.WIFI 还是 NetType.MOBILE 或者NetType.NONE
     */
    @NetSubscribe()
    public void doSometing(NetType netType) {
        WGCLogUtils.i(netType.name());
        if (netType.name().equals("WIFI")){
            ToastUtils.show("当前切换到WIFI");
        }else if (netType.name().equals("MOBILE")){
            ToastUtils.show("当前切换到数据网络");
        }else if (netType.name().equals("NONE")){
            ToastUtils.show("没有网络连接");
        }

    }

    /**
     * 声明一个long类型变量：用于存放上一点击“返回键”的时刻
     */
    private long mExitTime;
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //判断用户是否点击了“返回键”
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            //与上次点击返回键时刻作差
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                //大于2000ms则认为是误操作，使用Toast进行提示
                ToastUtils.show("再按一次退出程序");
                //并记录下本次点击“返回键”的时刻，以便下次进行判断
                mExitTime = System.currentTimeMillis();
            } else {
                //小于2000ms则认为是用户确实希望退出程序-调用System.exit()方法进行退出
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
