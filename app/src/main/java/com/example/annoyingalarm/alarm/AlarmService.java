package com.example.annoyingalarm.alarm;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.example.annoyingalarm.DBHelper;

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
        Boolean once = intent.getExtras().getBoolean("once");
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

        if(once){
            DBHelper dbHelper = new DBHelper(this);
            dbHelper.getAlarm(intent.getExtras().getLong("id")).setEnabled(false);
        }
        AlarmManagerHelper.setAlarms(this);

        return super.onStartCommand(intent, flags, startId);
    }
}
