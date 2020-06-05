package com.example.annoyingalarm.alarm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.Ringtone;
import android.net.Uri;
import android.os.Bundle;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.annoyingalarm.R;

public class AlarmScreenShakeActivity extends AppCompatActivity {
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private ShakeDetector mShakeDetector;
    private AudioManager audioManager ;
    private Ringtone ringtone;
    private ImageView img;
    private MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        setContentView(R.layout.activity_alarm_screen_shake);

        String name = getIntent().getStringExtra(AlarmManagerHelper.NAME);
        int timeHour = getIntent().getIntExtra(AlarmManagerHelper.TIME_HOUR, 0);
        int timeMinute = getIntent().getIntExtra(AlarmManagerHelper.TIME_MINUTE, 0);
        int volumn = getIntent().getIntExtra(AlarmManagerHelper.VOL, 7);
        String tone = getIntent().getStringExtra(AlarmManagerHelper.TONE);

        audioManager= (AudioManager) getApplication().getSystemService(AUDIO_SERVICE);

        img=findViewById(R.id.icon_sleep);

        TextView tvName =  findViewById(R.id.tvName);
        tvName.setText(name);

        TextView tvTime =  findViewById(R.id.tvTime);
        tvTime.setText(String.format("%02d : %02d", timeHour, timeMinute));

        final TextView tvShake = findViewById(R.id.tvShake);

        try {
            if (tone != null && !tone.equals("")) {
                Uri toneUri = Uri.parse(tone);
                if (toneUri != null) {
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,volumn,AudioManager.FLAG_PLAY_SOUND);
                    //ringtone = RingtoneManager.getRingtone(this,toneUri);
                    //ringtone.play();
                    mediaPlayer = MediaPlayer.create(getApplicationContext(),toneUri);
                    mediaPlayer.setLooping(true);
                    mediaPlayer.start();
                    Toast.makeText(this,"Ring",Toast.LENGTH_SHORT).show();
                }
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }

        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mShakeDetector = new ShakeDetector();
        mShakeDetector.setOnShakeListener(new ShakeDetector.OnShakeListener() {
            @Override
            public void onShake(int count) {
                if(count==2){
                    img.setImageResource(R.drawable.icon_smile);
                }
                else if(count==3){
                    img.setImageResource(R.drawable.icon_happy);
                    tvShake.setText("Good morning you too");
                    //ringtone.stop();
                    mediaPlayer.stop();
                    finish();
                }
            }
        });
    }
    @Override
    public void onResume() {
        super.onResume();
        // Add the following line to register the Session Manager Listener onResume
        mSensorManager.registerListener(mShakeDetector, mAccelerometer,	SensorManager.SENSOR_DELAY_UI);
    }

    @Override
    public void onPause() {
        // Add the following line to unregister the Sensor Manager onPause
        mSensorManager.unregisterListener(mShakeDetector);
        super.onPause();
    }
}
