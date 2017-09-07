package com.technobrix.tbx.safedoors.NoticeBoard;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.technobrix.tbx.safedoors.R;



public class NoticeBoard1 extends Fragment {
    RecyclerView recyclerView;
    GridLayoutManager manager;
    NoticeAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.notice_board,container,false);
        recyclerView  = (RecyclerView)view.findViewById(R.id.recycler);
        manager = new GridLayoutManager(getContext(),1);
        adapter = new NoticeAdapter(getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);
        return view;
    }
}
