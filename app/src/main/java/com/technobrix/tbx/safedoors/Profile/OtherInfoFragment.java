package com.technobrix.tbx.safedoors.Profile;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.technobrix.tbx.safedoors.R;



public class OtherInfoFragment extends Fragment {

    RecyclerView recyclerView;
    GridLayoutManager manager;
    OtherAdapter adapter;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.other_info_fragment , container , false);
        recyclerView = (RecyclerView)view.findViewById(R.id.other);
        manager= new GridLayoutManager(getContext(),1);
        adapter = new OtherAdapter(getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);
        return view;
    }
}
