package com.example.newsmac.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.newsmac.R;
import com.example.newsmac.models.News;
import com.example.newsmac.ui.NewsDetailFragment;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.NewsViewHolder> {
    private ArrayList<News> mNews = new ArrayList<>();
    private Context mContext;

    public NewsListAdapter(Context context, ArrayList<News> news){
        mContext = context;
        mNews = news;
    }
    @Override
    public NewsListAdapter.NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.restaurant_list_item,parent, false);
        NewsViewHolder viewHolder = new NewsViewHolder(view);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder(NewsListAdapter.NewsViewHolder holder, int position){
        holder.bindNews(mNews.get(position));
    }
    @Override
    public int getItemCount() {
        return mNews.size();
    }

    public class  NewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.newsNameTextView) TextView mNewsNameTextView;
        @BindView(R.id.descriptionTextView) TextView mDescription;
        @BindView(R.id.authorTextView) TextView mAuthorTextView;

        private Context mContext;

        public NewsViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View view){
            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(mContext, NewsDetailFragment.class);
            intent.putExtra("position", itemPosition);
            intent.putExtra("news", Parcels.wrap(mNews));
            mContext.startActivity(intent);
        }
        public void bindNews(News news) {
            mNewsNameTextView.setText(news.getTitle());
            mDescription.setText(news.getDescription());
            mAuthorTextView.setText(news.getAuthor());
        }
    }

}
