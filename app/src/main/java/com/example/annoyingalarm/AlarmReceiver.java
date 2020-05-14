package com.example.annoyingalarm;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Vibrator;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String value = intent.getExtras().getString("Extra");

        Intent intentAlarmMusic = new Intent(context,AlarmMusic.class);
        intentAlarmMusic.putExtra("Extra",value);
        context.startService(intentAlarmMusic);
        /*Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.vibrate(2000);
        Notification noti = new Notification.Builder(context)
                .setContentTitle("Alarm is ON")
                .setContentText("Hello world")
                .setSmallIcon(R.drawable.icon_alarm).build();
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        noti.flags|= Notification.FLAG_AUTO_CANCEL;
        manager.notify(0,noti);*/

        /*Uri notfi = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
        Ringtone r = RingtoneManager.getRingtone(context,notfi);
        r.play();*/
    }
}
