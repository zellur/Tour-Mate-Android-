package com.hfad.tourmate.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.hfad.tourmate.java_class.User;

/**
 * Created by rakib on 1/19/18.
 */

public class UserDataSource {

    private SQLiteDatabase database;
    private UserDataBaseHelper helper;


    public UserDataSource(Context context){
        helper = new UserDataBaseHelper(context);
    }

    public void open(){

        database = helper.getWritableDatabase();
    }
    public void close(){

        database.close();
    }

    public boolean insertUser(User user){

        this.open();
        ContentValues values = new ContentValues();
        values.put(UserDataBaseHelper.COL_USER_NAME,user.getName());
        values.put(UserDataBaseHelper.COL_USER_EMAIL,user.getEmail());
        values.put(UserDataBaseHelper.COL_USER_PHONE,user.getPhone());
        values.put(UserDataBaseHelper.COL_USER_PASSWORD,user.getPassword());

        long insertedRow = database.insert(UserDataBaseHelper.TABLE_USER,null,values);

        if (insertedRow>0){

            this.close();

            return true;
        }else{
            this.close();
            return false;
        }

    }

    public String getUserPassword(String userPhone){
        this.open();

        Cursor cursor= database.query(UserDataBaseHelper.TABLE_USER,null,UserDataBaseHelper.COL_USER_PHONE+"=?",new String[]{userPhone},null,null,null);

        if(cursor.getCount()<1){

            String invalid = "User PhoneNo is Not Exist";

            return invalid;
        }

        else{

            cursor.moveToFirst();
            String password = cursor.getString(cursor.getColumnIndex(UserDataBaseHelper.COL_USER_PASSWORD));
            cursor.close();
            this.close();
            return password;
        }
    }
}
