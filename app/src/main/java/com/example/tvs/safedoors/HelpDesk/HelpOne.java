package com.example.tvs.safedoors.HelpDesk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.tvs.safedoors.R;

public class HelpOne extends AppCompatActivity {

    RecyclerView recyclerView;
    GridLayoutManager manager;
    HelpOneAdapter adapeter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_one2);
        recyclerView = (RecyclerView) findViewById(R.id.helpdesk1);
        manager = new GridLayoutManager(this,1);
        adapeter = new HelpOneAdapter(this);
        recyclerView.setAdapter(adapeter);
        recyclerView.setLayoutManager(manager);

    }
}
