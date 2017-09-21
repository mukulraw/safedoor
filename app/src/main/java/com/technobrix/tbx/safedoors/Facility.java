package com.technobrix.tbx.safedoors;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class Facility extends Fragment {

    RecyclerView recyclerView;
    GridLayoutManager manager;
    FacilityAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.facility , container , false);

        recyclerView = (RecyclerView)view.findViewById(R.id.facility);
        manager = new GridLayoutManager(getContext(),1);
        adapter = new FacilityAdapter(getContext());
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        return view;
    }
}
