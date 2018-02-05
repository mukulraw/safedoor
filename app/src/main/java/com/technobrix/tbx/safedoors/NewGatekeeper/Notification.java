package com.technobrix.tbx.safedoors.NewGatekeeper;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;


import com.technobrix.tbx.safedoors.Help;
import com.technobrix.tbx.safedoors.R;

public class Notification extends AppCompatActivity {

    Toolbar toolbar;

    TabLayout tabs;
    ViewPager pager;
    NotiPager adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification2);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setNavigationIcon(R.drawable.arrow);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();


            }

        });

        toolbar.setTitle("Notification");

        tabs = (TabLayout)findViewById(R.id.tabs);
        pager = (ViewPager)findViewById(R.id.pager);

        adapter = new NotiPager(getSupportFragmentManager() , 2);

        tabs.addTab(tabs.newTab().setText("Regular"));
        tabs.addTab(tabs.newTab().setText("New"));

        pager.setAdapter(adapter);
        tabs.setupWithViewPager(pager);

        tabs.getTabAt(0).setText("Regular");
        tabs.getTabAt(1).setText("New");

    }


    public class NotiPager extends FragmentStatePagerAdapter {


        public NotiPager(FragmentManager fm , int tab) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            if (position == 0){

                return new Regular();
            }

            else if (position == 1){

                return new NotiNew();
            }

            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }
    }

}



