package com.example.annoyingalarm;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.TimePicker;
import android.widget.Toast;



public class AddAlarmActivity extends AppCompatActivity {

    private AlarmObject alarmDetails;
    private TimePicker timePicker;
    private EditText tbName;
    private Button btnDone,btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_alarm);

        timePicker = findViewById(R.id.timePicker);
        tbName = findViewById(R.id.tbName);
        btnCancel = findViewById(R.id.btnCancel);
        btnDone = findViewById(R.id.btnDone);

        final long id = getIntent().getExtras().getLong("id");
        Toast.makeText(this,String.valueOf(id),Toast.LENGTH_SHORT).show();
        if(id == -1){
            alarmDetails = new AlarmObject();
        }
        else{
            alarmDetails = (AlarmObject) getIntent().getSerializableExtra("Alarm");
            timePicker.setHour(alarmDetails.getTimeHour());
            timePicker.setMinute(alarmDetails.getTimeMinute());
            tbName.setText(alarmDetails.getName());
        }

        btnDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateModelFromLayout();
                Intent newAlarm = new Intent();
                newAlarm.putExtra("NewAlarm",alarmDetails);
                setResult(RESULT_OK,newAlarm);
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

        alarmDetails.isEnabled = true;
    }
}
