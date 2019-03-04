package com.example.newsmac;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TopActivity extends AppCompatActivity {
    private  String[] top = new String[] {"Texas LawMaker","Syracuse vs North Carolina","Cara anna & Khaled","USA today","Chicago tribune","Aaron Farling","Trade War"};
    @BindView(R.id.topListView) ListView mTopListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top);
        ButterKnife.bind(this);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, top);
        mTopListView.setAdapter(adapter);
    }
}
