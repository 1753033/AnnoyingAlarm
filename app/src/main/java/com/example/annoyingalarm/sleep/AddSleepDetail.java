package com.example.annoyingalarm.sleep;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.annoyingalarm.DBHelper;
import com.example.annoyingalarm.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class AddSleepDetail extends AppCompatActivity {
    private boolean is24HView = true;
    private int lastSelectedHour1 = -1;
    private int lastSelectedMinute1 = -1;
    private int lastSelectedHour2 = -1;
    private int lastSelectedMinute2 = -1;
    private TextView edtBedtime, edtWakeUp, edtDate;
    private int mYear = -1, mMonth = -1, mDay = -1;
    private Button btnDone, btnCancel;
    DBHelper dbHelper = new DBHelper(this);
    private SleepObject sleep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        setContentView(R.layout.activity_add_sleep_detail);

        edtBedtime = findViewById(R.id.edtBedtime);
        edtBedtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSelectTime1();
            }
        });

        edtWakeUp = findViewById(R.id.edtWakeUp);
        edtWakeUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSelectTime2();
            }
        });

        edtDate = findViewById(R.id.edtDate);
        edtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonSelectDate();
            }
        });

        final long id = getIntent().getIntExtra("id", 0);
        if (id == -1) {
            sleep = new SleepObject();
        } else {
            sleep = dbHelper.getSleep(id);
            lastSelectedHour1 = sleep.getStartToSleepHrs();
            lastSelectedMinute1 = sleep.getStartToSleepMinute();
            lastSelectedHour2 = sleep.getWakeUpTimeHrs();
            lastSelectedMinute2 = sleep.getWakeUpTimeMinute();
            mYear = sleep.getYear();
            mMonth = sleep.getMonth() - 1;
            mDay = sleep.getDay();
            edtBedtime.setText(String.format("%02d:%02d ", lastSelectedHour1, lastSelectedMinute1));
            edtWakeUp.setText(String.format("%02d:%02d ", lastSelectedHour2, lastSelectedMinute2));
            edtDate.setText(String.format("%02d/%02d/%02d ", mDay, mMonth + 1, mYear));
        }

        btnDone = findViewById(R.id.btnDone);

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateModelFromLayout();
                if (sleep.getId() < 0) {
                    dbHelper.addSleep(sleep);
                } else {
                    dbHelper.updateSleep(sleep);
                }
                setResult(RESULT_OK);
                finish();
            }
        });

        btnCancel = findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }

    private void updateModelFromLayout() {
        sleep.setDay(mDay);
        sleep.setMonth(mMonth);
        sleep.setYear(mYear);
        sleep.setStartToSleepHrs(lastSelectedHour1);
        sleep.setStartToSleepMinute(lastSelectedMinute1);
        sleep.setWakeUpTimeHrs(lastSelectedHour2);
        sleep.setWakeUpTimeMinute(lastSelectedMinute2);
        sleep.setSleepDuration(countDuration(lastSelectedHour1, lastSelectedHour2, lastSelectedMinute1, lastSelectedMinute2));
    }

    private float countDuration(int hrs1, int hrs2, int minute1, int minute2) {
        float duration = 0;
        String time1 = String.format("%d-%d-%d %d:%d:%d", mYear, mMonth, mDay, hrs1, minute1, 0);
        String time2 = String.format("%d-%d-%d %d:%d:%d", mYear, mMonth, mDay + 1, hrs2, minute2, 0);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date1 = null;
        Date date2 = null;
        try {
            date1 = format.parse(time1);
            date2 = format.parse(time2);
        }
        catch (ParseException e) {

        }

        long difference = date2.getTime() - date1.getTime();
        duration = (float)(difference) / (60 * 60 * 1000);
        return duration;
    }

    private void buttonSelectTime1()  {
        if(this.lastSelectedHour1 == -1)  {
            // Get Current Time
            final Calendar c = Calendar.getInstance();
            this.lastSelectedHour1 = c.get(Calendar.HOUR_OF_DAY);
            this.lastSelectedMinute1 = c.get(Calendar.MINUTE);
        }

        // Time Set Listener.
        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {

            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                edtBedtime.setText(String.format("%02d:%02d ", hourOfDay, minute));
                lastSelectedHour1 = hourOfDay;
                lastSelectedMinute1 = minute;
            }
        };

        // Create TimePickerDialog:
        TimePickerDialog timePickerDialog = null;

        timePickerDialog = new TimePickerDialog(this,
                android.R.style.Theme_Holo_Light_Dialog_NoActionBar,
                timeSetListener, lastSelectedHour1, lastSelectedMinute1, is24HView);

        // Show
        timePickerDialog.show();
    }

    private void buttonSelectTime2()  {
        if(this.lastSelectedHour2 == -1)  {
            // Get Current Time
            final Calendar c = Calendar.getInstance();
            this.lastSelectedHour2 = c.get(Calendar.HOUR_OF_DAY);
            this.lastSelectedMinute2 = c.get(Calendar.MINUTE);
        }

        // Time Set Listener.
        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {

            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                edtWakeUp.setText(String.format("%02d:%02d ", hourOfDay, minute));
                lastSelectedHour2 = hourOfDay;
                lastSelectedMinute2 = minute;
            }
        };

        // Create TimePickerDialog:
        TimePickerDialog timePickerDialog = null;

        timePickerDialog = new TimePickerDialog(this,
                android.R.style.Theme_Holo_Light_Dialog_NoActionBar,
                timeSetListener, lastSelectedHour2, lastSelectedMinute2, is24HView);

        // Show
        timePickerDialog.show();
    }

    private void buttonSelectDate(){
        if(mYear == -1) {
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);
        }

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year,
                                          int monthOfYear, int dayOfMonth) {

                        edtDate.setText(String.format("%02d/%02d/%02d ", dayOfMonth, (monthOfYear + 1), year));
                        mYear = year;
                        mMonth = monthOfYear;
                        mDay = dayOfMonth;
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }
}