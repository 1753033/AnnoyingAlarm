package com.example.annoyingalarm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class NewsDetailActivity extends AppCompatActivity {

    WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        webView = findViewById(R.id.WebNews);

        Intent intent = getIntent();
        String link = intent.getStringExtra("link");

        webView.loadUrl(link);
        webView.setWebViewClient(new WebViewClient());
    }
}
