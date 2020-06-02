package com.example.annoyingalarm;

import android.provider.BaseColumns;

public final class ChallengeContract {
    public ChallengeContract() {
    }

    public static abstract class Challenge implements BaseColumns {
        public static final String TABLE_NAME = "challenge";
        public static final String COLUMN_NAME_CHALLENGE_ALARMTIME = "alarmtime";
        public static final String COLUMN_NAME_CHALLENGE_TURNOFFTIME = "turnofftime";
        public static final String COLUMN_NAME_CHALLENGE_STATUS = "status";
        public static final String COLUMN_NAME_CHALLENGE_SCORE = "score";
        public static final String COLUMN_NAME_CHALLENGE_IDALARM = "idalarm";
    }
}
