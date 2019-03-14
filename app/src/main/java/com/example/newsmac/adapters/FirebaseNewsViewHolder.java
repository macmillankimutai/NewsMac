package com.example.newsmac.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.newsmac.Constants;
import com.example.newsmac.R;
import com.example.newsmac.models.News;
import com.example.newsmac.ui.NewsDetailActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.parceler.Parcels;
import org.w3c.dom.Text;

import java.util.ArrayList;

import butterknife.BindView;

public class FirebaseNewsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    View mView;
    Context mContext;

    public FirebaseNewsViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }
    public void bindNews(News news){
        TextView mNewsNameTextView = (TextView) mView.findViewById(R.id.newsNameTextView);
        TextView mAuthorTextView = (TextView) mView.findViewById(R.id.authorTextView);

        mNewsNameTextView.setText(news.getTitle());
        mAuthorTextView.setText(news.getAuthor());
    }
    @Override
    public void onClick(View view) {
        final ArrayList<News> news = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_NEWS);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot  : dataSnapshot.getChildren()){
                    news.add(snapshot.getValue(News.class));
                }
                int itemPosition = getLayoutPosition();
                Intent intent = new Intent(mContext, NewsDetailActivity.class);
                intent.putExtra("position",itemPosition + "");
                intent.putExtra("news", Parcels.wrap(news));
                mContext.startActivity(intent);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
