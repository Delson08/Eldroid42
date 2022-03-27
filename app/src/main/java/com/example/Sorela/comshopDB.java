package com.example.Sorela;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class comshopDB extends SQLiteOpenHelper {
    public comshopDB(Context context) {
        super(context, "comshop.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table ComshopInventory(id TEXT primary key, name TEXT, dsc TEXT, price TEXT, quantity TEXT)");
    }
    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int ii) {
        DB.execSQL("drop Table if exists ComshopInventory");
        onCreate(DB);
    }

    public Boolean insertuserdata(String id, String name, String desc, String price, String quantity) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", id);
        contentValues.put("name", name);
        contentValues.put("dsc", desc);
        contentValues.put("price", price);
        contentValues.put("qty", quantity);
        long result = DB.insert("ComshopInventory", null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
    public Boolean updateuserdata(String id, String name, String desc, String price, String quantity)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("dsc", desc);
        contentValues.put("price", price);
        contentValues.put("qty", quantity);
        Cursor cursor = DB.rawQuery("Select * from ComshopInventory where id = ?", new String[]{id});
        if (cursor.getCount() > 0) {
            long result = DB.update("ComshopInventory", contentValues, "id=?", new String[]{id});
            if (result == -1) {
                return false;
            }
            else {
                return true;
            }
        }
        else {
            return false;
        }
    }
    public Boolean deletedata (String id)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from ComshopInventory where id = ?", new String[]{id});
        if (cursor.getCount() > 0) {
            long result = DB.delete("ComshopInventory", "id=?", new String[]{id});
            if (result == -1) {
                return false;
            }
            else {
                return true;
            }
        }
        else {
            return false;
        }
    }
    public Cursor getdata ()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from ComshopInventory", null);
        return cursor;
    }
}