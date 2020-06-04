package com.example.annoyingalarm;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MoreFragment extends Fragment {
    private static final int REQUEST_CODE_LOGIN = 0x9345;
    private ImageButton btnWeather,btnNews,btnNight,btnAlarm;
    private Button btnSetting, btnTodo;
    private Button btnAccountInfo, btnNotification,btnSleepHistory;
    private TextView txtName, txtEmail;
    private int FLAG_LOGIN = 0;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_more, container, false);
        btnTodo = view.findViewById(R.id.btnTodoList);
        btnTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openTodo = new Intent(getContext(),TodoListActivity.class);
                startActivity(openTodo);
            }
        });

        btnAccountInfo = view.findViewById(R.id.btnAccountInfo);
        btnAccountInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(FLAG_LOGIN == 0) {
                    final Intent openLogin = new Intent(getContext(), LoginActivity.class);
                    startActivityForResult(openLogin, REQUEST_CODE_LOGIN);
                }
                else {
                    final Intent openAccountInfo = new Intent(getContext(), AccountInfoActivity.class);
                    startActivity(openAccountInfo);
                }
            }
        });

        btnSleepHistory = view.findViewById(R.id.btnSleepHistory);
        btnSleepHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openSleepHistory = new Intent(getContext(),SleepHistoryActivity.class);
                startActivity(openSleepHistory);
            }
        });

        txtName = view.findViewById(R.id.txtName);
        txtEmail = view.findViewById(R.id.txtEmail);

        btnSetting = view.findViewById(R.id.btnSetting);
        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openSettings = new Intent(getContext(),SettingsActivity.class);
                startActivity(openSettings);
            }
        });
        return view;
    }
}
