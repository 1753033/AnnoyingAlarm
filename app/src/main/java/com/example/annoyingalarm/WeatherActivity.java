package com.example.annoyingalarm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WeatherActivity extends AppCompatActivity {

    EditText search;
    Button btnSearch,btnNext;
    TextView tvCity,tvCountry,tvTemp,tvState,tvHumidity,tvCloud,tvWind,tvDate;
    ImageView iconWeather;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);



        search = findViewById(R.id.search);
        btnSearch = findViewById(R.id.btnSearch);
        btnNext = findViewById(R.id.btnNext);
        tvCity = findViewById(R.id.tvCityName);
        tvCountry = findViewById(R.id.tvCountryName);
        tvTemp = findViewById(R.id.tvTemp);
        tvState = findViewById(R.id.tvState);
        tvHumidity = findViewById(R.id.tvHumidity);
        tvCloud = findViewById(R.id.tvCloud);
        tvWind = findViewById(R.id.tvWind);
        tvDate = findViewById(R.id.tvDate);
        iconWeather = findViewById(R.id.icon);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String city = search.getText().toString();
                getCurrentWeatherData(city);
            }
        });
    }
    public void getCurrentWeatherData(String data){
        RequestQueue requestQueue = Volley.newRequestQueue(WeatherActivity.this);
        String URL = "http://api.openweathermap.org/data/2.5/weather?q="+data+"&units=metric&appid=e9566061dd067245ba0c8c82e118fbed";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);

                    String name = jsonObject.getString("name");
                    tvCity.setText("City: "+name);

                    String dateJSON = jsonObject.getString("dt");
                    long trans = Long.valueOf(dateJSON);
                    //Chuyen giay thanh mili giay
                    Date day = new Date(trans*1000);
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("EEEE dd-MM-yyyy HH-mm-ss");
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

                    JSONObject jsonObjectSys = jsonObject.getJSONObject("sys");
                    String country = jsonObjectSys.getString("country");
                    tvCountry.setText("Country: "+country);

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
