package com.example.annoyingalarm.account;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.annoyingalarm.DBHelper;
import com.example.annoyingalarm.sleep.SleepContract;
import com.example.annoyingalarm.sleep.SleepObject;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Firebase {
    private static final String TAG = "DocSnippets";
    private final FirebaseFirestore db;
    private FirebaseAuth mAuth;
    private DBHelper dbHelper;
    Firebase(FirebaseFirestore db) {
        this.db = db;
    }

    Firebase(DBHelper dbHelper) {
        this.db = FirebaseFirestore.getInstance();
        this.mAuth = FirebaseAuth.getInstance();
        this.dbHelper = dbHelper;
    }

    public void createUser() {
        Map<String, Object> user = new HashMap<>();
        user.put("uid", mAuth.getCurrentUser().getUid());

        db.collection("users").document(mAuth.getCurrentUser().getUid())
                .set(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void documentReference) {
                        Log.d(TAG, "DocumentSnapshot successfully written!");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });

    }

    public void syncSleepData() {
        ArrayList<SleepObject> list = dbHelper.getSleep();
        for (SleepObject item:
             list) {
            Map<String, Object> sleep = new HashMap<>();
            sleep.put(SleepContract.Sleep._ID, item.getId());
            sleep.put(SleepContract.Sleep.COLUMN_NAME_SLEEP_STARTTOSLEEPHRS, item.getStartToSleepHrs());
            sleep.put(SleepContract.Sleep.COLUMN_NAME_SLEEP_STARTTOSLEEPMINUTE, item.getStartToSleepMinute());
            sleep.put(SleepContract.Sleep.COLUMN_NAME_SLEEP_WAKEUPTIMEHRS, item.getWakeUpTimeHrs());
            sleep.put(SleepContract.Sleep.COLUMN_NAME_SLEEP_WAKEUPTIMEMINUTE, item.getWakeUpTimeMinute());
            sleep.put(SleepContract.Sleep.COLUMN_NAME_SLEEP_SLEEPDURATION, item.getSleepDuration());
            sleep.put(SleepContract.Sleep.COLUMN_NAME_SLEEP_DAY, item.getDay());
            sleep.put(SleepContract.Sleep.COLUMN_NAME_SLEEP_MONTH, item.getMonth());
            sleep.put(SleepContract.Sleep.COLUMN_NAME_SLEEP_YEAR, item.getYear());
            db.collection("users").document(mAuth.getCurrentUser().getUid()).collection(SleepContract.Sleep.TABLE_NAME)
                    .document(String.format("%d", item.getId()))
                    .set(sleep)
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d(TAG, "DocumentSnapshot successfully written!");
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "Error writing document", e);
                        }
                    });

        }
    }
}
