package com.example.emergencyphonenumber.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.emergencyphonenumber.MainActivity;

/**
 * Created by Promlert on 2017-11-26.
 */

public class PhoneDBHelper extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "phone.db";
    public static final int DATABASE_VERSION = 2;

    public static final String TABLE_NAME = "phone_number";
    public static final String COL_ID = "_id";
    public static final String COL_TITLE = "title";
    public static final String COL_NUMBER = "number";
public static final String COL_PICTURE = "picture";

    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "("
            + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COL_TITLE + " TEXT, "
            + COL_NUMBER + " TEXT, "
            + COL_PICTURE + " TEXT)";


    public PhoneDBHelper(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_TABLE);
        insertInitialData(db);
    }

    public void insertInitialData(SQLiteDatabase db){
        ContentValues cv = new ContentValues();
        cv.put(COL_TITLE,"เเจ้งเหตุด่วนเหตุร้าย");
        cv.put(COL_NUMBER,"191");
        cv.put(COL_PICTURE, "pic001.jpg");
        db.insert(TABLE_NAME,null,cv);

        cv = new ContentValues();
        cv.put(COL_TITLE,"เเจ้งเหตุเพลิงไหม้");
        cv.put(COL_NUMBER,"199");
        cv.put(COL_PICTURE, "pic002.jpg");
        db.insert(TABLE_NAME,null,cv);
    }

    public  void onUpgrade(SQLiteDatabase db ,int i ,int il){ //old ver ,new ver

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }



}
