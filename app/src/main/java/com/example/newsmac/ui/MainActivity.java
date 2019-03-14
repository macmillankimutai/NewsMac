package com.example.newsmac.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newsmac.Constants;
import com.example.newsmac.R;
import com.example.newsmac.SavedNewsListActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = MainActivity.class.getSimpleName();
    @BindView(R.id.textView) TextView mTextView;
    @BindView(R.id.getTextView) TextView mGetTextView;
    @BindView(R.id.sourceTextView) TextView mSourceTextView;
    @BindView(R.id.topTextView) TextView mTopTextView;
    //@BindView(R.id.locationEditText) EditText mLocationEditText;
    @BindView(R.id.searchNews) Button mSearchNews;
    @BindView(R.id.savedNewsButton) Button mSaved;

    //private SharedPreferences mSharedPreferences;
    //private SharedPreferences.Editor mEditor;
    private DatabaseReference mSearchedNewsReference;
    private ValueEventListener mSearchedNewsReferenceListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mSearchedNewsReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(Constants.FIREBASE_CHILD_SEARCHED_NEWS);

        mSearchedNewsReferenceListener = mSearchedNewsReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot searchSnapshot : dataSnapshot.getChildren()){
                    String search = searchSnapshot.getValue().toString();
                    Log.d("Search updated", "search: " + search);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
       // mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
       // mEditor = mSharedPreferences.edit();

        Typeface champagneFont = Typeface.createFromAsset(getAssets(), "fonts/cac_champagne.ttf");
        mTextView.setTypeface(champagneFont);
        mGetTextView.setTypeface(champagneFont);
        mSourceTextView.setTypeface(champagneFont);
        mTopTextView.setTypeface(champagneFont);
       // mLocationEditText.setTypeface(champagneFont);
        //mSearchNews.setTypeface(champagneFont);

        mSearchNews.setOnClickListener(this);
        mSaved.setOnClickListener(this);
        mTopTextView.setOnClickListener(this);
        mGetTextView.setOnClickListener(this);
        mSourceTextView.setOnClickListener(this);
    }
            @Override
            public void onClick(View v) {
              if(v == mSearchNews){
                Toast.makeText(MainActivity.this,"Searching News", Toast.LENGTH_LONG).show();
             //   String search = mLocationEditText.getText().toString();
             //   saveLocationToFirebase(search);

             //   if(!(search).equals("")) {
               //     addToSharedPreference(search);
                //}
                Intent intent = new Intent(MainActivity.this, SearchListActivity.class);
                startActivity(intent);
            }
            if(v == mSaved){
                Intent intent = new Intent(MainActivity.this, SavedNewsListActivity.class);
                startActivity(intent);
            }
            if(v == mTopTextView){
                Intent intent = new Intent(MainActivity.this, TopActivity.class);
                startActivity(intent);
            }
            if(v == mGetTextView) {
                Intent intent = new Intent(MainActivity.this, GetActivity.class);
                startActivity(intent);
            }
            if(v == mSourceTextView) {
                Intent intent = new Intent(MainActivity.this, SourcesActivity.class);
                startActivity(intent);
            }
        }
        public void saveLocationToFirebase(String search){
        mSearchedNewsReference.push().setValue(search);
        }
   //     private void addToSharedPreference(String search){
     //   mEditor.putString(Constants.PREFERENCES_LOCATION_KEY, search).apply();
       // }
    @Override
    protected void onDestroy() {
        super.onDestroy();

        mSearchedNewsReference.removeEventListener(mSearchedNewsReferenceListener);
    }

}
