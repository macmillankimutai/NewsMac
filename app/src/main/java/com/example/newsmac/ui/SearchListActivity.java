package com.example.newsmac.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.newsmac.R;
import com.example.newsmac.adapters.NewsListAdapter;
import com.example.newsmac.models.News;
import com.example.newsmac.services.NewsService;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class SearchListActivity extends AppCompatActivity {
    public static final String TAG = SearchListActivity.class.getSimpleName();
    @BindView(R.id.recyclerView) RecyclerView mRecylerView;
    private NewsListAdapter mAdapter;

    public ArrayList<News> news = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String search = intent.getStringExtra("search");

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
                SearchListActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter = new NewsListAdapter(getApplicationContext(), news);
                        mRecylerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(SearchListActivity.this);
                        mRecylerView.setLayoutManager(layoutManager);
                        mRecylerView.setHasFixedSize(true);
                    }
                });
            }
        });
    }
}
