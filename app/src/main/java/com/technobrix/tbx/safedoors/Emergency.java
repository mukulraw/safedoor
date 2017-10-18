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

import java.util.ArrayList;
import java.util.List;


public class Emergency extends Fragment {

    RecyclerView recyclerView;
    GridLayoutManager manager;
    ProgressBar bar;
    EmerAdapter adapter;

    List<EmerBean> list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.emer , container , false);
        recyclerView = (RecyclerView)view.findViewById(R.id.recycler);

       // bar = (ProgressBar) view.findViewById(R.id.progress);
        manager = new GridLayoutManager(getContext() , 1);

        list = new ArrayList<>();

        EmerBean b1 = new EmerBean();
        b1.setTitle("Police");
        b1.setNumber("100");

        list.add(b1);

        adapter = new EmerAdapter(getContext() , list);

        recyclerView.setLayoutManager(manager);

        Log.d("hbfdsg" , "man");

        return view;
    }
}
