package com.nsk.address;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "Address";
    private final String TAG = "DBHelper";
    public DBHelper(Context context) {
        super(context, DB_NAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        Log.d(TAG, "onCreate");
        Cursor cursor = sqLiteDatabase
                .rawQuery("select name from sqlite_master where type='table' and name='ADDRESS'",null);
        cursor.moveToFirst();
        if(cursor.getCount() > 0)
            Log.d(TAG, "onCreate: 테이블이 이미 존재 합니다.");
        else{
            Log.d(TAG, "onCreate: 테이블이 생성.");
            String sql = "create table address(id integer primary key autoincrement, name text, tel text)";
            sqLiteDatabase.execSQL(sql);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        Log.d(TAG, "onUpgrade");
        String sql = "drop table if exists address";
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);
    }
}
