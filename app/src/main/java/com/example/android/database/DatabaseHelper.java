package com.example.android.database;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import javax.security.auth.Subject;

/**
 * Created by techjini on 2/1/18.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String Table_Name="Countries";
    public static final String Subject="subject";
    public static final String _ID ="_id";
    public static final String Desc ="desc";

    static final int DB_VERSION=1;
    static final String DB_Name="COUNTRIES.DB";
    private static final String CREATE_TABLE="create table"+Table_Name+"("+_ID+"INTEGER PRIMARY KEY AUTOINCREMENT,"+ Subject+"TEXT NOT NULL"+Desc+"TEXT);";
    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public DatabaseHelper(Context context) {
        super(context, DB_Name, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS "+Table_Name);
        onCreate(db);
    }
}
