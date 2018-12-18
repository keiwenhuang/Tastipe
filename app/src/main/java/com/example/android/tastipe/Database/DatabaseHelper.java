package com.example.android.tastipe.Database;

/**
 * Created by Kevin on 9/14/2018
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * TODO: Add a class header comment!
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "DatabaseHelper";

    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "tastipe.db";



    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d(TAG, "onCreate: database created.");

        db.execSQL("create table " + TastipeDbSchema.TastipeTable.NAME + "(" +
                "_id integer primary key autoincrement," +
                TastipeDbSchema.TastipeTable.Cols.ID + "," +
                TastipeDbSchema.TastipeTable.Cols.TITLE + "," +
                TastipeDbSchema.TastipeTable.Cols.MINUTES + "," +
                TastipeDbSchema.TastipeTable.Cols.SERVINGS + "," +
                TastipeDbSchema.TastipeTable.Cols.IMAGE_URL + "," +
                TastipeDbSchema.TastipeTable.Cols.INGREDIENTS + "," +
                TastipeDbSchema.TastipeTable.Cols.INSTRUCTION + ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
