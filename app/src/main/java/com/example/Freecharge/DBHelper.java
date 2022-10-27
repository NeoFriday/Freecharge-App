package com.example.Freecharge;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "recharge.db";
    private static final String TABLE_NAME = "users";
    private static final String COLUMN_USER = "u_name";
    private static final String COLUMN_PASSWORD = "pass_word";
    private static final int DATABASE_VERSION = 1;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        String query = " CREATE TABLE " + TABLE_NAME + " ( " + COLUMN_USER + " TEXT PRIMARY KEY, " +
                COLUMN_PASSWORD + " TEXT " + " ) ";
        DB.execSQL(query);
        DB.execSQL("create Table Plandetails(id INTEGER primary key, provider TEXT, data TEXT, validity TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        DB.execSQL("drop Table if exists Plandetails");
        onCreate(DB);
    }

//    INSERTION OF USER

    public boolean addUser(String name, String pass)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put(COLUMN_USER, name);
        cv.put(COLUMN_PASSWORD, pass);

        long result = db.insert(TABLE_NAME, null, cv);

        if (result == 1)
        {
            return false;
        }
        else
        {
           return true;
        }
    }

//    CHECKING USER DATA

    public boolean checkUser(String username)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(" SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_USER + " = ? ",new String[]{username});

        if (cursor.getCount()>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

//    CHECKING USER'S PASSWORD AND CONFIRM_PASSWORD FIELD'S RECORDS

    public boolean checkUserPass(String username,String pass)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(" SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_USER + " = ? " + " AND " + COLUMN_PASSWORD + " = ? ",new String[]{username,pass} );

        if (cursor.getCount()>0)
        {
            return true;
        }
        else
        {
            return false;
        }
    }

//    INSERTION OF PLAN

    public boolean insertdata(String id, String provider, String data, String validity){
        SQLiteDatabase DB = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();

        contentValues.put("id", id);
        contentValues.put("provider", provider);
        contentValues.put("data", data);
        contentValues.put("validity", validity);

        long result = DB.insert("Plandetails", null, contentValues);

        if (result == -1){
            return false;
        }else{
            return true;
        }
    }

//    UPDATION OF PLAN

    public boolean updatedata (String id, String provider, String data, String validity) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("id", id);
        cv.put("provider", provider);
        cv.put("data", data);
        cv.put("validity", validity);

        long result = db.update("Plandetails", cv, "id" + " = ? ", new String[]{id});

        if (result == -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

//    DELETION OF DATA

    public boolean deletedata (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("id", id);

        long result = db.delete("Plandetails","id" + " = ? ", new String[]{id});

        if (result == -1)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

//    DISPLAYING DATA

    public Cursor getData(){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from Plandetails", null);
        return cursor;
    }

}
