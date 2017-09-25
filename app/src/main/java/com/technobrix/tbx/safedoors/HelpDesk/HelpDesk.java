package com.technobrix.tbx.safedoors.HelpDesk;

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



public class HelpDesk extends Fragment {
    RecyclerView recyclerView;
    GridLayoutManager manager;
    HelpDiskAdapeter adapeter;
    ProgressBar progressBar;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.helpdesk , container ,false);

        recyclerView = (RecyclerView)view.findViewById(R.id.helpdesk);
        progressBar = (ProgressBar) view.findViewById(R.id.progress);
        manager = new GridLayoutManager(getContext(),1);
        adapeter = new HelpDiskAdapeter(getContext());
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapeter);
        return  view;
    }
}
