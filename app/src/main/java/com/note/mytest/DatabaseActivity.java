package com.note.mytest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.note.testlibrary.tool.BaseActivity;
import com.note.testlibrary.tool.DatabaseHelper;
import com.note.testlibrary.tool.WGCLogUtils;

/**
 * @author : wgc
 * @e-mail : 786722510@qq.com
 * @date : 2020/9/29 9:01
 * @desc :测试数据库操作
 * */
public class DatabaseActivity extends BaseActivity implements View.OnClickListener {

    private   DatabaseHelper databaseHelper;
    private SQLiteDatabase sqLiteDatabase;
    private EditText etAccount,etPassWord;
    private Button btnAdd,btnDelete,btnUpdate,btnQuery;
    private ContentValues values = new ContentValues();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WGCLogUtils.d("数据库 onCreate");
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_database;
    }

    @Override
    protected void initData() {
        etAccount = findViewById(R.id.et_input_account);
        etPassWord = findViewById(R.id.et_input_password);
        btnAdd = findViewById(R.id.btn_add);
        btnDelete = findViewById(R.id.btn_delete);
        btnUpdate = findViewById(R.id.btn_update);
        btnQuery = findViewById(R.id.btn_query);
        btnAdd.setOnClickListener(this);
        btnQuery.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        WGCLogUtils.d("数据库 onResume");
        databaseHelper = new DatabaseHelper(DatabaseActivity.this);
        // getWritableDatabase 可读可写数据操作
        sqLiteDatabase = databaseHelper.getWritableDatabase();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_add:
                values.put("account", etAccount.getText().toString());
                values.put("password", etPassWord.getText().toString());
                sqLiteDatabase.insert(DatabaseHelper.login, null, values);
                break;
            case R.id.btn_delete:
                sqLiteDatabase.delete(DatabaseHelper.login, "id=?", new String[]{"3"});
                break;
            case R.id.btn_update:
                String update_password = "666";
                values.put("password", update_password);
                /**
                 * 参数说明
                 * table 数据库表
                 * values 修改内容值，以键值对形式
                 * whereClause 要修改内容的字段在数据库中的名称
                 * whereArgs 要修改内容的字段在数据库中要替换的值
                 */
                sqLiteDatabase.update(DatabaseHelper.login, values, "password=?", new String[]{
                        "123"});
                break;
            case R.id.btn_query:
                Cursor cursor = sqLiteDatabase.query(DatabaseHelper.login, new String[]{}, null, null, null, null, null);
                while (cursor.moveToNext()) {
                    int id = cursor.getInt(cursor.getColumnIndex("id"));
                   String account = cursor.getString(cursor.getColumnIndex("account"));
                   String password = cursor.getString(cursor.getColumnIndex("password"));
                    WGCLogUtils.i("ID：" + id);
                   WGCLogUtils.i("账号：" + account);
                   WGCLogUtils.i("密码：" + password);
                }
                // 最后一定得关闭游标
                cursor.close();
                break;
            default:
                break;

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (sqLiteDatabase != null){
            sqLiteDatabase.close();
        }
    }
}
