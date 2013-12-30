package com.warrior.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.appcompat.R;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by warrior on 13-12-30.
 */
public class FeedDb extends SQLiteOpenHelper {
    private final static String DATABASE_NAME = "feed.db";
    private final static int DATABASE_VERSION = 6;
    private final static String TABLE_NAME = "feed_log";
    public final static String FEED_LOG_ID = "_id";
    public final static String FEED_LOG_TIME = "time";
    public final static String FEED_LOG_QUANTITY = "quantity";
    public final static String FEED_LOG_CREATE_DT = "createDt";

    public FeedDb(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + "(" + FEED_LOG_ID + " integer primary key autoincrement, "
                                                        + FEED_LOG_TIME + " datetime DEFAULT CURRENT_TIMESTAMP, "
                                                        + FEED_LOG_QUANTITY + " NUMERIC,"
                                                        + FEED_LOG_CREATE_DT + " DATETIME DEFAULT CURRENT_TIMESTAMP);";
        db.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public Cursor select(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, "_id desc");
        return cursor;
    }

    public long insert(Date time, float quantity){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        cv.put(FEED_LOG_TIME, df.format(time));
        cv.put(FEED_LOG_QUANTITY, quantity);
        long row = db.insert(TABLE_NAME, null, cv);
        return  row;
    }

    public void delete(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String[] whereValue = { Integer.toString(id) };
        db.delete(TABLE_NAME, FEED_LOG_ID + "=?", whereValue);
    }
}
