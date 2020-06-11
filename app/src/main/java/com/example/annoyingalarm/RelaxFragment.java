package com.example.annoyingalarm;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.NumberPicker;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import java.util.Locale;

import static com.example.annoyingalarm.R.raw.nature;
import static com.example.annoyingalarm.R.raw.rain;
import static com.example.annoyingalarm.R.raw.thunder;
import static com.example.annoyingalarm.R.raw.water;

public class RelaxFragment extends Fragment {
    private CountDownTimer countDownTimer;
    private boolean isTimerRunning;
    private long timeLeftInMillis = 0;
    private long startTimeInMillis = 0;

    private TextView tvShow;
    private ImageButton btnStart, btnReset, btnRain, btnNature, btnThunder, btnWater;
    private ProgressBar progressBar;
    private NumberPicker numberPicker;

    private MediaPlayer mediaPlayer;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_relax, container, false);



        tvShow = view.findViewById(R.id.tvshow);
        btnStart = view.findViewById(R.id.btnStart);
        btnReset = view.findViewById(R.id.btnReset);
        progressBar = view.findViewById(R.id.progressBar);
        numberPicker = view.findViewById(R.id.numberPicker);
        numberPicker.setMinValue(0);
        numberPicker.setMaxValue(59);
        numberPicker.setWrapSelectorWheel(true);

        btnRain = view.findViewById(R.id.btRain);
        btnNature = view.findViewById(R.id.btNature);
        btnThunder = view.findViewById(R.id.btThunder);
        btnWater = view.findViewById(R.id.btWater);

        btnStart.setEnabled(false);
        btnReset.setEnabled(false);
        btnReset.setVisibility(View.INVISIBLE);



        numberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                startTimeInMillis = newVal * 60 * 1000;
                timeLeftInMillis = startTimeInMillis;
                progressBar.setMax((int) (startTimeInMillis/1000));
                progressBar.setProgress((int) (startTimeInMillis/1000));
                if(newVal!=0) {
                    btnStart.setEnabled(true);
                    btnReset.setEnabled(true);
                }
                else {
                    btnStart.setEnabled(false);
                    btnReset.setEnabled(false);
                }
            }
        });

        mediaPlayer = MediaPlayer.create(getContext(),nature);
        mediaPlayer.setLooping(true);

        btnNature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer = MediaPlayer.create(getContext(), nature);
                mediaPlayer.setLooping(true);
                Toast.makeText(getContext(),"You choose Nature sound",Toast.LENGTH_SHORT).show();
            }
        });
        btnRain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer = MediaPlayer.create(getContext(), rain);
                mediaPlayer.setLooping(true);
                Toast.makeText(getContext(),"You choose Rain sound",Toast.LENGTH_SHORT).show();
            }
        });

        btnThunder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer = MediaPlayer.create(getContext(), thunder);
                mediaPlayer.setLooping(true);
                Toast.makeText(getContext(),"You choose Thunder sound",Toast.LENGTH_SHORT).show();
            }
        });

        btnWater.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer = MediaPlayer.create(getContext(), water);
                mediaPlayer.setLooping(true);
                Toast.makeText(getContext(),"You choose Water sound",Toast.LENGTH_SHORT).show();
            }
        });

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                if (isTimerRunning) {
                    pauseTimer();
                } else {
                    startTimer();
                }
                numberPicker.setVisibility(View.INVISIBLE);
                numberPicker.setEnabled(false);
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                resetTimer();
            }
        });

        updateUICountDown();

        return view;
    }
    private void startTimer(){
        mediaPlayer.start();
        btnWater.setVisibility(View.INVISIBLE);
        btnRain.setVisibility(View.INVISIBLE);
        btnThunder.setVisibility(View.INVISIBLE);
        btnNature.setVisibility(View.INVISIBLE);
        countDownTimer = new CountDownTimer(timeLeftInMillis,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMillis = millisUntilFinished;
                updateUICountDown();
                int progress = (int) (millisUntilFinished/1000);
                progressBar.setProgress(progress);
            }

            @Override
            public void onFinish() {
                isTimerRunning=false;

                btnStart.setBackgroundResource(R.drawable.icons_play);
                btnStart.setVisibility(View.INVISIBLE);
                btnReset.setVisibility(View.VISIBLE);
                progressBar.setProgress(0);
                if (mediaPlayer.isPlaying())
                    mediaPlayer.stop();
            }
        }.start();
        isTimerRunning = true;

        btnStart.setBackgroundResource(R.drawable.icons_pause);
        btnReset.setVisibility(View.INVISIBLE);
    }
    private void pauseTimer(){
        countDownTimer.cancel();
        isTimerRunning = false;

        btnStart.setBackgroundResource(R.drawable.icons_play);
        btnReset.setVisibility(View.VISIBLE);
        mediaPlayer.pause();
    }
    private void resetTimer(){
        timeLeftInMillis = startTimeInMillis;
        updateUICountDown();
        btnReset.setVisibility(View.INVISIBLE);
        btnStart.setVisibility(View.VISIBLE);
        mediaPlayer.stop();
        mediaPlayer = MediaPlayer.create(getContext(),rain);
        mediaPlayer.setLooping(true);
        progressBar.setProgress((int) (startTimeInMillis/100));
        btnWater.setVisibility(View.VISIBLE);
        btnRain.setVisibility(View.VISIBLE);
        btnThunder.setVisibility(View.VISIBLE);
        btnNature.setVisibility(View.VISIBLE);
        numberPicker.setVisibility(View.VISIBLE);
        numberPicker.setEnabled(true);
    }
    private void updateUICountDown(){
        int minutes = (int) (timeLeftInMillis / 1000)  / 60;
        int seconds = (int) (timeLeftInMillis / 1000)  % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(),"%02d:%02d",minutes,seconds);
        tvShow.setText(timeLeftFormatted);
    }
}
