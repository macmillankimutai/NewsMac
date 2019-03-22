package com.example.newsmac.ui;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newsmac.Constants;
import com.example.newsmac.R;
import com.example.newsmac.SavedNewsListActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mSearchedNewsReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(Constants.FIREBASE_CHILD_SEARCHED_NEWS);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth){
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if(user != null) {
                    getSupportActionBar().setTitle("Welcome," + user.getDisplayName() + "!");
                } else {

                }
            }
        };

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
    public void move(View view){
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
        mGetTextView.startAnimation(animation);
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
               // Intent intent = new Intent(MainActivity.this, SavedNewsListActivity.class);
                //startActivity(intent);
                startButtoAnimation(mSaved);
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
        public void startButtoAnimation(Button btn){
            Animation shake = AnimationUtils.loadAnimation(this, R.anim.rotate);
            btn.setAnimation(shake);
            btn.startAnimation(shake);

            shake.setAnimationListener(new Animation.AnimationListener() {

                @Override
                public void onAnimationStart(Animation animation) {

                }

                @Override
                public void onAnimationRepeat(Animation animation) {

                }

                @Override
                public void onAnimationEnd(Animation animation) {
                    startActivity(new Intent(getApplicationContext(), SavedNewsListActivity.class));
                }
            });
        }
        public void saveLocationToFirebase(String search){
        mSearchedNewsReference.push().setValue(search);
        }
   //     private void addToSharedPreference(String search){
     //   mEditor.putString(Constants.PREFERENCES_LOCATION_KEY, search).apply();
       // }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.action_logout) {
            logout();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    private void logout() {
        FirebaseAuth.getInstance().signOut();
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();

        mSearchedNewsReference.removeEventListener(mSearchedNewsReferenceListener);
    }
    @Override
    public  void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }


}
