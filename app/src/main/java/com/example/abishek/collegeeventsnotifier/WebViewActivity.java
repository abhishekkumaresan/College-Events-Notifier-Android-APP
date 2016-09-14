package com.example.abishek.collegeeventsnotifier;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.widget.Toast;

/**
 * Created by abishek on 28/6/16.
 */
public class WebViewActivity extends AppCompatActivity {

    WebView webView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_view);
        Intent intent = getIntent();
        String url = intent.getExtras().getString("url");
        webView = (WebView) findViewById(R.id.webView);
        try {
            Toast.makeText(this, "Loading URL", Toast.LENGTH_LONG).show();
            webView.loadUrl(url);
        } catch (NullPointerException e) {
            e.getCause();
            e.getMessage();
        }
    }
}
