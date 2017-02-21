package com.thernsgroup.addressbook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by user on 14/2/17.
 */

public class DBconnectivity extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "AkhilContactDB.db";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE contact (ID INTEGER PRIMARY KEY   AUTOINCREMENT,name Text,mobile TEXT,email TEXT)";

    public DBconnectivity(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    // Getting All data
    public List<values> getAllvalues() {
        List<values> contactList = new ArrayList<values>();
        // Select All Query
        String selectQuery = "SELECT  * FROM contact";

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                values lang = new values();
                lang.setId(Integer.parseInt(cursor.getString(0)));
                lang.setValue(cursor.getString(1),cursor.getString(2),cursor.getString(3));
                contactList.add(lang);
            } while (cursor.moveToNext());
        }

        return contactList;
    }

    // Getting Specific data
    public values getValuesById(long id) {
        String selectQuery = "SELECT  * FROM contact where id="+id;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        values lang = new values();
        if(cursor.getCount()>0)
        {
            cursor.moveToFirst();
            Log.d("Id",cursor.getString(0));
            lang.setId(Integer.parseInt(cursor.getString(0)));
            lang.setValue(cursor.getString(1),cursor.getString(2),cursor.getString(3));
        }
        else
        {
            lang.setId(0);
            lang.setValue("","","");
        }

        return lang;
    }

}
