package com.example.sunyidong.qinfeng;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class Newbase extends SQLiteOpenHelper {

    public Newbase(Context context, String name, int version) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //初始化数据库操作，第一次打开数据库时回调
        String createSQL="create table liuyantable(" +
                "name text primary key," +
                "phone text not null," +
                "qq text not null," +
                "email text not null," +
                "content text not null," +
                "time text not null)";
        db.execSQL(createSQL);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.e("OpenHelper","onUpgrade");
    }
}