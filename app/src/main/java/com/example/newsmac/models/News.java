package com.example.newsmac.models;

import org.parceler.Parcel;

import java.util.ArrayList;

@Parcel
public class News {
    private ArrayList<String> source;
    private String author;
    private String title;
    private String description;
    private String url;
    private String publishedAt;
    private String content;
    private String imageUrl;

    public News() {}

    public News(ArrayList<String> source, String author, String title, String description,
               String url, String publishedAt, String content,String imageUrl){
        this.source = source;
        this.author = author;
        this.title = title;
        this.description = description;
        this.url = url;
        this.publishedAt = publishedAt;
        this.content = content;
        this.imageUrl = imageUrl;
    }
    public ArrayList<String> getSource(){
        return source;
    }
    public String getAuthor(){
        return author;
    }
    public String getTitle(){
        return title;
    }
    public String getDescription(){
        return description;
    }
    public String getUrl(){
        return url;
    }
    public String getPublishedAt(){
        return publishedAt;
    }
    public String getContent(){
        return content;
    }
    public String getImageUrl(){ return  imageUrl; }
}
