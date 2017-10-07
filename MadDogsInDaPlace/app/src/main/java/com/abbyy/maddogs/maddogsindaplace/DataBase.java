package com.abbyy.maddogs.maddogsindaplace;

/**
 * Created by philipp on 07.10.17.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DataBase {
    final String LOG_TAG = "dbLog";
    private DBHelper dbHelper;

    public DataBase(Context context) {
        dbHelper = new DBHelper(context);
    }

    public void addWord(String word, String translation) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("word", word);
        contentValues.put("translation", translation);
        db.insert("wordTable", null, contentValues);
    }

    public ArrayList<String> getWords() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String[] select = {"word"};
        Cursor cursor = db.query("wordTable", select, null, null, null, null, null);
        ArrayList<String> ret = new ArrayList<>();
        if (cursor.moveToFirst()) {
            while(!cursor.isAfterLast()) {
                ret.add(cursor.getString(cursor.getColumnIndex("name")));
                cursor.moveToNext();
            }
        }
        cursor.close();
        return ret;
    }

    public void close() {
        dbHelper.close();
    }

    class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context) {
            // конструктор суперкласса
            super(context, "wordDB", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.d(LOG_TAG, "--- onCreate database ---");
            // создаем таблицу с полями
            db.execSQL("create table wordTable ("
                    + "id integer primary key autoincrement,"
                    + "word text,"
                    + "translation text" + ");");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}


