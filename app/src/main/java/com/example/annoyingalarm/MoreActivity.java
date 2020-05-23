package com.example.annoyingalarm;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

public class MoreActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_LOGIN = 0x9345;
    private ImageButton btnWeather,btnNews,btnNight,btnAlarm;
    private Button btnSetting, btnTodo;
    private Button btnAccountInfo, btnNotification;
    private TextView txtName, txtEmail;
    private int FLAG_LOGIN = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        setContentView(R.layout.activity_more);

        btnTodo = findViewById(R.id.btnTodoList);
        btnTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openTodo = new Intent(MoreActivity.this,TodoListActivity.class);
                startActivity(openTodo);
            }
        });

        btnAccountInfo = (Button) findViewById(R.id.btnAccountInfo);
        btnAccountInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(FLAG_LOGIN == 0) {
                    final Intent openLogin = new Intent(MoreActivity.this, LoginActivity.class);
                    startActivityForResult(openLogin, REQUEST_CODE_LOGIN);
                }
                else {
                    final Intent openAcountInfo = new Intent(MoreActivity.this, AccountInfoActivity.class);
                    startActivity(openAcountInfo);
                }
            }
        });

        txtName = findViewById(R.id.txtName);
        txtEmail = findViewById(R.id.txtEmail);

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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Kiểm tra requestCode
        if(requestCode == REQUEST_CODE_LOGIN) {

            // resultCode được set bởi LoginActivity
            // RESULT_OK chỉ ra rằng kết quả này đã thành công
            if(resultCode == Activity.RESULT_OK) {
                // Nhận dữ liệu từ Intent trả về
                final String[] result = data.getStringArrayExtra(LoginActivity.EXTRA_DATA);
                txtName.setText(result[0]);
                txtEmail.setText(result[1]);
                txtEmail.setVisibility(View.VISIBLE);
                FLAG_LOGIN = 1;
                // Sử dụng kết quả result bằng cách hiện Toast
                Toast.makeText(this, "Result: " + result[0], Toast.LENGTH_LONG).show();

            } else {
                // LoginActivity không thành công, không có data trả về.
                Toast.makeText(this, "No login", Toast.LENGTH_LONG).show();
            }
        }
    }
}
