package com.example.annoyingalarm;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;

public class AlarmListAdapter extends BaseAdapter {
    private ArrayList<AlarmObject> list;
    private Context context;
    private String[] day = {"Su ","M ","Tu ","W ","Th ", "F ","Sa"};

    public AlarmListAdapter(Context context, ArrayList<AlarmObject> list) {
        this.list = list;
        this.context = context;
    }

    public void setAlarms(ArrayList<AlarmObject> list) {
        this.list = list;
    }

    @Override
    public int getCount() {
        if(list != null) {
            return list.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if(list!=null){
            return list.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        if(list != null) {
            return list.get(position).getId();
        }
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View row = inflater.inflate(R.layout.alarm_list_item,null);

        TextView tvTime = row.findViewById(R.id.tvTime);
        TextView tvName = row.findViewById(R.id.tvName);
        Switch btnToggle = row.findViewById(R.id.btnToggle);
        TextView tvRepeat = row.findViewById(R.id.tvRepeat);
        TextView tvType = row.findViewById(R.id.tvType);

        final AlarmObject obj = (AlarmObject) getItem(position);
        tvName.setText(obj.getName());
        tvTime.setText(String.format("%02d:%02d", obj.timeHour, obj.timeMinute));
        btnToggle.setChecked(obj.isEnabled);
        tvRepeat.setText("");
        btnToggle.setChecked(obj.isEnabled);
        tvType.setText(obj.type);
        for(int i = 0;i<obj.repeatingDays.length;i++){
            if (obj.repeatingDays[i]){
                tvRepeat.setText(tvRepeat.getText()+day[i]);
            }
        }

        row.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) context).startAlarmDetailsActivity(obj.id);
            }
        });
        btnToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // TODO Auto-generated method stub
                ((MainActivity) context).setAlarmEnabled(obj.id, isChecked);
            }
        });

        row.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                ((MainActivity) context).deleteAlarm(obj.id);
                return true;
            }
        });

        return row;
    }
}
