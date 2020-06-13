package com.example.annoyingalarm.sleep;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.annoyingalarm.R;
import com.example.annoyingalarm.SleepDetailActivity;

import java.util.ArrayList;

public class SleepListAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<SleepObject> data;
    Activity activity;
    public SleepListAdapter(Context context, ArrayList<SleepObject> data) {
        this.context = context;
        this.data = data;
    }

    public void setData(ArrayList<SleepObject> data) {
        this.data = data;
    }

    @Override
    public int getCount() {
        if(data!=null){
            return data.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if(data!=null){
            return data.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        if(data != null) {
            return data.get(position).getId();
        }
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        convertView = inflater.inflate(R.layout.custom_row_sleep, null);

        TextView edtBedtime, edtWakeUp, edtDate, edtDuration;

        edtBedtime = convertView.findViewById(R.id.edtBedtime);
        edtWakeUp = convertView.findViewById(R.id.edtWakeUp);
        edtDate = convertView.findViewById(R.id.edtDate);
        edtDuration = convertView.findViewById(R.id.edtDuration);

        final SleepObject sleep = (SleepObject)data.get(position);
        edtBedtime.setText(String.format("%02d:%02d ", sleep.getStartToSleepHrs(), sleep.getStartToSleepMinute()));
        edtWakeUp.setText(String.format("%02d:%02d ", sleep.getWakeUpTimeHrs(), sleep.getWakeUpTimeMinute()));
        edtDate.setText(String.format("%02d/%02d/%02d ", sleep.getDay(), sleep.getMonth(), sleep.getYear()));
        edtDuration.setText(String.format("%.2fHrs",sleep.getSleepDuration()));

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((SleepDetailActivity)context).startAddSleepDetail(sleep.getId());
            }
        });

        convertView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //((MainActivity) context).deleteAlarm(obj.id);
                ((SleepDetailActivity)context).deleteSleepDetail(sleep.getId());
                return true;
            }
        });
        return convertView;
    }

}
