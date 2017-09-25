package com.technobrix.tbx.safedoors.HelpDesk;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;

import com.technobrix.tbx.safedoors.R;

public class HelpOne extends AppCompatActivity {

    RecyclerView recyclerView;
    GridLayoutManager manager;
    HelpOneAdapter adapeter;

    ProgressBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_one2);
        bar = (ProgressBar) findViewById(R.id.bar);
        recyclerView = (RecyclerView) findViewById(R.id.helpdesk1);
        manager = new GridLayoutManager(this,1);
        adapeter = new HelpOneAdapter(this);
        recyclerView.setAdapter(adapeter);
        recyclerView.setLayoutManager(manager);

    }
}
