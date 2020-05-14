package com.example.annoyingalarm;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    ImageButton btnAddAlarm,btnWeather,btnNews,btnNight,btnMore;
    ListView listViewAlarm;
    AlarmListAdapter adapter;
    //AlarmDBHelper dbHelper = new AlarmDBHelper(this);
    static ArrayList<AlarmObject> listAlarm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(listAlarm==null) {
            listAlarm = new ArrayList<>();
            listAlarm.add(new AlarmObject(listAlarm.size()));
        }
        listViewAlarm = findViewById(R.id.listViewAlarm);
        adapter = new AlarmListAdapter(MainActivity.this,listAlarm);
        listViewAlarm.setAdapter(adapter);

        btnAddAlarm = findViewById(R.id.btnAddAlarm);

        btnAddAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startAlarmDetailsActivity(-1);
            }
        });

        btnWeather = findViewById(R.id.btnWeather);
        btnWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent switchToWeather = new Intent(MainActivity.this,WeatherActivity.class);
                startActivity(switchToWeather);
            }
        });
        btnNews = findViewById(R.id.btnNews);
        btnNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent switchToNews = new Intent(MainActivity.this,NewsActivity.class);
                startActivity(switchToNews);
            }
        });
        btnNight = findViewById(R.id.btnNight);
        btnNight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent switchToNight = new Intent(MainActivity.this,SleepHistoryActivity.class);
                startActivity(switchToNight);
            }
        });
        btnMore = findViewById(R.id.btnMore);
        btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent switchToMore = new Intent(MainActivity.this,MoreActivity.class);
                startActivity(switchToMore);
            }
        });
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 0) {
            if (resultCode == RESULT_OK) {
                AlarmObject newAlarm = (AlarmObject) data.getSerializableExtra("NewAlarm");
                newAlarm.id = listAlarm.size();
                listAlarm.add(newAlarm);
                adapter.notifyDataSetChanged();
            }
        }
    }
    public void startAlarmDetailsActivity(long id) {
        Intent intent = new Intent(MainActivity.this, AddAlarmActivity.class);
        intent.putExtra("id", id);
        startActivityForResult(intent, 0);
    }
}
