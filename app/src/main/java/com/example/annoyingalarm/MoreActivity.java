package com.example.annoyingalarm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class MoreActivity extends AppCompatActivity {

    ImageButton btnWeather,btnNews,btnNight,btnAlarm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);

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
}
