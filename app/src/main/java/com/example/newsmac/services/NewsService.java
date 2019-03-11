package com.example.newsmac.services;

import com.example.newsmac.Constants;
import com.example.newsmac.models.News;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NewsService {
    public static void findNews(String search, Callback callback){

        OkHttpClient client = new OkHttpClient.Builder()
                .build();

        HttpUrl.Builder urlBuilder = HttpUrl.parse(Constants.NEWS_BASE_URL).newBuilder();
        urlBuilder.addQueryParameter(Constants.NEWS_TOPIC_QUERY_PARAMETER, search);

        String url = urlBuilder.build().toString();
        Request request = new Request.Builder()
                .url(url)
                .header("Authorization",Constants.NEWS_API)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }
    public ArrayList<News> processResults(Response response){
        ArrayList<News> newses = new ArrayList<>();
        try{
            String jsonData =response.body().string();
            JSONObject newsJSON  = new JSONObject(jsonData);
            JSONArray articlesJSON = newsJSON.getJSONArray("articles");
            if(response.isSuccessful()){
                for (int i = 0; i < articlesJSON.length(); i++){
                   JSONObject newJSON = articlesJSON.getJSONObject(i);

                    ArrayList<String> source = new ArrayList<>();
                     String author = newJSON.getString("author");
                     String title = newJSON.getString("title");
                     String description = newJSON.getString("description");
                     String url = newJSON.getString("url");
                     String publishedAt = newJSON.getString("publishedAt");
                     String content = newJSON.getString("content");
                     String imageUrl = newJSON.getString("urlToImage");
                     String replaceString= imageUrl.replace("http","https");


                     News newss =  new News(source, author, title, description, url, publishedAt,content,replaceString);
                             newses.add(newss);
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }catch (JSONException e){
            e.printStackTrace();
        }
        return newses;
    }

}
