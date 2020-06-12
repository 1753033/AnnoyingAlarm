package com.example.annoyingalarm.sleep;

import android.provider.BaseColumns;

public final class SleepContract {
    public SleepContract() {}

    public static abstract class Sleep implements BaseColumns {
        public static final String TABLE_NAME = "sleep";
        public static final String COLUMN_NAME_SLEEP_STARTTOSLEEP = "starttosleep";
        public static final String COLUMN_NAME_SLEEP_WAKEUPTIME = "wakeuptime";
        public static final String COLUMN_NAME_SLEEP_SLEEPDURATION = "sleepduration";
        public static final String COLUMN_NAME_SLEEP_DATE = "date";
    }
}
