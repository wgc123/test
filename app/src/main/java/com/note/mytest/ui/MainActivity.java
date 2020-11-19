package com.note.mytest.ui;

import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

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
import com.note.mytest.tool.CustomDialog;
import com.note.testlibrary.tool.BaseActivity;
import com.note.testlibrary.tool.MyService;
import com.note.testlibrary.tool.WGCLogUtils;
import com.note.testlibrary.tool.WGCSharedPreferencesUtils;
import com.permissionx.guolindev.PermissionX;
import com.permissionx.guolindev.callback.ExplainReasonCallbackWithBeforeParam;
import com.permissionx.guolindev.callback.ForwardToSettingsCallback;
import com.permissionx.guolindev.callback.RequestCallback;
import com.permissionx.guolindev.request.ExplainScope;
import com.permissionx.guolindev.request.ForwardScope;
import com.sunchen.netbus.NetStatusBus;
import com.sunchen.netbus.annotation.NetSubscribe;
import com.sunchen.netbus.type.NetType;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.IdentityHashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author : wgc
 * @e-mail : 786722510@qq.com
 * @date : ${DATE} ${TIME}
 * @desc :
 */
public class MainActivity extends BaseActivity {
    private static final String URL_HTTP = "https://www.wanandroid.com/wxarticle/chapters/json";

    MyService.MyBinder myBinder;
    @BindView(R.id.start_service)
    Button startService;
    @BindView(R.id.stop_service)
    Button stopService;
    @BindView(R.id.bindService)
    Button bindService;
    @BindView(R.id.unbindService)
    Button unbindService;
    @BindView(R.id.btn_shared_preferences)
    Button btnSharedPreferences;
    @BindView(R.id.btn_zxingg)
    Button btnZxingg;
    @BindView(R.id.btn_fragment)
    Button btnFragment;
    @BindView(R.id.btn_permissions)
    Button btnPermissions;
    @BindView(R.id.btn_click)
    Button btnClick;
    @BindView(R.id.btn_scwang)
    Button btnScwang;
    @BindView(R.id.btn_okhttp)
    Button btnOkhttp;
    @BindView(R.id.btn_database)
    Button btnDatabase;
    @BindView(R.id.btn_html)
    Button btnHtml;
    @BindView(R.id.btn_drawable)
    Button btnDrawable;
    @BindView(R.id.btn_eventbus)
    Button btnEventbus;
    @BindView(R.id.next)
    Button next;
    @BindView(R.id.btn_jiaozi)
    Button btnJiaoZi;
    @BindView(R.id.btn_monty)
    Button btnMoney;
    @BindView(R.id.btn_algorithm)
    Button btnAlgorithm;
    @BindView(R.id.tv_show)
    TextView textView;
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
    protected int initLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        PermissionX.init(MainActivity.this)
                .permissions(Manifest.permission.CAMERA,
                        Manifest.permission.RECORD_AUDIO,
                        Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                        )
                .explainReasonBeforeRequest()
                .onExplainRequestReason(new ExplainReasonCallbackWithBeforeParam() {
                    @Override
                    public void onExplainReason(ExplainScope scope, List<String> deniedList, boolean beforeRequest) {
                        CustomDialog customDialog = new CustomDialog(MainActivity.this, "需要以下权限才能继续", deniedList);
                        scope.showRequestReasonDialog(customDialog);
                    }
                })
                .onForwardToSettings(new ForwardToSettingsCallback() {
                    @Override
                    public void onForwardToSettings(ForwardScope scope, List<String> deniedList) {
                        scope.showForwardToSettingsDialog(deniedList, "请在设置中允许以下权限", "允许");
                    }
                })
                .request(new RequestCallback() {
                    @Override
                    public void onResult(boolean allGranted, List<String> grantedList, List<String> deniedList) {
                        if (allGranted) {
                            startService.setEnabled(true);
                            Toast.makeText(MainActivity.this, "授予所有权限", Toast.LENGTH_SHORT).show();
                        } else {
                            startService.setEnabled(false);
                            Toast.makeText(MainActivity.this, "请开启权限", Toast.LENGTH_SHORT).show();
//                            Toast.makeText(MainActivity.this, "以下权限被拒绝：" + deniedList, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @OnClick({R.id.start_service, R.id.stop_service, R.id.bindService, R.id.unbindService, R.id.btn_shared_preferences,
            R.id.btn_zxingg, R.id.btn_fragment, R.id.btn_permissions, R.id.btn_click, R.id.btn_scwang, R.id.btn_okhttp,
            R.id.btn_database, R.id.btn_html, R.id.btn_drawable, R.id.btn_eventbus, R.id.next,
            R.id.btn_jiaozi,R.id.btn_monty,R.id.btn_algorithm,R.id.btn_viewpager,R.id.btn_banner,
            R.id.start_notification,R.id.btn_material})
    public void onViewClicked(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.btn_material:
                startActivity(MaterialDesignActivity.class);
                break;
            case R.id.start_notification:
                NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                    NotificationChannel channel = new NotificationChannel("normal", "Normal",
                            NotificationManager.IMPORTANCE_DEFAULT);
                    manager.createNotificationChannel(channel);
                }
                intent = new Intent(this,MainActivity.class);
                PendingIntent pendingIntent =  PendingIntent.getActivity(this, 0, intent, 0);
                Notification notification = new NotificationCompat.Builder(this,"normal")
                        .setContentTitle("通知栏")
                        .setContentText("这是一条通知栏信息")
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentIntent(pendingIntent)  // 点击通知栏进入页面
                        .setAutoCancel(true) // 点击通知栏取消显示
                        .build();
                manager.notify(1, notification);
                break;
            case R.id.start_service:
                //构建启动服务的Intent对象
                intent = new Intent(MainActivity.this, MyService.class);
                //调用startService()方法-传入Intent对象,以此启动服务
                startService(intent);
                break;
            case R.id.stop_service:
                //构建启动服务的Intent对象
                intent = new Intent(MainActivity.this, MyService.class);
                //调用startService()方法-传入Intent对象,以此启动服务
                stopService(intent);
                break;
            case R.id.bindService:
                //构建绑定服务的Intent对象
                intent = new Intent(MainActivity.this, MyService.class);
                //调用bindService()方法,以此停止服务
                bindService(intent, connection, BIND_AUTO_CREATE);
                //参数说明
                //第一个参数:Intent对象
                //第二个参数:上面创建的Serviceconnection实例
                //第三个参数:标志位
                //这里传入BIND_AUTO_CREATE表示在Activity和Service建立关联后自动创建Service
                //这会使得MyService中的onCreate()方法得到执行，但onStartCommand()方法不会执行
                break;
            case R.id.unbindService:
                //调用unbindService()解绑服务
                //参数是上面创建的Serviceconnection实例
                unbindService(connection);
                break;
            case R.id.btn_shared_preferences:
                String show = WGCSharedPreferencesUtils.getInstance(MainActivity.this).getSharedPreferencesParam("test", "name");
                WGCLogUtils.i("获取存储信息：" + show);
                break;
            case R.id.btn_zxingg:
                intent = new Intent(MainActivity.this, ZxingActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_fragment:
                intent = new Intent(MainActivity.this, FragmentActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_permissions:
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
                break;
            case R.id.btn_click:
                ToastUtils.show("防止多次点击事件");
                break;
            case R.id.btn_scwang:
                intent = new Intent(MainActivity.this, ScwangActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_okhttp:
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
                break;
            case R.id.btn_database:
                intent = new Intent(MainActivity.this, DatabaseActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_html:
                intent = new Intent(MainActivity.this, HtmlActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_drawable:
                intent = new Intent(MainActivity.this, DrawableActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_eventbus:
                EventBus.getDefault().post(EventBean.getInstance("我是在main主界面传递过来的"));
                intent = new Intent(MainActivity.this, EventBusActivity.class);
                startActivity(intent);
                break;
            case R.id.next:
                intent = new Intent(MainActivity.this, NextActivity.class);
                intent.putExtra("index", 0);
                startActivity(intent);
                break;
            case R.id.btn_jiaozi:
                intent = new Intent(MainActivity.this, JiaoZiMainActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_monty: //红包算法
                intent = new Intent(MainActivity.this, MontyActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_algorithm:
                intent = new Intent(MainActivity.this, AlgorithmActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_viewpager:
                intent = new Intent(MainActivity.this, ViewPagerActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_banner:
                intent = new Intent(MainActivity.this, BannerActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
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
     *
     * @param netType 会返回当前的网络类型为 NetType.WIFI 还是 NetType.MOBILE 或者NetType.NONE
     */
    @NetSubscribe()
    public void doSometing(NetType netType) {
        WGCLogUtils.i(netType.name());
        if (netType.name().equals("WIFI")) {
            ToastUtils.show("当前切换到WIFI");
        } else if (netType.name().equals("MOBILE")) {
            ToastUtils.show("当前切换到数据网络");
        } else if (netType.name().equals("NONE")) {
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
