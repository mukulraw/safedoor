package com.example.tvs.safedoors.Profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tvs.safedoors.R;


public class FamilyInfoFragment extends Fragment {

    RecyclerView recyclerView;
    GridLayoutManager manager;
    familyAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.family_info , container, false);
        recyclerView = (RecyclerView)v.findViewById(R.id.family);
        manager= new GridLayoutManager(getContext(),1);
        adapter = new familyAdapter(getContext());
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        return v;
    }
}
