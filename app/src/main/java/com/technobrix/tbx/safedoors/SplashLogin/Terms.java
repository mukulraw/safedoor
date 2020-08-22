package com.technobrix.tbx.safedoors.SplashLogin;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import com.technobrix.tbx.safedoors.R;

public class Terms extends AppCompatActivity {

    WebView web;
    String url = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms);

        web = (WebView)findViewById(R.id.web);
        web.getSettings().setJavaScriptEnabled(true);

        url = getIntent().getStringExtra("url");

        web.loadUrl(url);
    }
}
