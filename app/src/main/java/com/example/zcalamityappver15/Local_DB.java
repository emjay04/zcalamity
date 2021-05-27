package com.example.zcalamityappver15;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Local_DB extends SQLiteOpenHelper {
    public static String DBNAME = "LGU_DB.db";
    public Local_DB(Context context){super(context,"LGU_DB.db",null,1);}

    @Override
    public void onCreate(SQLiteDatabase Ldb) {
        Ldb.execSQL("create Table users(username TEXT primary key, office TEXT, barangay TEXT, establishment TEXT, barangay1 TEXT, street TEXT )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase Ldb, int oldVersion, int newVersion) {
        Ldb.execSQL("drop Table if exists users");
    }

    public  Boolean insertData(String username, String office, String barangay, String establishment, String barangay1 , String street){
        SQLiteDatabase Ldb = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username", username);
        contentValues.put("office", office);
        contentValues.put("barangay", barangay);
        contentValues.put("establishment", establishment);
        contentValues.put("barangay", barangay1);
        contentValues.put("street", street);
        long result = Ldb.insert("users",null,contentValues);
        if (result==-1) return false;
        else return true;
    }

    public Boolean checkuseracc(String username, String office, String barangay, String establishment, String barangay1 , String street){
        SQLiteDatabase Ldb = this.getWritableDatabase();
        Cursor cursor = Ldb.rawQuery("Select * from users where username = ?", new String[]{username,office,barangay,establishment,barangay1,street});
        if (cursor.getCount()>0)
            return true;
        else
            return false;
    }
}
