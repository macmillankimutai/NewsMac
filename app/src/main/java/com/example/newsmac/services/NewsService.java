package com.example.newsmac.services;

import com.example.newsmac.Constants;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;

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

}
