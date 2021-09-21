package com.calc.calculator.DataBase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.calc.calculator.DataBase.Model.Calculator;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "Calculator_db";


    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {

        // create table
        db.execSQL(Calculator.CREATE_TABLE);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + Calculator.TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    public void Delete_Table() {
        // Drop older table if existed
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + Calculator.TABLE_NAME);
        db.close();
    }

    public long insertHistory(String input, String result) {
        // get writable database as we want to write data
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(Calculator.COLUMN_INPUT, input);
        values.put(Calculator.COLUMN_RESULT, result);

        // insert row
        long id = db.insert(Calculator.TABLE_NAME, null, values);

        // close db connection
        db.close();

        // return newly inserted row id
        return id;
    }

    public Calculator getHistory(long id) {
        // get readable database as we are not inserting anything
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(Calculator.TABLE_NAME,
                new String[]{Calculator.COLUMN_ID, Calculator.COLUMN_INPUT, Calculator.COLUMN_RESULT, Calculator.COLUMN_TIMESTAMP},
                Calculator.COLUMN_ID + "=?",
                new String[]{String.valueOf(id)}, null, null, null, null);

        if (cursor != null)
            cursor.moveToFirst();

        // prepare object
        assert cursor != null;
        Calculator history_object = new Calculator(
                cursor.getInt(cursor.getColumnIndex(Calculator.COLUMN_ID)),
                cursor.getString(cursor.getColumnIndex(Calculator.COLUMN_INPUT)),
                cursor.getString(cursor.getColumnIndex(Calculator.COLUMN_RESULT)),
                cursor.getString(cursor.getColumnIndex(Calculator.COLUMN_TIMESTAMP)));

        // close the db connection
        cursor.close();

        return history_object;
    }

    public List<Calculator> getAllHistory() {
        List<Calculator> history = new ArrayList<>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + Calculator.TABLE_NAME + " ORDER BY " +
                Calculator.COLUMN_TIMESTAMP + " DESC";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Calculator history_object = new Calculator();
                history_object.setId(cursor.getInt(cursor.getColumnIndex(Calculator.COLUMN_ID)));
                history_object.setInput(cursor.getString(cursor.getColumnIndex(Calculator.COLUMN_INPUT)));
                history_object.setResult(cursor.getString(cursor.getColumnIndex(Calculator.COLUMN_RESULT)));
                history_object.setTimestamp(cursor.getString(cursor.getColumnIndex(Calculator.COLUMN_TIMESTAMP)));

                history.add(history_object);
            } while (cursor.moveToNext());
        }

        // close db connection
        db.close();
        cursor.close();

        // return list
        return history;
    }

    public int getHistoryCount() {
        String countQuery = "SELECT  * FROM " + Calculator.TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);

        int count = cursor.getCount();
        cursor.close();


        // return count
        return count;
    }

}
