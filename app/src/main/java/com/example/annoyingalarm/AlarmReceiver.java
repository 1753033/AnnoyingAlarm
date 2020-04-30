package com.example.annoyingalarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("In Receiver","Hello");
        String value = intent.getExtras().getString("Extra");

        Intent intent1 = new Intent(context,AlarmMusic.class);
        intent1.putExtra("Extra",value);
        context.startService(intent1);
    }
}
