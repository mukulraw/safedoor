package com.technobrix.tbx.safedoors.Profile;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.technobrix.tbx.safedoors.AllApiInterface;
import com.technobrix.tbx.safedoors.FacilityPOJO.Bean;
import com.technobrix.tbx.safedoors.FamilyPOJO.FamilyBean;
import com.technobrix.tbx.safedoors.ProfilePOJO.SetProfileBean;
import com.technobrix.tbx.safedoors.R;
import com.technobrix.tbx.safedoors.bean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Profile extends Fragment {

    TabLayout layout;
    ViewPager pager;
    ViewAdapter adapter;


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.profile , container , false);
        layout = (TabLayout)view.findViewById(R.id.sliding_tabs);
        pager = (ViewPager) view.findViewById(R.id.viewpager);

        layout.addTab(layout.newTab().setText("ProfileInfo"));
        layout.addTab(layout.newTab().setText("FamilyInfo"));
        layout.addTab(layout.newTab().setText("DocInfo"));
        layout.addTab(layout.newTab().setText("OtherInfo"));
        layout.setTabGravity(TabLayout.GRAVITY_FILL);
        adapter = new ViewAdapter(getChildFragmentManager(), layout.getTabCount());

        pager.setAdapter(adapter);
        layout.setupWithViewPager(pager);

        layout.getTabAt(0).setText("ProfileInfo");
        layout.getTabAt(1).setText("FamilyInfo");
        layout.getTabAt(2).setText("DocInfo");
        layout.getTabAt(3).setText("OtherInfo");



        return view;
    }
}
