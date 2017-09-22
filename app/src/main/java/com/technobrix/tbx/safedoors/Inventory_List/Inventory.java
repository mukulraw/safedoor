package com.technobrix.tbx.safedoors.Inventory_List;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.technobrix.tbx.safedoors.AllApiInterface;
import com.technobrix.tbx.safedoors.InventryListPOJO.InventoryBean;
import com.technobrix.tbx.safedoors.LoginPOJO.LoginBean;
import com.technobrix.tbx.safedoors.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by TBX on 8/28/2017.
 */

public class Inventory extends Fragment {

    RecyclerView recyclerView;
    GridLayoutManager manager;
    InventoryAdapter adapter;
    ProgressBar bar;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.inventory_items , container , false);

        recyclerView = (RecyclerView)view.findViewById(R.id.inventory);
        bar = (ProgressBar) view.findViewById(R.id.progress);
        adapter = new InventoryAdapter(getContext());
        manager = new GridLayoutManager(getContext(),1);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

        bar.setVisibility(View.VISIBLE);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://safedoors.in")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AllApiInterface cr = retrofit.create(AllApiInterface.class);
        Call<InventoryBean> call = cr.inventory("1");
        call.enqueue(new Callback<InventoryBean>() {
            @Override
            public void onResponse(Call<InventoryBean> call, Response<InventoryBean> response) {
                bar.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<InventoryBean> call, Throwable t) {

                bar.setVisibility(View.GONE);

            }
        });
        return view;

    }
}
