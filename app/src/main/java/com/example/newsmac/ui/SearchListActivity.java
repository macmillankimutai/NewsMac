package com.example.newsmac.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.newsmac.Constants;
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
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private String mRecentAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String search = intent.getStringExtra("search");

        getSearch(search);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mRecentAddress = mSharedPreferences.getString(Constants.PREFERENCES_LOCATION_KEY, null);

        if(mRecentAddress != null) {
            getSearch(mRecentAddress);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        ButterKnife.bind(this);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                addToSharedPreferences(s);
                getSearch(s);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        return super.onOptionsItemSelected(item);
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
    private void addToSharedPreferences(String search){
        mEditor.putString(Constants.PREFERENCES_LOCATION_KEY, search).apply();
    }

}
