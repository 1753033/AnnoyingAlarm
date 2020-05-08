package com.example.annoyingalarm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class NewsActivity extends AppCompatActivity {

    ListView lvTitle;
    ArrayList<String> arrayTitle,arrayLink;
    ArrayAdapter adapter;
    TextView tvDateNews;
    ImageButton btnAlarm,btnWeather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        btnAlarm = findViewById(R.id.btnAlarm);
        btnWeather = findViewById(R.id.btnWeather);

        tvDateNews = findViewById(R.id.tvDateNews);
        lvTitle = findViewById(R.id.lvTitle);
        arrayTitle = new ArrayList<>();
        arrayLink = new ArrayList<>();

        adapter = new ArrayAdapter(NewsActivity.this,android.R.layout.simple_list_item_1,arrayTitle);

        lvTitle.setAdapter(adapter);

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        tvDateNews.setText(formatter.format(date));

        new RSS().execute("https://vnexpress.net/rss/so-hoa.rss");

        lvTitle.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(NewsActivity.this,NewsDetailActivity.class);
                intent.putExtra("link",arrayLink.get(position));
                startActivity(intent);
            }
        });

        btnAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewsActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        btnWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewsActivity.this,WeatherActivity.class);
                startActivity(intent);
            }
        });
    }

    private class RSS extends AsyncTask<String,Void,String> {
        @Override
        protected String doInBackground(String... strings) {
            StringBuilder content = new StringBuilder();
            try {
                URL url = new URL(strings[0]);
                InputStreamReader inputStreamReader = new InputStreamReader(url.openConnection().getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line = "";
                while ((line = bufferedReader.readLine())!=null){
                    content.append(line);
                }
                bufferedReader.close();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return content.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            XMLDOMParser parser = new XMLDOMParser();

            Document document = parser.getDocument(s);

            NodeList nodeList = document.getElementsByTagName("item");


            for (int i =0;i<nodeList.getLength();i++){
                Element element = (Element) nodeList.item(i);
                arrayTitle.add(parser.getValue(element,"title"));
                arrayLink.add(parser.getValue(element,"link"));
            }

            adapter.notifyDataSetChanged();

        }
    }
}
