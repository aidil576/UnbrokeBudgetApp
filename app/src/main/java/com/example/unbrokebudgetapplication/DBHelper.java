package com.example.unbrokebudgetapplication;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.google.firebase.auth.FirebaseUser;

import java.sql.Date;
import java.sql.Time;

public class DBHelper extends SQLiteOpenHelper {

    public static String user1 = LoginActivity.getUserEmail();
    public static final String DATABASE_NAME = user1+".db";


    public static final String TABLE_NAME_1 ="money_in_out";
    public static final String COL_1 ="ID";
    public static final String COL_2 ="EXPENSES_TYPE";
    public static final String COL_3 ="PRICE";
    public static final String COL_4 ="DATE";
    public static final String COL_5 = "TIME";

    public static final String TABLE_NAME_2 = "income";
    public static final String COL_1_INCOME = "ID_INCOME";
    public static final String COL_2_INCOME = "INCOME_VALUE";

    public static final String TABLE_NAME_3 = "budget";
    public static final String COL_1_BUDGET = "ID_BUDGET";
    public static final String COL_2_BUDGET = "BUDGET_NAME";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table "+ TABLE_NAME_1 +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,EXPENSES_TYPE TEXT, PRICE DOUBLE, DATE DATE, TIME TIME)");
        db.execSQL("create table "+ TABLE_NAME_2 +" (ID_INCOME INTEGER PRIMARY KEY AUTOINCREMENT, INCOME_VALUE DOUBLE)");
        db.execSQL("create table "+ TABLE_NAME_3 +" (ID_BUDGET INTEGER PRIMARY KEY AUTOINCREMENT, BUDGET_NAME TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME_1);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME_2);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME_3);
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

    public boolean addGuide(Context context, String BudgetType){
        SQLiteDatabase db = new DBHelper(context).getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2_BUDGET, BudgetType);
        long result = db.insert(TABLE_NAME_3,null,contentValues);
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

    @SuppressLint("Range")
    public double calc_Sum(){
        double total = 0.00;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT SUM(" + DBHelper.COL_3 + ") as Total FROM " + DBHelper.TABLE_NAME_1, null);

        if (cursor.moveToFirst()) {

            total = cursor.getDouble(cursor.getColumnIndex("Total"));// get final total
    }
        return total;
    }

    @SuppressLint("Range")
    public double calcNegative() {
        double negativeValue = 0.0;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT SUM(CASE WHEN " + DBHelper.COL_3 + " < 0 THEN " + DBHelper.COL_3 + " ELSE 0 END) AS Negative_Value FROM " + DBHelper.TABLE_NAME_1, null);
        if (cursor.moveToFirst()) {
            negativeValue = cursor.getDouble(cursor.getColumnIndex("Negative_Value"));
        }
        cursor.close();
        return negativeValue;
    }

    @SuppressLint("Range")
    public String getBudget(){

        String selectQuery = "SELECT * FROM " + TABLE_NAME_3 + " ORDER BY ID_BUDGET DESC LIMIT 1";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery,null);
        String str = "";
        if(cursor.moveToFirst())
            str = cursor.getString(cursor.getColumnIndex(COL_2_BUDGET));
        cursor.close();
        return str;
    }
    public boolean isIncomeTableEmpty() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + TABLE_NAME_2, null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        cursor.close();
        return count == 0;
    }
}
