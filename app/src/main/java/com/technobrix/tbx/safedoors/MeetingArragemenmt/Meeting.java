package com.technobrix.tbx.safedoors.MeetingArragemenmt;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.technobrix.tbx.safedoors.MainActivity;
import com.technobrix.tbx.safedoors.R;


public class Meeting extends Fragment {

    LinearLayout layout1,linearLayout2,linearLayout3;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.meeting , container , false);
        layout1 = (LinearLayout)view.findViewById(R.id.meeting);
        linearLayout2 = (LinearLayout)view.findViewById(R.id.starttime);
        linearLayout3 = (LinearLayout)view.findViewById(R.id.endtime);
        layout1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager =((MainActivity) getContext()).getSupportFragmentManager();
                fragmentManager.popBackStack();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                Meetingcalender fragment = new Meetingcalender();
                ft.replace(R.id.replace, fragment);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                 ft.addToBackStack(null);
                ft.commit();
            }
        });

        linearLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager =((MainActivity) getContext()).getSupportFragmentManager();
                fragmentManager.popBackStack();
                FragmentTransaction ft = fragmentManager.beginTransaction();
                MeetingClock fragment = new MeetingClock();
                ft.replace(R.id.replace, fragment);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE);
                ft.addToBackStack(null);
                ft.commit();
            }
        });
        return view;
    }
}
