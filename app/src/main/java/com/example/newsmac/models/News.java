package com.example.newsmac.models;

import java.util.ArrayList;

public class News {
    private ArrayList<String> mSource;
    private String mAuthor;
    private String mTitle;
    private String mDescription;
    private String mUrl;
    private String mPublishedAt;
    private String mContent;

    public News(ArrayList<String> source, String author, String title, String description,
               String url, String publishedAt, String content){
        this.mSource = source;
        this.mAuthor = author;
        this.mTitle = title;
        this.mDescription = description;
        this.mUrl = url;
        this.mPublishedAt = publishedAt;
        this.mContent = content;
    }
    public ArrayList<String> getSource(){
        return mSource;
    }
    public String getAuthor(){
        return mAuthor;
    }
    public String getTitle(){
        return mTitle;
    }
    public String getDescription(){
        return mDescription;
    }
    public String getUrl(){
        return mUrl;
    }
    public String getPublishedAt(){
        return mPublishedAt;
    }
    public String getContent(){
        return mContent;
    }
}
