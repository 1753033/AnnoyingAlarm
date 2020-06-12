package com.example.annoyingalarm.sleep;

import android.provider.BaseColumns;

public final class SleepContract {
    public SleepContract() {}

    public static abstract class Sleep implements BaseColumns {
        public static final String TABLE_NAME = "sleep";
        public static final String COLUMN_NAME_SLEEP_STARTTOSLEEPHRS = "starttosleephrs";
        public static final String COLUMN_NAME_SLEEP_STARTTOSLEEPMINUTE = "starttosleepminute";
        public static final String COLUMN_NAME_SLEEP_WAKEUPTIMEHRS = "wakeuptimehrs";
        public static final String COLUMN_NAME_SLEEP_WAKEUPTIMEMINUTE = "wakeuptimeminute";
        public static final String COLUMN_NAME_SLEEP_SLEEPDURATION = "sleepduration";
        public static final String COLUMN_NAME_SLEEP_DAY = "day";
        public static final String COLUMN_NAME_SLEEP_MONTH = "month";
        public static final String COLUMN_NAME_SLEEP_YEAR = "year";
    }
}
