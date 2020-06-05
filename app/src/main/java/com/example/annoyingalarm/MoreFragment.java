package com.example.annoyingalarm;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;

import com.example.annoyingalarm.account.AccountInfoActivity;
import com.example.annoyingalarm.account.LoginActivity;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MoreFragment extends Fragment {
    private static final int REQUEST_CODE_LOGIN = 0x9345;
    private ImageButton btnWeather,btnNews,btnNight,btnAlarm;
    private Button btnSetting, btnTodo;
    private Button btnAccountInfo, btnNotification,btnSleepHistory;
    private TextView txtName, txtEmail;
    private ImageView avatar;
    private FirebaseAuth mAuth;
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

        mAuth = FirebaseAuth.getInstance();

        btnAccountInfo = view.findViewById(R.id.btnAccountInfo);
        btnAccountInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mAuth.getCurrentUser() == null) {
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

        btnSetting = view.findViewById(R.id.btnSetting);
        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent openSettings = new Intent(getContext(),SettingsActivity.class);
                startActivity(openSettings);
            }
        });

        // update ui
        txtName = view.findViewById(R.id.txtName);
        txtEmail = view.findViewById(R.id.txtEmail);
        avatar = view.findViewById(R.id.avatar);

        txtName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mAuth.getCurrentUser() == null) {
                    final Intent openLogin = new Intent(getContext(), LoginActivity.class);
                    startActivityForResult(openLogin, REQUEST_CODE_LOGIN);
                }
                else {
                    final Intent openAccountInfo = new Intent(getContext(), AccountInfoActivity.class);
                    startActivity(openAccountInfo);
                }
            }
        });

        if(mAuth.getCurrentUser() != null)
            updateUI();
        else {
            txtEmail.setVisibility(View.INVISIBLE);
            txtName.setText("Login");
        }

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        updateUI();
    }

    private void updateUI() {
        FirebaseUser firebaseUser = mAuth.getCurrentUser();
        txtName.setText(firebaseUser.getDisplayName());
        txtEmail.setText(firebaseUser.getEmail());
        txtEmail.setVisibility(View.VISIBLE);
        avatar.setImageURI(firebaseUser.getPhotoUrl());
    }
}
