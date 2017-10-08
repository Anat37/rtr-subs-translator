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

import com.google.gson.internal.bind.DateTypeAdapter;

import java.util.ArrayList;
import java.util.List;

public class DataBase {
    private static final String sourceWord = "word";
    private static final String translatedWord = "translation";
    private static final String sourceLang = "froml";
    private static final String destLang = "tol";
    private static final String tableName = "wordTable";

    private static DataBase instance;
    final String LOG_TAG = "dbLog";
    private DBHelper dbHelper;

    private DataBase(Context context) {
        dbHelper = new DBHelper(context);
    }

    static public DataBase getInstance(Context context) {
        if (instance == null) {
            instance = new DataBase(context);
        }
        return instance;
    }

    public void addWord(Word word) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(sourceWord, word.getSrcWord());
        contentValues.put(translatedWord, word.getDstWord());
        contentValues.put(sourceLang, word.getSrcLang());
        contentValues.put(destLang, word.getDstLang());
        db.insert(tableName, null, contentValues);
    }

    public ArrayList<Word> getWords() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String[] select = {sourceWord};
        Cursor cursor = db.query(tableName, null, null, null, null, null, null);
        ArrayList<Word> ret = new ArrayList<>();
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                ret.add(new Word(cursor.getString(cursor.getColumnIndex(sourceWord)),
                        cursor.getString(cursor.getColumnIndex(translatedWord)),
                        cursor.getInt(cursor.getColumnIndex(sourceLang)), cursor.getInt(cursor.getColumnIndex(destLang))));
                cursor.moveToNext();
            }
        }
        cursor.close();
        return ret;
    }

    public Boolean isIn(Word word) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String[] columns = {sourceWord, translatedWord, sourceLang, destLang};
        String selection = sourceWord + " = ? and " + translatedWord + " = ? and " + sourceLang +
                " = ? and " + destLang + " = ?";
        String[] args = {word.getSrcWord(), word.getDstWord() == null ? "NULL" : word.getDstWord(),
                word.getSrcLang().toString(),
                word.getDstLang().toString()};
        Cursor cursor = db.query(tableName, columns, selection, args, null, null, null);
        if (cursor.getCount() == 1) {
            cursor.close();
            return Boolean.TRUE;
        } else {
            cursor.close();
            return Boolean.FALSE;
        }
    }

    public void delete(Word word) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String selection = sourceWord + " = ?";
        String[] args = {word.getSrcWord()};
        db.delete(tableName, selection, args);
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
            db.execSQL("create table " + tableName + " ("
                    + "id integer primary key autoincrement,"
                    + sourceWord + " text,"
                    + translatedWord + " text," + sourceLang + " integer," + destLang + " integer" + ");");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}


