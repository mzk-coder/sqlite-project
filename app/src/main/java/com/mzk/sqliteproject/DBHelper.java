package com.mzk.sqliteproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBHelper extends SQLiteOpenHelper {

    private static final String DBNAME = "userDB";
    private static final int DBVERSION = 3;

    SQLiteDatabase db;

    public DBHelper(Context context) {
        super(context, DBNAME, null, DBVERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {

        this.db = db;
        final String SQL_CREATE_USER_TABLE = "CREATE TABLE " +
                Contract.userTable.TABLE_NAME + "(" +
                Contract.userTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                Contract.userTable.COLUMN_USERNAME + " TEXT, " +
                Contract.userTable.COLUMN_PASSWORD + " TEXT " +
                ")";
        db.execSQL(SQL_CREATE_USER_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Contract.userTable.TABLE_NAME);
        onCreate(db);
    }

    public Boolean insertData(Account account) {

        SQLiteDatabase DariDB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Contract.userTable.COLUMN_USERNAME, account.getUsername());
        contentValues.put(Contract.userTable.COLUMN_PASSWORD, account.getPassword());

        long result = DariDB.insert(Contract.userTable.TABLE_NAME, null, contentValues);
        return result != -1;

    }


    public Boolean checkUsername(String username) {

        try {
            SQLiteDatabase DariDB = this.getWritableDatabase();


            Cursor cursor = DariDB.rawQuery("select * from " + Contract.userTable.TABLE_NAME + "  where username = ?  ", new String[]{username});
            Boolean success  = cursor.getCount() > 0;
            cursor.close();
            return success;

        } catch (Exception ex) {
            ex.printStackTrace();
            return false;

        }



    }

    public Account checkUsernamePassword(String username, String password) {
        Account account = null;
        SQLiteDatabase DariDB = this.getWritableDatabase();
        Cursor cursor = DariDB.rawQuery("select * from " + Contract.userTable.TABLE_NAME + " where "+Contract.userTable.COLUMN_USERNAME+" = ?   and " +Contract.userTable.COLUMN_PASSWORD+" = ? ", new String[]{username, password});

        if (cursor!=null) {
            cursor.moveToFirst();
            account = new Account();
            account.setId(cursor.getInt(0));
            account.setUsername(cursor.getString(1));
            account.setPassword(cursor.getString(2));

            cursor.close();
        }


        return account;

    }


}

