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

    public void addWord(Word word) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("word", word.getSrcWord());
        contentValues.put("translation", word.getDstWord());
        contentValues.put("froml", word.getSrcLang());
        contentValues.put("tol", word.getDstLang());
        db.insert("wordTable", null, contentValues);
    }

    public ArrayList<Word> getWords() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String[] select = {"word"};
        Cursor cursor = db.query("wordTable", select, null, null, null, null, null);
        ArrayList<Word> ret = new ArrayList<>();
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                ret.add(new Word(cursor.getString(cursor.getColumnIndex("word"))));
                cursor.moveToNext();
            }
        }
        cursor.close();
        return ret;
    }

    public void close() {
        dbHelper.close();
    }

    @Override
    protected void finalize() throws Throwable {
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
                    + "translation text," + "froml integer," + "tol integer" + ");");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}


