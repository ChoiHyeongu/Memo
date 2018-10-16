package com.example.motivation.memo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class MemoDatabase extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "memolist.db";
    private static final int DATABASE_VERSION = 2;

    public MemoDatabase(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table memo(_id INTEGER PRIMARY KEY AUTOINCREMENT, title Text, content Text, Date date);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists memo");
        onCreate(db);
    }
}
