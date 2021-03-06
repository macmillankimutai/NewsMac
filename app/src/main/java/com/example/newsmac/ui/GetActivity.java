package com.example.newsmac.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.newsmac.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class GetActivity extends AppCompatActivity {
    private String[] everything = new String[] {"TechCrunch","The Verge","Business Insider","Gizmodo","Mashable","EthereumWorld","Crypto World"};

    @BindView(R.id.getListView) ListView mGetListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get);
        ButterKnife.bind(this);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, everything);
        mGetListView.setAdapter(adapter);
    }
}
