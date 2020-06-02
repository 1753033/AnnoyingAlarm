package com.example.annoyingalarm;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ListView;


public class MainActivity extends AppCompatActivity {
    final int Code_Settings = 123001;
    ImageButton btnAddAlarm,btnWeather,btnNews,btnNight,btnMore;
    ListView listViewAlarm;
    AlarmListAdapter adapter;
    DBHelper dbHelper = new DBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        setContentView(R.layout.activity_main);


        listViewAlarm = findViewById(R.id.listViewAlarm);
        adapter = new AlarmListAdapter(MainActivity.this,dbHelper.getAlarms());
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
                Intent switchToNight = new Intent(MainActivity.this,RelaxActivity.class);
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
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            adapter.setAlarms(dbHelper.getAlarms());
            adapter.notifyDataSetChanged();
        }
    }
    public void startAlarmDetailsActivity(long id) {
        Intent intent = new Intent(MainActivity.this, AddAlarmActivity.class);
        intent.putExtra("id", id);
        startActivityForResult(intent, 0);
    }

    public void startSettingsActivity() {
        Intent intent = new Intent(MainActivity.this, SettingsActivity.class);

        startActivityForResult(intent, Code_Settings);
    }

    public void deleteAlarm(long id) {
        final long alarmId = id;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Do you want to delete "+ dbHelper.getAlarm(alarmId).getName() +" ?")
                .setCancelable(true)
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dbHelper.deleteAlarm(alarmId);
                        adapter.setAlarms(dbHelper.getAlarms());
                        adapter.notifyDataSetChanged();
                    }
                }).show();
    }
    public void setAlarmEnabled(long id, boolean isEnabled) {
        AlarmManagerHelper.cancelAlarms(this);

        AlarmObject obj = dbHelper.getAlarm(id);
        obj.isEnabled = isEnabled;
        dbHelper.updateAlarm(obj);

        adapter.setAlarms(dbHelper.getAlarms());
        adapter.notifyDataSetChanged();

        AlarmManagerHelper.setAlarms(this);
    }
}
