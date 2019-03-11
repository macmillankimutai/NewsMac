package com.example.newsmac.ui;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.newsmac.R;
import com.example.newsmac.models.News;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewsDetailFragment extends Fragment implements View.OnClickListener {
    @BindView(R.id.titleNameTextView) TextView mTitle;
    @BindView(R.id.authorTextView) TextView mAuthor;
    @BindView(R.id.descriptionTextView) TextView mDescribe;
    @BindView(R.id.saveNewsButton) Button mSave;
    @BindView(R.id.publishTextView) TextView mPublish;
  //  @BindView(R.id.newsImageView) ImageView mImage;

    private News mNews;

    public static NewsDetailFragment newInstance(News news) {
        NewsDetailFragment newsDetailFragment = new NewsDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("news", Parcels.wrap(news));
        newsDetailFragment.setArguments(args);
        return newsDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mNews = Parcels.unwrap(getArguments().getParcelable("news"));
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_detail,container,false);
        ButterKnife.bind(this, view);

        mTitle.setText(mNews.getTitle());
        mAuthor.setText(mNews.getAuthor());
        mDescribe.setText(mNews.getDescription());

       // Picasso.get()
         //      .load(mNews.getImageUrl()).fit().into(mImage);

        mPublish.setOnClickListener(this);


        return view;
    }
    @Override
    public void onClick(View v){
        if (v == mPublish){
            Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mNews.getUrl()));
            startActivity(webIntent);
        }
    }

}
