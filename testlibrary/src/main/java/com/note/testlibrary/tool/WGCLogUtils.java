package com.note.testlibrary.tool;
import android.util.Log;

import com.note.testlibrary.BuildConfig;

/**
 * 说明：日志工具
 * 作者：王贵才
 * 邮箱：786722510@qq.com
 */
public class WGCLogUtils {

    private static String className;//类名
    private static String methodName;//方法名
    private static int lineNumber;//行数


    private static boolean isDebuggable() {
        return !BuildConfig.DEBUG;
    }

    private static String createLog(String log ) {
        return "wgc" + " " + methodName + "  " + "(" + className + ":" + lineNumber + ")：" + log;
    }

    // 获取文件名、方法名、所在行数
    private static void getMethodNames(StackTraceElement[] sElements){
        className = sElements[1].getFileName();
        methodName = sElements[1].getMethodName();
        lineNumber = sElements[1].getLineNumber();
    }

    /**
     *颜色是红色
     * @param message 打印程序中的错误信息(loge+tab)
     */
    public static void e(String message){
        if (isDebuggable())
            return;
        getMethodNames(new Throwable().getStackTrace());
        Log.e("wgc" +className, createLog(message));
    }

    /**
     * 输出为绿色 打印一些比较重要的数据，可帮助你分析用户行为数据（logi+tab）
     * @param message
     */
    public static void i(String message){
        if (isDebuggable())
            return;
        getMethodNames(new Throwable().getStackTrace());
        Log.i("wgc" +className, createLog(message));
    }

    /**
     *颜色是蓝色 打印一些调试信息(logd+tab)
     * @param message
     */
    public static void d(String message){
        if (isDebuggable())
            return;
        getMethodNames(new Throwable().getStackTrace());
        Log.d("wgc" +className, createLog(message));
    }

    /**
     * 输出颜色为黑色 打印一些最为繁琐、意义不大的日志信息
     * @param message
     */
    public static void v(String message){
        if (isDebuggable())
            return;
        getMethodNames(new Throwable().getStackTrace());
        Log.v("wgc" +className, createLog(message));
    }

    /**
     *  输出为橙色 Warning表示警告，但不一定会马上出现错误，开发时有时用来表示特别注意的地方
     * @param message
     */
    public static void w(String message){
        if (isDebuggable())
            return;
        getMethodNames(new Throwable().getStackTrace());
        Log.w("wgc" +className, createLog(message));
    }

}
