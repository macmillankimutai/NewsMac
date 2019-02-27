package com.example.newsmac;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SourcesActivity extends AppCompatActivity {
    public String[] top = new String[] {"ABC news","Aftenposten","Al jazeera English","ANSA","Argaam","Ary News"};
    @BindView(R.id.sourceListView) ListView mSourceListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sources);
        ButterKnife.bind(this);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, top);
        mSourceListView.setAdapter(adapter);
    }
}
