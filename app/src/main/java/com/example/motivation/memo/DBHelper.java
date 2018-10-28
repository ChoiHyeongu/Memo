package com.example.motivation.memo;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import static android.content.ContentValues.TAG;

public class DBHelper extends SQLiteOpenHelper {

    final static String DATABASE_NAME = "MEMO.db";
    final static int DATABASE_VERSION = 2;
    final static String TABLE_NAME = "MEMO";

    public DBHelper(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try{
            String DROP_SQL = "drop table if exists " + TABLE_NAME;
            db.execSQL(DROP_SQL);
        } catch (Exception e){
            Log.e(TAG, "Exception in DROP_SQL", e);
        }

        String CREATE_SQL = "create table " + TABLE_NAME + "("
                + " _id integer PRIMARY KEY autoincrement, "
                + " title text,"
                + "date text)";

        try {
            db.execSQL(CREATE_SQL);
        } catch (Exception e){
            Log.e(TAG, "Exception in CREATE_SQL", e);
        }

    }

    public void insert(String title, String date) {
        SQLiteDatabase db = getWritableDatabase();

        db.execSQL("insert into " + TABLE_NAME + " VALUES(null, '"+title+"', '" + date +"');");
        db.close();
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}




