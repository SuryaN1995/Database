package com.example.android.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

/**
 * Created by techjini on 2/1/18.
 */

public class DBManager {
    private DatabaseHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;


    public DBManager(Context context) {
        this.context = context;
    }
    public DBManager open() throws SQLException{
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }
    public void close(){
        dbHelper.close();
    }

    public void insert(String name, String desc){
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.Subject,name);
        contentValues.put(DatabaseHelper.Desc,desc);
        database.insert(DatabaseHelper.Table_Name,null,contentValues);
    }
    public Cursor fetch(){
        String[] columns = new String[]{DatabaseHelper._ID,DatabaseHelper.Subject,DatabaseHelper.Desc};
        Cursor cursor = database.query(DatabaseHelper.Table_Name,columns,null,null,null,null,null);
        if(cursor!=null){
            cursor.moveToFirst();
        }
        return cursor;
    }
    public int update(long _id,String name, String desc){
        ContentValues contentValues= new ContentValues();
        contentValues.put(DatabaseHelper.Subject,name);
        contentValues.put(DatabaseHelper.Desc,desc);
       int i= database.update( DatabaseHelper.Table_Name, contentValues, DatabaseHelper._ID + " = " + _id,null);
        return i;
    }
    public void delete(long _id){
        database.delete(DatabaseHelper.Table_Name,DatabaseHelper._ID + "=" + _id,null);
    }

}
