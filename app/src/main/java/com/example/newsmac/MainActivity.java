package com.example.newsmac;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = MainActivity.class.getSimpleName();
    @BindView(R.id.textView) TextView mTextView;
    @BindView(R.id.getTextView) TextView mGetTextView;
    @BindView(R.id.sourceTextView) TextView mSourceTextView;
    @BindView(R.id.topTextView) TextView mTopTextView;
    @BindView(R.id.locationEditText) EditText mLocationEditText;
    @BindView(R.id.searchNews) Button mSearchNews;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        Typeface champagneFont = Typeface.createFromAsset(getAssets(), "fonts/cac_champagne.ttf");
        mTextView.setTypeface(champagneFont);
        mGetTextView.setTypeface(champagneFont);
        mSourceTextView.setTypeface(champagneFont);
        mTopTextView.setTypeface(champagneFont);
        mLocationEditText.setTypeface(champagneFont);
        //mSearchNews.setTypeface(champagneFont);

        mSearchNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"Searching News", Toast.LENGTH_LONG).show();
                String search = mLocationEditText.getText().toString();
                //Log.d(TAG, search);
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                intent.putExtra("search",search);
                startActivity(intent);
            }
        });

        mTopTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Top News", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, TopActivity.class);
                startActivity(intent);
            }
        });
        mSourceTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"Getting Sources",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this, SourcesActivity.class);
                startActivity(intent);
            }
        });
        mGetTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"Getting all News",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(MainActivity.this, GetActivity.class);
                startActivity(intent);
            }
        });

    }
}
