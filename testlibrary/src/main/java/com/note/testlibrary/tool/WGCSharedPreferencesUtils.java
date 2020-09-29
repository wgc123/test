package com.note.testlibrary.tool;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * @author : wgc
 * @e-mail : 786722510@qq.com
 * @date : 2020/9/29 11:21
 * @desc :SharedPreferences 数据存储
 */
public class WGCSharedPreferencesUtils {

    private static WGCSharedPreferencesUtils sharedPreferences;
    private Context mContext;

    public WGCSharedPreferencesUtils(Context context){
        mContext = context;
    }

    public static WGCSharedPreferencesUtils getInstance(Context context){
        if (sharedPreferences==null){
            synchronized (WGCSharedPreferencesUtils.class) {
                if (sharedPreferences == null) {
                    sharedPreferences = new WGCSharedPreferencesUtils(context.getApplicationContext());
                }
            }
        }
        return sharedPreferences;
    }

    /**
     * sharedPreferences
     * @param name 唯一获取的文件名
     * @nameParam 保存的字段
     * @sharedPreferences 需要保存的值
     */
    public void getSharedPreferencesConfig(String name,String nameParam, String valuesParam){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(name, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(nameParam,valuesParam);
        editor.apply();
    }

    /**
     * 获取SharedPreferences存储的值
     * @param name 唯一获取的文件名
     * @param nameParam 保存的字段
     */
    public String getSharedPreferencesParam(String name,String nameParam){
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(name, Context.MODE_PRIVATE);
        name = sharedPreferences.getString(nameParam, "");
        WGCLogUtils.i("获取SharedPreferences值：" + name);
        return name;
    }




}
