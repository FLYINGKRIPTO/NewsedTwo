package com.example.dell.newsed;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class NewsAdapter extends ArrayAdapter<NewsFeatures> {
    ArrayList<NewsFeatures> list;

    NewsAdapter(@NonNull Context context, ArrayList<NewsFeatures> list) {
        super(context, R.layout.activity_main, R.id.list, list);
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.news, parent, false);
        }
        NewsFeatures newsFeatures = list.get(position);
        ImageView image = view.findViewById(R.id.thumbnail);
        TextView headline = view.findViewById(R.id.headline);
        TextView author = view.findViewById(R.id.author);
        TextView date = view.findViewById(R.id.date);
        headline.setText(newsFeatures.getHeadline());
        author.setText(newsFeatures.getAuthor());
        date.setText(newsFeatures.getPublicationDate());
        image.setImageBitmap(newsFeatures.getThumbnail());
        return view;
    }
}
