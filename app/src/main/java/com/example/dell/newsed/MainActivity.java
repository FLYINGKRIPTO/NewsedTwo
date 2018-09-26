package com.example.dell.newsed;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();
    private static final String REQUESTED_URL = "https://content.guardianapis.com/search?q=Technology&format=json&order-by=newest&page-size=20&from-date=2018-09-01&show-fields=headline,thumbnail,short-url&show-tags=contributor,publication&api-key=751d026c-5315-4412-824f-90852ee18451";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
