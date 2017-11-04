package com.example.bob.gymapp;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by marmm on 10/2/17.
 */

public class DBHelper extends SQLiteOpenHelper {


    public static final String DATABASE_FIRST_NAME = "reminders";
    public static final String DATABASE_NAME_EXTENSION = ".db";
    public static final String DATABASE_NAME = DATABASE_FIRST_NAME + DATABASE_NAME_EXTENSION;
    public static final int DATABASE_VERSION = 3;

    // Creating the table
    private static final String DATABASE_CREATE =
            "CREATE TABLE " + RemindersContract.ReminderEntry.TABLE_NAME_WORKOUT +
                    "(" +
                    RemindersContract.ReminderEntry._ID + " INTEGER PRIMARY KEY, " +
                    RemindersContract.ReminderEntry.COLUMN_NAME_WORKOUT +
                    ");";

                  /*  private static final String DATABASE_CREATE2 =
                            "CREATE TABLE "
                                    + RemindersContract.PushEntry.TABLE_NAME_PUSH +
                                    "(" +
                                    RemindersContract.PushEntry._ID + " INTEGER PRIMARY KEY,"
                                    + RemindersContract.PushEntry.COLUMN_NAME_PUSH + ");"; */


    //private static final String CREATE_TABLE_PUSH =



    //Constructor
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DATABASE_CREATE);
       // sqLiteDatabase.execSQL(DATABASE_CREATE2);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + RemindersContract.ReminderEntry.TABLE_NAME_WORKOUT);
        onCreate(sqLiteDatabase);

    }
}