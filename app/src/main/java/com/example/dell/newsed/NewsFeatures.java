package com.example.dell.newsed;

import android.graphics.Bitmap;

import java.net.URL;

public class NewsFeatures {
    private String headline;
    private URL webUrl;
    private Bitmap thumbnail;
    private String publicationDate;
    private String author;

    public NewsFeatures(String headline, URL webUrl, Bitmap thumbnail, String publicationDate, String author) {
        this.headline = headline;
        this.webUrl = webUrl;
        this.thumbnail = thumbnail;
        this.publicationDate = publicationDate;
        this.author = author;
    }

    public String getHeadline() {
        return headline;
    }

    public URL getWebUrl() {
        return webUrl;
    }

    public Bitmap getThumbnail() {
        return thumbnail;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public String getAuthor() {
        return author;
    }
}
