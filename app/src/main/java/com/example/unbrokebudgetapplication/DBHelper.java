package com.example.unbrokebudgetapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME ="money.db";
    public static final String TABLE_NAME ="daily_expenses_table";
    public static final String COL_1 ="ID";
    public static final String COL_2 ="EXPENSES_TYPE";
    public static final String COL_3 ="PRICE";
    public static final String COL_4 ="DATE";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+ TABLE_NAME +" (ID INTEGER PRIMARY KEY,EXPENSES_TYPE TEXT, PRICE DOUBLE, DATE DATE)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME);
        onCreate(db);
    }
}
