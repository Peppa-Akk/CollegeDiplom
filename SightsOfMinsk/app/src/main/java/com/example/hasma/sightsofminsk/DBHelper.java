package com.example.hasma.sightsofminsk;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper{

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "registerr.db";
    public static final String TABLE_NAME = "register";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_MIN_RATING = "min";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_PASS = "pass";
    public static final String TABLE_NAME1 = "sights";
    public static final String COLUMN_ID1 = "id_sights";
    public static final String COLUMN_NAME1 = "name_sights";
    public static final String TABLE_NAME2 = "full";
    public static final String COLUMN_ID2 = "id_Full";
    public static final String COLUMNT_RATING = "rating";

    private int[] position_id = {1, 2, 3, 4, 5, 6, 7, 8};
    private String[] name = {"Центральный ботанический сад Национальной академии наук Беларуси",
            "Белорусский государственный музей истории Великой Отечественной войны",
            "Центральный детский парк имени Максима Горького",
            "Белорусский государственный цирк",
            "Национальный художественный музей Республики Беларусь",
            "Музей валунов",
            "Национальный исторический музей Республики Беларусь",
            "Музей истории города Минска"};
    SQLiteDatabase db;


    public DBHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        ContentValues contentValues = new ContentValues();

        db.execSQL("create TABLE " + TABLE_NAME + " (" + COLUMN_ID + " integer primary key, " + COLUMN_MIN_RATING + " integer, " + COLUMN_NAME + " text, " + COLUMN_PASS + " text" + ")");

        db.execSQL("create Table " + TABLE_NAME1 + " (" + COLUMN_ID1 + " integer primary key, " + COLUMN_NAME1 + " text" + ")");

        for (int i = 0; i < position_id.length; i++){
            contentValues.put(COLUMN_ID1, position_id[i]);
            contentValues.put(COLUMN_NAME1, name[i]);
            db.insert(TABLE_NAME1, null, contentValues);
        }

        db.execSQL("create Table " + TABLE_NAME2 + " (" + COLUMN_ID2 + " integer primary key, " + COLUMN_ID + " integer, " + COLUMN_ID1 + " integer, " + COLUMNT_RATING + " integer" + ")");

        this.db = db;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(query);
        String query1 = "DROP TABLE IF EXISTS " + TABLE_NAME1;
        db.execSQL(query1);
        String query2 = "DROP TABLE IF EXISTS " + TABLE_NAME2;
        db.execSQL(query2);
        this.onCreate(db);
    }
}
