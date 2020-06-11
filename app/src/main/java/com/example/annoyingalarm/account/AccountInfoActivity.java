package com.example.annoyingalarm.account;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.annoyingalarm.MainActivity;
import com.example.annoyingalarm.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AccountInfoActivity extends AppCompatActivity {
    Button btnLogout, btnChangePwd;
    private FirebaseAuth mAuth;
    private GoogleSignInClient mGoogleSignInClient;
    private TextView txtUsername, txtEmail, txtName, txtEmail2;
    private ImageView tvAvatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        setContentView(R.layout.activity_account_info);

        mAuth = FirebaseAuth.getInstance();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        btnLogout = findViewById(R.id.btnLogout);

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
                Intent intent = new Intent(AccountInfoActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // UpdateUI
        txtEmail = findViewById(R.id.txtEmail);
        txtEmail2 = findViewById(R.id.txtEmail2);
        txtName = findViewById(R.id.txtName);
        txtUsername = findViewById(R.id.txtUsername);
        tvAvatar = findViewById(R.id.tvAvatar);
        if(mAuth.getCurrentUser() != null)
            updateUI();
        // getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // getSupportActionBar().setHomeButtonEnabled(true);

        btnChangePwd = findViewById(R.id.btnChangePwd);
        btnChangePwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AccountInfoActivity.this, ChangePasswordActivity.class);
                startActivity(intent);
            }
        });
        if(AppCompatDelegate.getDefaultNightMode()==AppCompatDelegate.MODE_NIGHT_NO) {
            ViewGroup viewGroup = (ViewGroup) ((ViewGroup) (findViewById(android.R.id.content))).getChildAt(0);
            viewGroup.setBackgroundResource(R.drawable.background);
            btnLogout.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_logout,0,0,0);
            btnChangePwd.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_logout,0,0,0);

        }
        else {
            ViewGroup viewGroup = (ViewGroup) ((ViewGroup) (findViewById(android.R.id.content))).getChildAt(0);
            viewGroup.setBackgroundResource(R.drawable.background_dark);
            btnLogout.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_logout_dark,0,0,0);
            btnChangePwd.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_logout_dark,0,0,0);
        }
    }

    private void updateUI() {
        FirebaseUser account = mAuth.getCurrentUser();
        txtUsername.setText(account.getDisplayName());
        txtName.setText("    Name: " + account.getDisplayName());
        txtEmail.setText(account.getEmail());
        txtEmail2.setText("    Email: " + account.getEmail());
        tvAvatar.setImageURI(account.getPhotoUrl());
    }

    private void signOut() {
        // Firebase sign out
        mAuth.signOut();

        // Google sign out
        mGoogleSignInClient.signOut().addOnCompleteListener(this,
                new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        //updateUI(null);
                    }
                });
    }

}
