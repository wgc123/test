package com.note.testlibrary.tool;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.note.testlibrary.R;
import com.note.testlibrary.tool.WGCLogUtils;

/**
 * 本地service 操作
 * @author wgc
 */
public class MyService extends Service {

    MyBinder myBinder = new MyBinder();

    /**
     * 用于和Activity建立关联的
     * @param intent
     * @return
     */
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        WGCLogUtils.d("执行 onBind");
        return myBinder;
    }

    /**
     * 1.前台Service优先级较高，不会由于系统内存不足而被回收；后台Service优先级较低，
     *   当系统出现内存不足情况时，很有可能会被回收
     * 2.前台Service在下拉通知栏有显示通知，但后台Service没有；
     */
    @Override
    public void onCreate() {
        super.onCreate();
        WGCLogUtils.d("执行 onCreate");
        //settingNotification 显示前台服务
        settingNotification();
    }

    private void settingNotification(){
        /**
        //添加下列代码将后台Service变成前台Service
        //构建"点击通知后打开MainActivity"的Intent对象
        Intent notificationIntent = new Intent(this,MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,notificationIntent,0);
        //新建Builder对象
        Notification.Builder builder = new Notification.Builder(this);
        //设置通知的标题
        builder.setContentTitle("前台服务通知的标题");
        //设置通知的内容
        builder.setContentText("前台服务通知的内容");
        //设置通知的图标
        builder.setSmallIcon(R.mipmap.ic_launcher);
        //设置点击通知后的操作
        builder.setContentIntent(pendingIntent);
        //将Builder对象转变成普通的notification
        Notification notification = builder.getNotification();
        //        startForeground(1, notification);//让Service变成前台Service,并在系统的状态栏显示出来
         */
    }

    /**
     * 一般会将 Service 和 Thread联合着用，即在Service中再创建一个子线程（工作线程）去处理耗时操作逻辑
     * @param intent
     * @param flags
     * @param startId
     * @return
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        WGCLogUtils.d("执行 onStartCommand");
        //新建工作线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                // 开始执行后台任务
            }
        }).start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        WGCLogUtils.d("执行 onDestroy");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        WGCLogUtils.d("执行 onUnbind");
        return super.onUnbind(intent);
    }


    /**
     * 新建一个子类继承自Binder类
     */
    public static class MyBinder extends Binder {
        public void service_connect_Activity() {
            //新建工作线程
            new Thread(new Runnable() {
                @Override
                public void run() {
                    // 执行具体的下载任务
                }
            }).start();
            WGCLogUtils.d("Service关联了Activity,并在Activity执行了Service的方法");
        }
    }

}
