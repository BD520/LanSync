package com.cjh.lansync.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class LocalSQLiteOpenHelper extends SQLiteOpenHelper {

    public LocalSQLiteOpenHelper(@Nullable Context context, @Nullable String name, int version) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DBConfig.CREATE_TABLE_CONTACTS);
        db.execSQL(DBConfig.CREATE_TABLE_SMS);
        db.execSQL(DBConfig.CREATE_TABLE_APPS);
        db.execSQL(DBConfig.CREATE_TABLE_DCIM);
        db.execSQL(DBConfig.CREATE_TABLE_MEDIA);
        // TODO: 2019/6/9 创建电话号表
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO: 2019/6/9 实现数据库更新代码
    }
}
