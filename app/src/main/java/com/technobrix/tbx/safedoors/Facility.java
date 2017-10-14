package com.technobrix.tbx.safedoors;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.technobrix.tbx.safedoors.FacilityPOJO.Bean;
import com.technobrix.tbx.safedoors.FacilityPOJO.FacilityList;
import com.technobrix.tbx.safedoors.LoginPOJO.LoginBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class Facility extends Fragment {

    RecyclerView recyclerView;
    GridLayoutManager manager;
    FacilityAdapter adapter;
    ProgressBar bar;
    List<FacilityList> list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.facility , container , false);

        recyclerView = (RecyclerView)view.findViewById(R.id.facility);

        bar = (ProgressBar) view.findViewById(R.id.progress);

        manager = new GridLayoutManager(getContext(),1);

        list = new ArrayList<>();

        adapter = new FacilityAdapter(getContext(),list);

        recyclerView.setLayoutManager(manager);

        recyclerView.setAdapter(adapter);

        Log.d("kamal" , "hii");

        bar.setVisibility(View.VISIBLE);

        bean b = (bean)getContext().getApplicationContext();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://safedoors.in")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AllApiInterface cr = retrofit.create(AllApiInterface.class);

        Call<Bean> call = cr.bean(b.socity_id);

        call.enqueue(new Callback<Bean>() {
            @Override
            public void onResponse(Call<Bean> call, Response<Bean> response) {
                bar.setVisibility(View.GONE);

                adapter.setgriddata(response.body().getFacilityList());





            }

            @Override
            public void onFailure(Call<Bean> call, Throwable t) {

                bar.setVisibility(View.GONE);

            }
        });
        return view;
    }
}
