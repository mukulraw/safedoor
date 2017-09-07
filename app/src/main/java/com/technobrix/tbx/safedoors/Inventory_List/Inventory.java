package com.technobrix.tbx.safedoors.Inventory_List;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.technobrix.tbx.safedoors.R;

/**
 * Created by TBX on 8/28/2017.
 */

public class Inventory extends Fragment {

    RecyclerView recyclerView;
    GridLayoutManager manager;
    InventoryAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.inventory_items , container , false);

        recyclerView = (RecyclerView)view.findViewById(R.id.inventory);
        adapter = new InventoryAdapter(getContext());
        manager = new GridLayoutManager(getContext(),1);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        return view;

    }
}
