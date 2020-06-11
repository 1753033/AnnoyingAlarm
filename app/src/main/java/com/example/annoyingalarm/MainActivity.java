package com.example.annoyingalarm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.annoyingalarm.alarm.AlarmFragment;


public class MainActivity extends AppCompatActivity {
    final int Code_Settings = 123001;
    ImageButton btnAlarm,btnWeather,btnNews,btnNight,btnMore;
    TextView tvAlarm,tvWeather,tvNews,tvNight,tvMore;
    DBHelper dbHelper = new DBHelper(this);
    AlarmFragment alarmFragment = new AlarmFragment(dbHelper);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        setContentView(R.layout.activity_main);


        tvAlarm = findViewById(R.id.tvAlarm);
        tvNews = findViewById(R.id.tvNews);
        tvNight = findViewById(R.id.tvSleep);
        tvWeather = findViewById(R.id.tvWeather);
        tvMore = findViewById(R.id.tvMore);

        btnAlarm = findViewById(R.id.btnAlarm);
        btnAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                replaceFragmentContent(alarmFragment);
                highlightText(tvAlarm);
            }
        });

        btnWeather = findViewById(R.id.btnWeather);
        btnWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent switchToWeather = new Intent(MainActivity.this,WeatherActivity.class);
                //startActivity(switchToWeather);
                replaceFragmentContent(new WeatherFragment());
                highlightText(tvWeather);
            }
        });
        btnNews = findViewById(R.id.btnNews);
        btnNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent switchToNews = new Intent(MainActivity.this,NewsActivity.class);
                //startActivity(switchToNews);
                replaceFragmentContent(new NewsFragment());
                highlightText(tvNews);
            }
        });
        btnNight = findViewById(R.id.btnNight);
        btnNight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent switchToNight = new Intent(MainActivity.this,RelaxActivity.class);
                //startActivity(switchToNight);
                replaceFragmentContent(new RelaxFragment());
                highlightText(tvNight);
            }
        });
        btnMore = findViewById(R.id.btnMore);
        btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent switchToMore = new Intent(MainActivity.this,MoreActivity.class);
                //startActivity(switchToMore);
                replaceFragmentContent(new MoreFragment());
                highlightText(tvMore);
            }
        });


        initAlarmFragment();
        highlightText(tvAlarm);

    }

    private void initAlarmFragment() {

        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentTransaction ft = fragmentManager.beginTransaction();

        ft.replace(R.id.fragment, alarmFragment);

        ft.commit();

    }

    protected void replaceFragmentContent(Fragment fragment) {

        if (fragment != null) {

            FragmentManager fmgr = getSupportFragmentManager();

            FragmentTransaction ft = fmgr.beginTransaction();

            ft.replace(R.id.fragment, fragment);

            ft.commit();

        }

    }
    void highlightText(TextView tv){
        tvWeather.setTextColor(Color.parseColor("#718792"));
        tvNight.setTextColor(Color.parseColor("#718792"));
        tvAlarm.setTextColor(Color.parseColor("#718792"));
        tvMore.setTextColor(Color.parseColor("#718792"));
        tvNews.setTextColor(Color.parseColor("#718792"));
        tv.setTextColor(getColor(R.color.colorText));
    }
    /*@Override
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
    }*/

  /*  public void startSettingsActivity() {
        Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
        startActivityForResult(intent, Code_Settings);
    }*/

    /*public void deleteAlarm(long id) {
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
    }*/
}
