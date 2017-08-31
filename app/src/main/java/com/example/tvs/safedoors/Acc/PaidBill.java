package com.example.tvs.safedoors.Acc;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tvs.safedoors.R;


public class PaidBill extends Fragment {

    TextView conti;
    RecyclerView recyclerView;
    PaidAdapter adapter;
    GridLayoutManager manager;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.paidbill , container, false);

        conti = (TextView)view.findViewById(R.id.conti);
        recyclerView = (RecyclerView) view.findViewById(R.id.paidbill);
        manager = new GridLayoutManager(getContext(),1);
        adapter = new PaidAdapter(getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);
        return view;
    }
}
