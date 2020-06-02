package com.example.annoyingalarm;

import android.provider.BaseColumns;

public final class AccountContract {
    public AccountContract() {}

    public static abstract class Account implements BaseColumns {
        public static final String TABLE_NAME = "account";
        public static final String COLUMN_NAME_ACCOUNT_NAME = "name";
        public static final String COLUMN_NAME_ACCOUNT_EMAIL = "email";
        public static final String COLUMN_NAME_ACCOUNT_SYNCDATA = "syncdata";
        public static final String COLUMN_NAME_ACCOUNT_SYNCGOOGLE = "syncgoogle";
    }
}
