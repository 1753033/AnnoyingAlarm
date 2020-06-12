package com.example.annoyingalarm;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.github.mikephil.charting.charts.CombinedChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SleepHistoryActivity extends AppCompatActivity {
    private boolean is24HView = true;
    private int lastSelectedHour1 = -1;
    private int lastSelectedMinute1 = -1;
    private int lastSelectedHour2 = -1;
    private int lastSelectedMinute2 = -1;
    private TextView edtBedtime, edtWakeUp;
    private Button btnGoToDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        setContentView(R.layout.activity_sleep_history);
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
        btnGoToDetail = findViewById(R.id.btnGoToDetail);
        btnGoToDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(SleepHistoryActivity.this, SleepDetailActivity.class);
                startActivity(intent);
            }
        });
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
                edtBedtime.setText(hourOfDay + ":" + minute );
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
                edtWakeUp.setText(hourOfDay + ":" + minute );
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

}

