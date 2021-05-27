package com.example.zcalamityappver15;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Res_DB extends SQLiteOpenHelper {
    public static String DBNAME = "Res_DB.db";
    public Res_DB(Context context) {super(context, "Res_DB.db",null,1);}

    @Override
    public void onCreate(SQLiteDatabase Rdb) {
        Rdb.execSQL("create Table users(username TEXT primary key, number TEXT, city TEXT, barangay TEXT, street TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase Rdb, int oldVersion, int newVersion) {
        Rdb.execSQL("drop Table if exists users");
    }



    public  Boolean insertData(String username, String number, String city, String barangay, String street){
        SQLiteDatabase Rdb = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("number", number);
        contentValues.put("city", city);
        contentValues.put("barangay", barangay);
        contentValues.put("street", street);
        long result = Rdb.insert("users",null,contentValues);
        if (result==-1) return false;
        else return true;
    }

    public Boolean checkuseracc(String username, String number, String city, String barangay, String street){
        SQLiteDatabase Rdb = this.getWritableDatabase();
        Cursor cursor = Rdb.rawQuery("Select * from users where username = ?", new String[]{username,number,city,barangay,street});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }
}
