package com.example.dell.newsed;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getName();
    private static final String REQUESTED_URL = "https://content.guardianapis.com/search?q=Technology&format=json&order-by=newest&page-size=20&from-date=2018-09-01&show-fields=headline,thumbnail,short-url&show-tags=contributor,publication&api-key=751d026c-5315-4412-824f-90852ee18451";
    private static final int NEWS_LOADER_ID = 1;
    private NewsAdapter newsAdapter;
    private ProgressBar progressBar;
    private TextView emptyStateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView newsListView =(ListView) findViewById(R.id.list);
        emptyStateTextView = (TextView) findViewById(R.id.empty_view);
        newsListView.setEmptyView(emptyStateTextView);
        //adapter code
        newsAdapter = new NewsAdapter(this, new ArrayList<NewsFeatures>());
        newsListView.setAdapter(newsAdapter);

    }
}
