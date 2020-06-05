package com.example.annoyingalarm.alarm;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class AlarmService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // TODO Auto-generated method stub
        String type = intent.getExtras().getString("alarmType");
        Intent alarmIntent;
        if(type.equals("Math")) {
            alarmIntent = new Intent(getBaseContext(), AlarmScreenMathActivity.class);
        }
        else if (type.equals("Shake")){
            alarmIntent = new Intent(getBaseContext(),AlarmScreenShakeActivity.class);
        }
        else {
            alarmIntent = new Intent(getBaseContext(), AlarmScreenActivity.class);
        }
        alarmIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        alarmIntent.putExtras(intent);
        getApplication().startActivity(alarmIntent);

        AlarmManagerHelper.setAlarms(this);

        return super.onStartCommand(intent, flags, startId);
    }
}
