package com.mzk.sqliteproject;

import android.provider.BaseColumns;

public class Contract  {



    private Contract() {
    }

    public static class userTable implements BaseColumns {
        public static final String TABLE_NAME = "user";
        public static final String COLUMN_USERNAME = "username";
        public static final String COLUMN_PASSWORD = "password";
    }


}
