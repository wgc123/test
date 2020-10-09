package com.note.testlibrary.tool;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Looper;
import android.provider.Settings;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * 说明：activity 基类
 * 作者：王贵才
 * 邮箱：786722510@qq.com
 */
public abstract class BaseActivity extends AppCompatActivity {
    private static Toast toast;
    public Context context;

    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        ActivityCollector.addActivity(this);         //activity统一管理
        this.getWindow().setFlags(0x80000000,0x80000000);
        setContentView(initLayout());
        // 动态加载初始化控件
        ButterKnife.bind(this);
        // 设置布局的方向
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED);
        //设置数据
        initData();
    }

    /**
     * 加载布局文件
     * @return 加载布局文件
     */
    protected abstract int initLayout();

    /**
     * 获取数据
     */
    protected abstract void initData();

    /**
     * 显示提示消息
     * @param msg 提示信息
     */
    @SuppressLint({"ShowToast", "WrongConstant"})
    public void showToast(String msg) {
        try {
            if (null == toast) {
                toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
            } else {
                toast.setText(msg);
            }
            runOnUiThread(() -> {
                toast.setGravity(Gravity.BOTTOM, 0, 100);
                toast.setDuration(1000);
                toast.show();
            });
        } catch (Exception e) {
            e.printStackTrace();
            //解决在子线程中调用Toast的异常情况处理
            Looper.prepare();
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
            Looper.loop();
        }
    }


    /**
     * @param showPass 显示密码
     */
    public void showEditPass(EditText showPass){
        showPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
//        showPass.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        showPass.setSelection(showPass.length());
    }

    /**
     * @param hidePass 隐藏密码
     */
    public void hideEditPass(EditText hidePass){
        hidePass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
//        hidePass.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        hidePass.setSelection(hidePass.length());
    }

    /**
     * 隐藏软键盘  method xxx is never used 声明了方法没有使用就会出现波浪线
     */
    public void hideSoftInput() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        if (getCurrentFocus() != null && null != imm) {
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    /**
     * 显示软键盘
     */
    public void showSoftInput() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        if (getCurrentFocus() != null && null != imm) {
            imm.showSoftInputFromInputMethod(getCurrentFocus().getWindowToken(), 0);
        }
    }

    /**
     * 页面跳转
     * @param clz 跳转的类
     */
    public void startActivity(Class<?> clz) {
        startActivity(new Intent(BaseActivity.this, clz));
    }

    /**
     * 携带数据的页面跳转
     * @param clz 跳转的类
     * @param bundle 参数
     */
    public void startActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(BaseActivity.this, clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * 返回到桌面
     */
    public void homeIntent(){
        Intent home = new Intent(Intent.ACTION_MAIN);
        home.addCategory(Intent.CATEGORY_HOME);
        startActivity(home);
    }

    @SuppressLint("SetTextI18n")
    public void getVersionName(TextView textView){
        PackageManager packageManager = getPackageManager();
        try {
            PackageInfo packageInfo = packageManager.getPackageInfo(getPackageName(), 0);
            String name = packageInfo.versionName;
            textView.setText("version:" + name);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }

    @Override
    public boolean moveTaskToBack(boolean nonRoot) {
        if(!nonRoot){
            if(!isTaskRoot()){
                return false;
            }
        }
        return super.moveTaskToBack(nonRoot);
    }

    /**
     * 悬浮窗动态设置 否则跳转开启页
     * @param code
     * @return
     */
    public boolean RequestOverlayPermission(int code) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (!Settings.canDrawOverlays(this)) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent, code);
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
}
