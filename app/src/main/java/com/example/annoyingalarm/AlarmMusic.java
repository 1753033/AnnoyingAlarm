package com.example.annoyingalarm;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

public class AlarmMusic extends Service {

    MediaPlayer mediaPlayer;
    int ID;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        String value = intent.getExtras().getString("Extra");

        if (value.equals("on")){
            ID = 1;
        }
        else if (value.equals("off")){
            ID = 0;
        }

        if (ID == 1) {
            mediaPlayer = MediaPlayer.create(this, R.raw.nhacchuong);
            mediaPlayer.start();

            ID = 0;
        }
        else if (ID == 0){
            mediaPlayer.stop();
            mediaPlayer.reset();
        }

        return START_NOT_STICKY;
    }
}
