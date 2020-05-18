package com.example.annoyingalarm;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.RadioButton;
import android.widget.TimePicker;
import android.widget.Toast;



public class AddAlarmActivity extends AppCompatActivity {

    private AlarmObject alarmDetails;
    AlarmDBHelper dbHelper = new AlarmDBHelper(this);
    private TimePicker timePicker;
    private EditText tbName;
    private Button btnDone,btnCancel;
    private RadioButton rbMon,rbTue,rbWed,rbThu,rbFri,rbSat,rbSun;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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


        final long id = getIntent().getExtras().getLong("id");
        Toast.makeText(this,String.valueOf(id),Toast.LENGTH_SHORT).show();
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
        }

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
                //AlarmManagerHelper.setAlarms(this);
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
        alarmDetails.isEnabled = true;
    }
}
