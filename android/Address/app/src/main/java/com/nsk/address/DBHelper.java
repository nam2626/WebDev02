package com.nsk.address;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "Address";
    private final String TAG = "DBHelper";
    public DBHelper(Context context) {
        super(context, DB_NAME, null, 3);
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

    public long add(AddressVO vo){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("name",vo.getName());
        values.put("tel",vo.getTel());

        long result = db.insert("Address",null,values); //데이터 삽입
        Log.d(TAG, "add: "+result);
        db.close();
        return result;
    }

    public List<AddressVO> selectAllAddressVO() {
        ArrayList<AddressVO> list = new ArrayList<AddressVO>();
        String sql = "select * from address";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(sql,null);
        Log.d(TAG, "selectAllAddressVO: "+cursor.getCount());
        if(cursor.moveToFirst()){
            do{
                list.add(new AddressVO(cursor.getInt(0),cursor.getString(1),cursor.getString(2)));
            }while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;
    }

    public void delete(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete("address","id=?",new String[]{String.valueOf(id)});
        Log.d(TAG, "delete: "+result);
        db.close();
    }
}
