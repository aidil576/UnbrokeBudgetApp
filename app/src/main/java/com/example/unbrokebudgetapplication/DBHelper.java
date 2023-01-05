package com.example.unbrokebudgetapplication;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.sql.Date;
import java.sql.Time;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME ="credit.db";

    public static final String TABLE_NAME_1 ="money_in_out";
    public static final String COL_1 ="ID";
    public static final String COL_2 ="EXPENSES_TYPE";
    public static final String COL_3 ="PRICE";
    public static final String COL_4 ="DATE";
    public static final String COL_5 = "TIME";

    public static final String TABLE_NAME_2 = "income";
    public static final String COL_1_INCOME = "ID_INCOME";
    public static final String COL_2_INCOME = "INCOME_VALUE";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+ TABLE_NAME_1 +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,EXPENSES_TYPE TEXT, PRICE DOUBLE, DATE DATE, TIME TIME)");
        db.execSQL("create table "+ TABLE_NAME_2 +" (ID_INCOME INTEGER PRIMARY KEY AUTOINCREMENT, INCOME_VALUE DOUBLE)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME_1);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME_2);
        onCreate(db);
    }

    public boolean addMoney(Context context, String ExpenseType,double amount, Date date, Time time) {
        SQLiteDatabase db = new DBHelper(context).getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, ExpenseType);
        contentValues.put(COL_3, amount);
        contentValues.put(COL_4, String.valueOf(date));
        contentValues.put(COL_5, String.valueOf(time));
        long result = db.insert(TABLE_NAME_1,null,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }
    public boolean changeIncome(Context context, double income)
    {
        SQLiteDatabase db = new DBHelper(context).getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2_INCOME, income);
        long result = db.insert(TABLE_NAME_2,null,contentValues);
        if(result == -1)
            return false;
        else
            return true;
    }

    @SuppressLint("Range")
    public String ShowLast(){

        String selectQuery = "SELECT * FROM " + TABLE_NAME_2 + " ORDER BY ID_INCOME DESC LIMIT 1";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        String str = "";
        if(cursor.moveToFirst())
            str = cursor.getString(cursor.getColumnIndex(COL_2_INCOME));
        cursor.close();
        return str;
    }
}
