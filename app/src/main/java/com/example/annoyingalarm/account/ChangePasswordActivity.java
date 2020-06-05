package com.example.annoyingalarm.account;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.annoyingalarm.MainActivity;
import com.example.annoyingalarm.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ChangePasswordActivity extends AppCompatActivity {
    private Button btnBack, btnChangePwd;
    private GoogleSignInClient mGoogleSignInClient;
    private EditText txtOldPassword, txtNewPassword, txtCofirmPwd;
    private ProgressBar progressBar;
    private FirebaseAuth mAuth;
    FirebaseUser user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        setContentView(R.layout.activity_change_password);

        // Xuly
        mAuth = FirebaseAuth.getInstance();
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);


        txtNewPassword = findViewById(R.id.txtNewPassword);
        txtCofirmPwd = findViewById(R.id.txtConfirmPw);
        progressBar = findViewById(R.id.progress_circular);

        btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btnChangePwd = findViewById(R.id.btnChangePwd);
        btnChangePwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newPwd = txtNewPassword.getText().toString().trim();
                String confirmPwd = txtCofirmPwd.getText().toString().trim();
                user = mAuth.getCurrentUser();
                if (user != null && !newPwd.equals("")) {
                    if (newPwd.length() < 6) {
                        txtNewPassword.setError("Password too short, enter minimum 6 characters");
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                    else if(!newPwd.equals(confirmPwd)) {
                        Toast.makeText(ChangePasswordActivity.this, "New password and confirm password are different!", Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                    else {
                        user.updatePassword(newPwd)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(ChangePasswordActivity.this, "Password is updated, sign in with new password!", Toast.LENGTH_SHORT).show();
                                            signOut();
                                            progressBar.setVisibility(View.INVISIBLE);
                                            Intent intent = new Intent(ChangePasswordActivity.this, MainActivity.class);
                                            startActivity(intent);
                                        } else {
                                            Toast.makeText(ChangePasswordActivity.this, "Failed to update password!", Toast.LENGTH_SHORT).show();
                                            progressBar.setVisibility(View.INVISIBLE);
                                        }
                                    }
                                });
                    }
                } else if (newPwd.equals("")) {
                    txtNewPassword.setError("Enter password");
                    progressBar.setVisibility(View.INVISIBLE);
                }
            }
        });

    }

    public void signOut() {
        mGoogleSignInClient.signOut();
        mAuth.signOut();
    }
}
