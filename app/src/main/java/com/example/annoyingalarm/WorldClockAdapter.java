package com.example.annoyingalarm;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

public class WorldClockAdapter extends BaseAdapter {
    private ArrayList<WorldClock> list;
    private Context context;

    public WorldClockAdapter(Context context, ArrayList<WorldClock> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View row = inflater.inflate(R.layout.clock_list_item,null);

        TextView tvTimezone = row.findViewById(R.id.tvTimezone);
        TextView tvCity = row.findViewById(R.id.tvCity);
        TextView tvTime = row.findViewById(R.id.tvTime);
        final ImageButton img = row.findViewById(R.id.img_Weather);

        final WorldClock worldClock = list.get(position);

        tvCity.setText(worldClock.city);
        tvTime.setText(String.valueOf(worldClock.timeHour)+":"+String.valueOf(worldClock.timeMinute));
        tvTimezone.setText(worldClock.timeZone +" : GMT");
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent switchToWeather = new Intent(context,WeatherActivity.class);
                switchToWeather.putExtra("city",worldClock.city);
                context.startActivity(switchToWeather);
            }
        });

        return row;
    }
}
