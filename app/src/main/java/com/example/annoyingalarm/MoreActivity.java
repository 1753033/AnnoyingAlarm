package com.example.annoyingalarm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class MoreActivity extends AppCompatActivity {

    ImageButton btnWeather,btnNews,btnNight,btnAlarm;
    Button btnSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);

        btnSetting = findViewById(R.id.btnSetting);
        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openSettings = new Intent(MoreActivity.this,SettingsActivity.class);
                startActivity(openSettings);
            }
        });

        btnWeather = findViewById(R.id.btnWeather);
        btnWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent switchToWeather = new Intent(MoreActivity.this,WeatherActivity.class);
                startActivity(switchToWeather);
            }
        });
        btnNews = findViewById(R.id.btnNews);
        btnNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent switchToNews = new Intent(MoreActivity.this,NewsActivity.class);
                startActivity(switchToNews);
            }
        });
        btnNight = findViewById(R.id.btnNight);
        btnNight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent switchToNight = new Intent(MoreActivity.this,SleepHistoryActivity.class);
                startActivity(switchToNight);
            }
        });
        btnAlarm = findViewById(R.id.btnAlarm);
        btnAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent switchToAlarm = new Intent(MoreActivity.this,MainActivity.class);
                startActivity(switchToAlarm);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        LinearLayout curScr = findViewById(R.id.more_layout);

        SharedPreferences myPrefContainer = getSharedPreferences("Settings", Context.MODE_PRIVATE);
        String key = "Background";

        if ((myPrefContainer != null) && myPrefContainer.contains(key)) {
            String color = myPrefContainer.getString(key, "1");

            if (color == "0xFFFFFF")
                curScr.setBackgroundColor(Color.WHITE);
            if (color == "0x000000")
                curScr.setBackgroundColor(Color.BLACK);
        }
    }
}
