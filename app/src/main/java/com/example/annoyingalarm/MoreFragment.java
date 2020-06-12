package com.example.annoyingalarm;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.graphics.drawable.RoundedBitmapDrawable;
import androidx.core.graphics.drawable.RoundedBitmapDrawableFactory;
import androidx.fragment.app.Fragment;

import com.example.annoyingalarm.account.AccountInfoActivity;
import com.example.annoyingalarm.account.LoginActivity;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

public class MoreFragment extends Fragment {
    private static final int REQUEST_CODE_LOGIN = 0x9345;
    private static final String TAG = "More fragment";
    private Button btnAccountInfo,btnSleepHistory,btnSetting, btnTodo, btnTheme;
    private TextView txtName, txtEmail;
    private ImageView avatar;
    private FirebaseAuth mAuth;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_more, container, false);


        avatar = view.findViewById(R.id.avatar);
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

        btnTheme = view.findViewById(R.id.btnTheme);
        btnTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_NO) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                }
                else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                }
                //startActivity(new Intent(getContext(),MainActivity.class));
                onCreateView(inflater, container, savedInstanceState);

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
        if(resultCode != Activity.RESULT_CANCELED)
            updateUI();
    }

    private void updateUI() {
        FirebaseUser firebaseUser = mAuth.getCurrentUser();
        txtName.setText(firebaseUser.getDisplayName());
        Log.w(TAG, "ImageURI: " + firebaseUser.getPhotoUrl());
        txtEmail.setText(firebaseUser.getEmail());
        txtEmail.setVisibility(View.VISIBLE);
        Picasso.with(getContext()).load(firebaseUser.getPhotoUrl())
                .into(avatar, new Callback() {
                    @Override
                    public void onSuccess() {
                        Bitmap imageBitmap = ((BitmapDrawable) avatar.getDrawable()).getBitmap();
                        RoundedBitmapDrawable imageDrawable = RoundedBitmapDrawableFactory.create(getResources(), imageBitmap);
                        imageDrawable.setCircular(true);
                        imageDrawable.setCornerRadius(Math.max(imageBitmap.getWidth(), imageBitmap.getHeight()) / 2.0f);
                        avatar.setImageDrawable(imageDrawable);
                    }
                    @Override
                    public void onError() {
                        avatar.setImageResource(R.drawable.ic_logo_user);
                    }
                });
    }
}
