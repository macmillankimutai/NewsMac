package com.example.newsmac;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
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

    }
}
