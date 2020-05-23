package com.example.annoyingalarm;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class AlarmScreenMathActivity extends AppCompatActivity {
    private Ringtone ringtone;
    private Button btn0,btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8,btn9,btnCheck,btnClear;
    private TextView tvName,tvTime,tvFunction,tvResult;
    private String result="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        setContentView(R.layout.activity_alarm_screen_math);
        final long id = getIntent().getIntExtra(AlarmManagerHelper.ID,-1);
        String name = getIntent().getStringExtra(AlarmManagerHelper.NAME);
        int timeHour = getIntent().getIntExtra(AlarmManagerHelper.TIME_HOUR, 0);
        int timeMinute = getIntent().getIntExtra(AlarmManagerHelper.TIME_MINUTE, 0);
        final String tone = getIntent().getStringExtra(AlarmManagerHelper.TONE);
        final boolean once = getIntent().getBooleanExtra(AlarmManagerHelper.ONCE,true);


        tvName =  findViewById(R.id.alarm_screen_name);
        tvTime =  findViewById(R.id.alarm_screen_time);

        tvName.setText(name);
        tvTime.setText(String.format("%02d : %02d", timeHour, timeMinute));

        tvFunction =findViewById(R.id.tvFunction);
        tvResult = findViewById(R.id.tvResult);
        tvFunction.setText("");
        tvResult.setText("");

        btn0 = findViewById(R.id.btn0);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        btn5 = findViewById(R.id.btn5);
        btn6 = findViewById(R.id.btn6);
        btn7 = findViewById(R.id.btn7);
        btn8 = findViewById(R.id.btn8);
        btn9 = findViewById(R.id.btn9);

        View.OnClickListener btnClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button temp = (Button)v;
                result += temp.getText().toString();
                tvResult.setText(result);
            }
        };

        btn0.setOnClickListener(btnClick);
        btn1.setOnClickListener(btnClick);
        btn2.setOnClickListener(btnClick);
        btn3.setOnClickListener(btnClick);
        btn4.setOnClickListener(btnClick);
        btn5.setOnClickListener(btnClick);
        btn6.setOnClickListener(btnClick);
        btn7.setOnClickListener(btnClick);
        btn8.setOnClickListener(btnClick);
        btn9.setOnClickListener(btnClick);

        Random generator = new Random();

        final int y = generator.nextInt(10)+1;
        final int x = generator.nextInt(10)+y;
        final int value = generator.nextInt(2);

        tvFunction.setText(stringFunction(value,x,y));

        btnClear = findViewById(R.id.btnClear);
        btnCheck = findViewById(R.id.btnCheck);

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result = "";
                tvResult.setText(result);
            }
        });

        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int resultInt = Integer.valueOf(result);
                Toast.makeText(AlarmScreenMathActivity.this,result,Toast.LENGTH_SHORT).show();
                if(checkResult(value,x,y,resultInt)) {
                    ringtone.stop();
                    finish();
                }
                else {
                    result = "";
                    tvResult.setText(result);
                }
            }
        });

        try {
            if (tone != null && !tone.equals("")) {
                Uri toneUri = Uri.parse(tone);
                if (toneUri != null) {
                    ringtone = RingtoneManager.getRingtone(this,toneUri);
                    ringtone.play();
                    Toast.makeText(this,"Ring",Toast.LENGTH_SHORT).show();
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }
    boolean checkResult(int value,int x, int y,int resultInt){
        switch (value){
            case 0:
                if(x+y==resultInt)
                    return true;
            case 1:
                if(x-y==resultInt)
                    return true;
            default:
                return false;
        }
    }
    String stringFunction(int value,int x,int y){
        String function = "";

        function += String.valueOf(x);
        switch (value){
            case 0:
                function += " + ";
                break;
            case 1:
                function += " - ";
                break;
        }
        function += String.valueOf(y);
        function += " = ";

        return function;
    }
}
