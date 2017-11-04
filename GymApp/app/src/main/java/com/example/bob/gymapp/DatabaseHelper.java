package com.example.bob.gymapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bob on 27-10-2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    // Logcat tag
    private static final String LOG = "DatabaseHelper";

    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "WorkoutSchedule";

    // Table Names
    private static final String TABLE_PUSH = "PushWorkouts";
    private static final String TABLE_PULL = "pull";
    private static final String TABLE_LEGS= "legs";

    // Common column names
    private static final String KEY_ID = "id";
    private static final String KEY_CREATED_AT = "created_at";

    // NOTES Table - column nmaes
    private static final String KEY_PUSH_ID = "id";
    private static final String KEY_PUSH_NAME = "push naam";

    // TAGS Table - column names
    private static final String KEY_PULL_ID = "id";
    private static final String KEY_PULL_NAME = "pull naam";

    // NOTE_TAGS Table - column names
    private static final String KEY_LEGS_ID = "id";
    private static final String KEY_LEGS_NAME = "tag_id";

    // Table Create Statements
    // Todo table create statement
    private static final String CREATE_TABLE_PUSH = "CREATE TABLE "
            + TABLE_PUSH + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_PUSH_NAME + " TEXT" + ")";

    // Tag table create statement
    private static final String CREATE_TABLE_PULL = "CREATE TABLE " + TABLE_PULL
            + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_PULL_NAME + " TEXT," + ")";

    // todo_tag table create statement
    private static final String CREATE_TABLE_LEGS = "CREATE TABLE "
            + TABLE_LEGS + "(" + KEY_ID + " INTEGER PRIMARY KEY," + KEY_LEGS_NAME + " TEXT," + ")";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        // creating required tables
        db.execSQL(CREATE_TABLE_PUSH);
        db.execSQL(CREATE_TABLE_PULL);
        db.execSQL(CREATE_TABLE_LEGS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PUSH);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PULL);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LEGS);

        // create new tables
        onCreate(db);
    }
    public long createPushWorkout(Push push) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_PUSH_ID, push.getId());
        values.put(KEY_PUSH_NAME, push.getPushName());


        // insert row
        long push_id = db.insert(TABLE_PUSH, null, values);

        return push_id;
    }

    public long createPullWorkout(Pull pull) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_PULL_ID, pull.getId());
        values.put(KEY_PULL_NAME, pull.getPullName());


        // insert row
        long pull_id = db.insert(TABLE_PUSH, null, values);

        return pull_id;
    }

    public long createLegsWorkout(Legs legs) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_PULL_ID, legs.getId());
        values.put(KEY_PULL_NAME, legs.getLegsName());


        // insert row
        long legs_id = db.insert(TABLE_PUSH, null, values);

        return legs_id;
    }

    public Push getPush(long push_id) {
        SQLiteDatabase db = this.getReadableDatabase();

        String selectQuery = "SELECT  * FROM " + TABLE_PUSH + " WHERE "
                + KEY_PUSH_ID + " = " + push_id;

        Log.e(LOG, selectQuery);

        Cursor c = db.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();

        Push p = new Push();
        p.setId(c.getInt(c.getColumnIndex(KEY_PUSH_ID)));
        p.setPushName((c.getString(c.getColumnIndex(KEY_PUSH_NAME))));


        return p;
    }

    public List<Push> getAllPushWorkouts() {
        List<Push> todos = new ArrayList<Push>();
        String selectQuery = "SELECT  * FROM " + TABLE_PUSH;

        Log.e(LOG, selectQuery);

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (c.moveToFirst()) {
            do {
                Push td = new Push();
                td.setId(c.getInt((c.getColumnIndex(KEY_PUSH_ID))));
                td.setPushName((c.getString(c.getColumnIndex(KEY_PUSH_NAME))));

                todos.add(td);
            } while (c.moveToNext());
        }

        return todos;
    }

    public int updatePush(Push push) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_PUSH_NAME, push.getPushName());


        // updating row
        return db.update(TABLE_PUSH, values, KEY_ID + " = ?",
                new String[] { String.valueOf(push.getId()) });
    }

    public void deletePushWorkout(long push_id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_PUSH, KEY_ID + " = ?",
                new String[] { String.valueOf(push_id) });
    }

    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }


}
