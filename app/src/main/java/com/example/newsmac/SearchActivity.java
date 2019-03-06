package com.example.newsmac;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.newsmac.models.News;
import com.example.newsmac.services.NewsService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class SearchActivity extends AppCompatActivity {
    public static final String TAG = SearchActivity.class.getSimpleName();
    @BindView(R.id.locationTextView) TextView mLocationTextView;
    @BindView(R.id.listView) ListView mListView;

    public ArrayList<News> news = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String search = intent.getStringExtra("search");

        mLocationTextView.setText("All searched news:" + search);
        getSearch(search);
    }
    private void getSearch(String search){
        final NewsService newsService = new NewsService();
        newsService.findNews(search, new  Callback() {
            @Override
            public void onFailure(Call call, IOException e){
                e.printStackTrace();
            }
            @Override
            public void onResponse(Call call, Response response) throws IOException {
                news = newsService.processResults(response);
                SearchActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String[] newNames = new String[news.size()];
                        for(int i = 0; i < newNames.length; i++){

                            newNames[i] = news.get(i).getAuthor();
                        }
                        ArrayAdapter adapter = new ArrayAdapter(SearchActivity.this, android.R.layout.simple_list_item_1, newNames);
                        mListView.setAdapter(adapter);
                        for (News news :news){
                            Log.d(TAG, "Source:" + news.getSource());
                            Log.d(TAG, "Author:" + news.getAuthor());
                            Log.d(TAG, "Title:" + news.getTitle());
                            Log.d(TAG, "Description:" + news.getDescription());
                            Log.d(TAG, "Url" + news.getUrl());
                            Log.d(TAG,"PublishedAt:" + news.getPublishedAt());
                            Log.d(TAG, "Content:" + news.getContent());
                        }
                    }
                });
            }
        });
    }
}
