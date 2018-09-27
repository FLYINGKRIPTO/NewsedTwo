package com.example.dell.newsed;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.List;

public class NewsLoader extends AsyncTaskLoader<List<NewsFeatures>> {
    private static final String TAG = NewsLoader.class.getName();
    private String mUrl;
    
    public NewsLoader(Context context,String url) {
        super(context);
        mUrl= url;

    }

    @Override
    protected void onStartLoading() {
       forceLoad();
        Log.d(TAG, "onStartLoading: method ");
    }

    @Override
    public List<NewsFeatures> loadInBackground() {
        if(mUrl == null){
            Log.d(TAG, "loadInBackground: ");
            return null;
        }
        List<NewsFeatures> newsFeatures = QueryUtils.fetchNews(mUrl);
        return  newsFeatures;
    }
}
