package com.hfad.tourmate.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by rakib on 1/19/18.
 */

public class UserDataBaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "userDataBase";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_USER="user";

    public static final String COL_USER_ID="id";
    public static final String COL_USER_NAME="name";
    public static final String COL_USER_EMAIL="email";
    public static final String COL_USER_PHONE="phone";
    public static final String COL_USER_PASSWORD="password";


    public static final String CREATE_TABLE_USER = "CREATE TABLE "+TABLE_USER+"("+
            COL_USER_ID+" INTEGER PRIMARY KEY, "+
            COL_USER_NAME+" TEXT NOT NULL, "+
            COL_USER_EMAIL+" TEXT NOT NULL UNIQUE, "+
            COL_USER_PHONE+" TEXT NOT NULL UNIQUE, "+
            COL_USER_PASSWORD+" TEXT NOT NULL);";



    public UserDataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(CREATE_TABLE_USER);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
