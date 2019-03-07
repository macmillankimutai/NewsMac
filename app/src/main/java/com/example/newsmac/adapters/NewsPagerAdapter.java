package com.example.newsmac.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.newsmac.models.News;
import com.example.newsmac.ui.NewsDetailFragment;

import java.util.ArrayList;

public class NewsPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<News> mNews;

    public  NewsPagerAdapter(FragmentManager fm, ArrayList<News> news) {
        super(fm);
        mNews = news;
    }
    @Override
    public Fragment getItem(int position){
        return NewsDetailFragment.newInstance(mNews.get(position));
    }
    @Override
    public int getCount(){
        return mNews.size();
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return mNews.get(position).getTitle();
    }
}
