package com.example.annoyingalarm;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class AddAlarmActivity extends AppCompatActivity {

    Button btnCancel;
    Button btnDone;
    TimePicker timePicker;
    TextView tvTime;

    Calendar calendar;
    AlarmManager alarmManager;
    PendingIntent pendingIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_alarm);

        btnCancel = findViewById(R.id.btnCancel);
        btnDone = findViewById(R.id.btnDone);
        timePicker = findViewById(R.id.timePicker);
        tvTime = findViewById(R.id.tvTime);

        calendar = Calendar.getInstance();
        //Truy cap he thong bao dong cua may
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        final Intent intent = new Intent(AddAlarmActivity.this,AlarmReceiver.class);

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar.set(Calendar.HOUR_OF_DAY,timePicker.getHour());
                calendar.set(Calendar.MINUTE,timePicker.getMinute());

                int hour = timePicker.getHour();
                int minute = timePicker.getMinute();
                if (minute < 10) {
                    tvTime.setText(hour + ":" + "0" +minute);
                }
                else  {
                    tvTime.setText(hour + ":" +minute);
                }

                intent.putExtra("Extra","on");

                pendingIntent = PendingIntent.getBroadcast(AddAlarmActivity.this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
                alarmManager.set(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),pendingIntent);
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                tvTime.setText("");
                alarmManager.cancel(pendingIntent);
                intent.putExtra("Extra","off");
                sendBroadcast(intent);

            }
        });
    }
}
