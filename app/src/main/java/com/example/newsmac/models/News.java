package com.example.newsmac.models;

import org.parceler.Parcel;

import java.util.ArrayList;

@Parcel
public class News {
    private ArrayList<String> mSource;
    private String mAuthor;
    private String mTitle;
    private String mDescription;
    private String mUrl;
    private String mPublishedAt;
    private String mContent;
    private String mImageUrl;

    public News() {}

    public News(ArrayList<String> source, String author, String title, String description,
               String url, String publishedAt, String content,String imageUrl){
        this.mSource = source;
        this.mAuthor = author;
        this.mTitle = title;
        this.mDescription = description;
        this.mUrl = url;
        this.mPublishedAt = publishedAt;
        this.mContent = content;
        this.mImageUrl = imageUrl;
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
    public String getImageUrl(){ return  mImageUrl; }
}
