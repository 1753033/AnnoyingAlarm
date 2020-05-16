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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NewsActivity extends AppCompatActivity {

    ListView lvTitle;
    ArrayList<String> arrayTitle,arrayLink,arrayImg;
    NewsAdapter adapter;
    TextView tvDateNews;
    ImageButton btnAlarm,btnWeather,btnNight,btnMore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        tvDateNews = findViewById(R.id.tvDateNews);
        lvTitle = findViewById(R.id.lvTitle);
        arrayTitle = new ArrayList<>();
        arrayLink = new ArrayList<>();
        arrayImg = new ArrayList<>();

        adapter = new NewsAdapter(NewsActivity.this,arrayTitle,arrayLink,arrayImg);

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

        btnWeather = findViewById(R.id.btnWeather);
        btnWeather.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent switchToWeather = new Intent(NewsActivity.this,WeatherActivity.class);
                startActivity(switchToWeather);
            }
        });
        btnAlarm = findViewById(R.id.btnAlarm);
        btnAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent switchToAlarm = new Intent(NewsActivity.this,MainActivity.class);
                startActivity(switchToAlarm);
            }
        });
        btnNight = findViewById(R.id.btnNight);
        btnNight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent switchToNight = new Intent(NewsActivity.this,SleepHistoryActivity.class);
                startActivity(switchToNight);
            }
        });
        btnMore = findViewById(R.id.btnMore);
        btnMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent switchToMore = new Intent(NewsActivity.this,MoreActivity.class);
                startActivity(switchToMore);
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
            NodeList nodeList1 = document.getElementsByTagName("description");

            for (int i =0;i<nodeList.getLength();i++){
                String cdata = nodeList1.item(i+1).getTextContent();
                Pattern p = Pattern.compile("<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>");
                Matcher matcher = p.matcher(cdata);
                if(matcher.find()){
                    arrayImg.add(matcher.group(1));
                }
                Element element = (Element) nodeList.item(i);
                arrayTitle.add(parser.getValue(element,"title"));
                arrayLink.add(parser.getValue(element,"link"));
            }

            adapter.notifyDataSetChanged();

        }
    }
}
