package com.example.annoyingalarm.rawcode;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.annoyingalarm.MainActivity;
import com.example.annoyingalarm.R;
import com.example.annoyingalarm.Weather;
import com.example.annoyingalarm.WeatherAdapter;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class WeatherActivity extends AppCompatActivity {

    ImageButton btnAlarm,btnNews,btnNight,btnMore;
    EditText search;
    Button btnSearch;
    TextView tvCity,tvTemp,tvState,tvHumidity,tvCloud,tvWind,tvDate;
    ImageView iconWeather;
    String City;
    String APIKey ="53fbf527d52d4d773e828243b90c1f8e";

    ListView lvWeather7days;
    WeatherAdapter weatherAdapter;
    ArrayList<Weather> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //will hide the title
        getSupportActionBar().hide(); // hide the title bar
        setContentView(R.layout.activity_weather);


        search = findViewById(R.id.search);
        btnSearch = findViewById(R.id.btnSearch);
        tvCity = findViewById(R.id.tvCityName);
        tvTemp = findViewById(R.id.tvTemp);
        tvState = findViewById(R.id.tvState);
        tvHumidity = findViewById(R.id.tvHumidity);
        tvCloud = findViewById(R.id.tvCloud);
        tvWind = findViewById(R.id.tvWind);
        tvDate = findViewById(R.id.tvDate);
        iconWeather = findViewById(R.id.icon);
        lvWeather7days = findViewById(R.id.lvWeather7days);
        final LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) findViewById(R.id.layoutTop).getLayoutParams();


        arrayList = new ArrayList<>();
        weatherAdapter = new WeatherAdapter(WeatherActivity.this,arrayList);
        lvWeather7days.setAdapter(weatherAdapter);

        layoutParams.setMargins(0,0,0,200);
        lvWeather7days.setVisibility(View.GONE);;

        getCurrentWeatherData("Saigon");
        Get7DaysData("Saigon");

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String city = search.getText().toString();
                if (city.equals("")){
                    City = "Saigon";
                }
                else {
                    City = city;
                }

                lvWeather7days.setVisibility(View.VISIBLE);
                layoutParams.setMargins(0,0,0,0);
                getCurrentWeatherData(City);
                Get7DaysData(City);
            }
        });

        btnAlarm = findViewById(R.id.btnAlarm);
        btnAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent switchToAlarm = new Intent(WeatherActivity.this, MainActivity.class);
                startActivity(switchToAlarm);
            }
        });
        btnNews = findViewById(R.id.btnNews);
        btnNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent switchToNews = new Intent(WeatherActivity.this, NewsActivity.class);
                startActivity(switchToNews);
            }
        });
        btnNight = findViewById(R.id.btnNight);
        btnNight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent switchToNight = new Intent(WeatherActivity.this, RelaxActivity.class);
                startActivity(switchToNight);
            }
        });
        btnMore = findViewById(R.id.btnMore);
        btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent switchToMore = new Intent(WeatherActivity.this, MoreActivity.class);
                startActivity(switchToMore);
            }
        });

    }
    public void getCurrentWeatherData(String data){
        RequestQueue requestQueue = Volley.newRequestQueue(WeatherActivity.this);
        String URL = "http://api.openweathermap.org/data/2.5/weather?q="+data+"&units=metric&appid="+APIKey;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);

                    String name = jsonObject.getString("name");
                    tvCity.setText(name);

                    String dateJSON = jsonObject.getString("dt");
                    long trans = Long.valueOf(dateJSON);
                    //Chuyen giay thanh mili giay
                    Date day = new Date(trans*1000);
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE dd-MM-yyyy");
                    String Date = simpleDateFormat.format(day);
                    tvDate.setText(Date);

                    JSONArray jsonArrayWeather = jsonObject.getJSONArray("weather");
                    JSONObject jsonObjectWeather = jsonArrayWeather.getJSONObject(0);
                    String state = jsonObjectWeather.getString("main");
                    String icon = jsonObjectWeather.getString("icon");
                    Picasso.with(WeatherActivity.this).load("http://openweathermap.org/img/wn/"+icon+".png").into(iconWeather);
                    tvState.setText(state);

                    JSONObject jsonObjectMain = jsonObject.getJSONObject("main");
                    String temp = jsonObjectMain.getString("temp");
                    String humidity = jsonObjectMain.getString("humidity");
                    Double t = Double.valueOf(temp);
                    String tem = String.valueOf(t.intValue());
                    tvTemp.setText(tem+"°C");
                    tvHumidity.setText(humidity+"%");

                    JSONObject jsonObjectWind = jsonObject.getJSONObject("wind");
                    String wi = jsonObjectWind.getString("speed");
                    tvWind.setText(wi+"m/s");

                    JSONObject jsonObjectCloud = jsonObject.getJSONObject("clouds");
                    String cloud = jsonObjectCloud.getString("all");
                    tvCloud.setText(cloud+"%");


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(WeatherActivity.this,"Can't not find.",Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(stringRequest);
    }
    private void Get7DaysData(String data) {
        if(!arrayList.isEmpty()){
            arrayList.clear();
        }
        String URL = "http://api.openweathermap.org/data/2.5/forecast/daily?q="+data+"&units=metric&cnt=7&appid="+APIKey;
        RequestQueue requestQueue = Volley.newRequestQueue(WeatherActivity.this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);

                    JSONArray jsonArrayList = jsonObject.getJSONArray("list");
                    for (int i =1;i<jsonArrayList.length();i++){
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
                Toast.makeText(WeatherActivity.this,"Can't not find.",Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(stringRequest);
    }
}
