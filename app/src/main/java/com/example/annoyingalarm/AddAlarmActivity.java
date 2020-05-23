package com.example.annoyingalarm;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;

import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;


public class AddAlarmActivity extends AppCompatActivity {

    private AlarmObject alarmDetails;
    AlarmDBHelper dbHelper = new AlarmDBHelper(this);
    private TimePicker timePicker;
    private EditText tbName;
    private Button btnDone,btnCancel;
    private RadioButton rbMon,rbTue,rbWed,rbThu,rbFri,rbSat,rbSun;
    private Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        setContentView(R.layout.activity_add_alarm);

        timePicker = findViewById(R.id.timePicker);
        tbName = findViewById(R.id.tbName);
        btnCancel = findViewById(R.id.btnCancel);
        btnDone = findViewById(R.id.btnDone);
        rbSun = findViewById(R.id.rbSunday);
        rbMon = findViewById(R.id.rbMonday);
        rbTue = findViewById(R.id.rbTuesday);
        rbWed = findViewById(R.id.rbWednesday);
        rbThu = findViewById(R.id.rbThursday);
        rbFri = findViewById(R.id.rbFriday);
        rbSat = findViewById(R.id.rbSaturday);
        spinner = findViewById(R.id.spinner);

        timePicker.setIs24HourView(true);
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Default");
        arrayList.add("Math");

        ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,arrayList);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);

        final long id = getIntent().getExtras().getLong("id");
        if(id == -1){
            alarmDetails = new AlarmObject();
        }
        else{
            alarmDetails = dbHelper.getAlarm(id);

            timePicker.setHour(alarmDetails.getTimeHour());
            timePicker.setMinute(alarmDetails.getTimeMinute());
            tbName.setText(alarmDetails.getName());
            rbSun.setChecked(alarmDetails.getRepeatingDay(alarmDetails.SUNDAY));
            rbMon.setChecked(alarmDetails.getRepeatingDay(alarmDetails.MONDAY));
            rbTue.setChecked(alarmDetails.getRepeatingDay(alarmDetails.TUESDAY));
            rbWed.setChecked(alarmDetails.getRepeatingDay(alarmDetails.WEDNESDAY));
            rbThu.setChecked(alarmDetails.getRepeatingDay(alarmDetails.THURSDAY));
            rbFri.setChecked(alarmDetails.getRepeatingDay(alarmDetails.FRIDAY));
            rbSat.setChecked(alarmDetails.getRepeatingDay(alarmDetails.SATURDAY));
            if(alarmDetails.type.equals("Default")){
                spinner.setSelection(0);
            }
            else {
                spinner.setSelection(1);
            }
        }

        View.OnTouchListener radioButtonTouch = new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (((RadioButton) v).isChecked()) {
                    // If the button was already checked, uncheck them all
                    ((RadioButton) v).setChecked(false);
                    // Prevent the system from re-checking it
                    return true;
                }
                return false;
            }
        };
        rbSun.setOnTouchListener(radioButtonTouch);
        rbMon.setOnTouchListener(radioButtonTouch);
        rbTue.setOnTouchListener(radioButtonTouch);
        rbWed.setOnTouchListener(radioButtonTouch);
        rbThu.setOnTouchListener(radioButtonTouch);
        rbFri.setOnTouchListener(radioButtonTouch);
        rbSat.setOnTouchListener(radioButtonTouch);

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateModelFromLayout();
                if(alarmDetails.getId()<0){
                    dbHelper.createAlarm(alarmDetails);
                }
                else {
                    dbHelper.updateAlarm(alarmDetails);
                }
                AlarmManagerHelper.setAlarms(AddAlarmActivity.this);
                setResult(RESULT_OK);
                finish();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }
    private void updateModelFromLayout() {
        alarmDetails.timeMinute = timePicker.getMinute();
        alarmDetails.timeHour = timePicker.getHour();
        alarmDetails.name = tbName.getText().toString();
        alarmDetails.setRepeatingDays(new boolean[]{rbSun.isChecked(),rbMon.isChecked(), rbTue.isChecked(),rbWed.isChecked(),rbThu.isChecked(),rbFri.isChecked(),rbSat.isChecked()});
        if(rbMon.isChecked() && rbTue.isChecked() && rbWed.isChecked() && rbThu.isChecked() && rbFri.isChecked() && rbSat.isChecked() && rbSun.isChecked()){
            alarmDetails.repeatWeekly = true;
        }
        else{
            alarmDetails.repeatWeekly = false;
        }
        alarmDetails.alarmTone = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM);
        alarmDetails.type = spinner.getSelectedItem().toString();
        alarmDetails.isEnabled = true;
    }
}
