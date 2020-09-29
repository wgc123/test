package com.note.testlibrary.tool;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

/**
 * @author : wgc
 * @e-mail : 786722510@qq.com
 * @date : 2020/9/29 9:01
 * @desc :数据库新建、版本
 * */
public class DatabaseHelper extends SQLiteOpenHelper {

    /**
     * 数据库版本
     */
    private final static int version = 1;
    /**
     * 数据库名
     */
    private final static String database_name="test_db";
    private static DatabaseHelper helper;
    private Context mContext;

    /**
     * 用户登录数据库
     */
    public static final String login = "login";

    public static DatabaseHelper getInstance(Context context){
        if (helper == null){
            helper = new DatabaseHelper(context);
        }
        return helper;
    }

    /**
     * 数据库构造函数
     * @param context 上下文对象
     * @param name 数据库名称
     * @param factory 一个可选的游标工厂（通常是 Null）
     * @param version 当前数据库的版本，值必须是整数并且是递增的状态
     */
    public DatabaseHelper(@Nullable Context context) {
        super(context, database_name, null, version);
        mContext = context;
    }

    /**
     * 数据库创建
     * 调用时刻：当数据库第1次创建时调用
     * 作用：创建数据库 表 & 初始化数据
     * SQLite数据库创建支持的数据类型： 整型数据、字符串类型、日期类型、二进制
     * @param sqLiteDatabase
     */
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + login
                // id自增长
                + "(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                // account 账号
                + "account VARCHAR(32) NOT NULL,"
                // password 密码
                +"password INTEGER NOT NULL)"
        );
    }

    /**
     * 数据库更新
     * 调用时刻：当数据库升级时则自动调用（即 数据库版本 发生变化时）
     * 作用：更新数据库表结构
     * 注：创建SQLiteOpenHelper子类对象时,必须传入一个version参数，该参数 = 当前数据库版本, 若该版本高于之前版本, 就调用onUpgrade()
     * @param sqLiteDatabase
     * @param i 旧版本数据库
     * @param i1 新版本数据库
     */
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        onCreate(sqLiteDatabase);
    }
}
