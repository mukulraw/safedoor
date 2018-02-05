package com.technobrix.tbx.safedoors.Event;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.technobrix.tbx.safedoors.Help;
import com.technobrix.tbx.safedoors.R;

/**
 * Created by tvs on 10/31/2017.
 */

public class Event extends Fragment {

    TabLayout tabs;
    ViewPager pager;
    EventAdapter adapter;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.event , container , false);

        tabs = (TabLayout)view.findViewById(R.id.tabs);
        pager = (ViewPager)view.findViewById(R.id.pager);

        adapter = new EventAdapter(getChildFragmentManager() , 2);

        tabs.addTab(tabs.newTab().setText("Event"));
        tabs.addTab(tabs.newTab().setText("Meeting"));

        pager.setAdapter(adapter);
        tabs.setupWithViewPager(pager);

        tabs.getTabAt(0).setText("Event");
        tabs.getTabAt(1).setText("Meeting");

        return view;
    }


    public class EventAdapter extends FragmentStatePagerAdapter {


        public EventAdapter(FragmentManager fm , int tab) {
            super(fm);

        }

        @Override
        public Fragment getItem(int position) {

            if (position == 0){
                return new Calender();
            }
            else if (position == 1){

                return new Meeting();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }
    }

}
