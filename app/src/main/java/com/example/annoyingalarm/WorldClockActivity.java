package com.example.annoyingalarm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class WorldClockActivity extends AppCompatActivity {
    Calendar calendar;
    ListView listViewClock;
    Spinner spinnerWorldClock;
    String [] timeZoneId;
    WorldClockAdapter worldClockAdapter;
    ArrayAdapter<String> timeZoneAdapter;
    ArrayList<WorldClock> worldClockList;
    SimpleDateFormat sdf;
    long milisecond;
    Date date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        setContentView(R.layout.activity_world_clock);

        listViewClock = findViewById(R.id.listViewClock);
        spinnerWorldClock = findViewById(R.id.spinnerWorldClock);

        if(worldClockList==null){
            worldClockList = new ArrayList<>();
        }

        timeZoneId = TimeZone.getAvailableIDs();

        timeZoneAdapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_item,timeZoneId);
        worldClockAdapter = new WorldClockAdapter(this,worldClockList);
        timeZoneAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerWorldClock.setAdapter(timeZoneAdapter);
        listViewClock.setAdapter(worldClockAdapter);


        spinnerWorldClock.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                getGMTtime();
                String selectedID = (String) (parent.getItemAtPosition(position));
                WorldClock worldClock = new WorldClock();
                worldClock.timeZone = TimeZone.getTimeZone(selectedID).getDisplayName();

                date = new Date();
                DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                dateFormat.setTimeZone(TimeZone.getTimeZone(selectedID));

                worldClock.city = dateFormat.format(date);

                worldClockList.add(worldClock);
                worldClockAdapter.notifyDataSetChanged();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }
    private void getGMTtime(){
        calendar = Calendar.getInstance();

        milisecond = calendar.getTimeInMillis();

        TimeZone tzCurrent = calendar.getTimeZone();

        int offset = tzCurrent.getRawOffset();
        if(tzCurrent.inDaylightTime(new Date())){
            offset += tzCurrent.getDSTSavings();
        }
        milisecond+=offset;
        date = new Date(milisecond);
    }
}
