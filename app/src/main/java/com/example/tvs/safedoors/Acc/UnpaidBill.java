package com.example.tvs.safedoors.Acc;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tvs.safedoors.R;



public class UnpaidBill extends Fragment {

    RecyclerView recyclerView;
    GridLayoutManager manager;
    UnpaidAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.unpaid_bill, container,false);

        recyclerView = (RecyclerView)view.findViewById(R.id.unpaid);
        adapter = new UnpaidAdapter(getContext());
        manager = new GridLayoutManager(getContext(),1);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        return view;
    }
}
