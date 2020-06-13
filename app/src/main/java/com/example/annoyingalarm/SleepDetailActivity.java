package com.example.annoyingalarm;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;

import com.example.annoyingalarm.sleep.AddSleepDetail;
import com.example.annoyingalarm.sleep.SleepListAdapter;

import java.util.Calendar;

public class SleepDetailActivity extends AppCompatActivity {
    private ImageButton btnAddSleep;
    private static final int REQUEST_CODE_SLEEP = 0x9346;
    private ListView lViewDetail;
    SleepListAdapter adapter;
    DBHelper dbHelper = new DBHelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        setContentView(R.layout.activity_sleep_detail);

        btnAddSleep = findViewById(R.id.btnAddSleep);
        btnAddSleep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(SleepDetailActivity.this, AddSleepDetail.class);
                // quy dinh khi add moi id la -1
                intent.putExtra("id", -1);
                startActivityForResult(intent, REQUEST_CODE_SLEEP);
            }
        });

        lViewDetail = findViewById(R.id.lViewDetail);
        adapter = new SleepListAdapter(this, dbHelper.getSleep());
        lViewDetail.setAdapter(adapter);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode != Activity.RESULT_CANCELED)
        {
            adapter.setData(dbHelper.getSleep());
            adapter.notifyDataSetChanged();
        }
    }

    public void startAddSleepDetail(int code) {
        final Intent intent = new Intent(SleepDetailActivity.this, AddSleepDetail.class);
        // quy dinh khi add moi id la -1
        intent.putExtra("id", code);
        startActivityForResult(intent, REQUEST_CODE_SLEEP);
    }

    public void deleteSleepDetail(int code) {
        final long id = code;
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Do you want to delete ?")
                .setCancelable(true)
                .setNegativeButton("Cancel", null)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dbHelper.deleteSleep(id);
                        adapter.setData(dbHelper.getSleep());
                        adapter.notifyDataSetChanged();
                    }
                }).show();
    }
}
