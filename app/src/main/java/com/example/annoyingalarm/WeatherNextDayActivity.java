package com.example.annoyingalarm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class WeatherNextDayActivity extends AppCompatActivity {

    ImageView imgBack;
    ListView lvWeather7days;
    TextView tvCity;
    WeatherAdapter weatherAdapter;
    ArrayList<Weather> arrayList;

    String City;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_next_day);

        Intent intent = getIntent();
        String city = intent.getStringExtra("City");
        String APIKey = intent.getStringExtra("APIKey");
        if(city.equals("")){
            City = "Saigon";
        }
        else {
            City = city;
        }
        Get7DaysData(City,APIKey);



        imgBack = findViewById(R.id.back);
        tvCity = findViewById(R.id.tvCity);
        lvWeather7days = findViewById(R.id.lvWeather7days);
        arrayList = new ArrayList<>();
        weatherAdapter = new WeatherAdapter(WeatherNextDayActivity.this,arrayList);
        lvWeather7days.setAdapter(weatherAdapter);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void Get7DaysData(String data,String APIKey) {
        String URL = "http://api.openweathermap.org/data/2.5/forecast/daily?q="+data+"&units=metric&cnt=7&appid="+APIKey;
        RequestQueue requestQueue = Volley.newRequestQueue(WeatherNextDayActivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);

                    JSONObject jsonObjectCity = jsonObject.getJSONObject("city");
                    String cityName = jsonObjectCity.getString("name");
                    tvCity.setText(cityName);

                    JSONArray jsonArrayList = jsonObject.getJSONArray("list");
                    for (int i =0;i<jsonArrayList.length();i++){
                        JSONObject jsonObjectList = jsonArrayList.getJSONObject(i);
                        String date = jsonObjectList.getString("dt");
                        long trans = Long.valueOf(date);
                        //Chuyen giay thanh mili giay
                        Date day = new Date(trans*1000);
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE dd-MM-yyyy");
                        String Date = simpleDateFormat.format(day);

                        JSONObject jsonObjectTemp = jsonObjectList.getJSONObject("temp");
                        String maxTemp = jsonObjectTemp.getString("max");
                        String minTemp = jsonObjectTemp.getString("min");
                        Double max = Double.valueOf(maxTemp);
                        Double min = Double.valueOf(minTemp);
                        String Max = String.valueOf(max.intValue());
                        String Min = String.valueOf(min.intValue());

                        JSONArray jsonArrayWeather = jsonObjectList.getJSONArray("weather");
                        JSONObject weather = jsonArrayWeather.getJSONObject(0);
                        String status = weather.getString("description");
                        String icon = weather.getString("icon");

                        arrayList.add(new Weather(Date,status,icon,Max,Min));
                    }
                    weatherAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue.add(stringRequest);
    }

}
