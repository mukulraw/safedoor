package com.technobrix.tbx.safedoors.NoticeBoard;

import android.icu.text.SimpleDateFormat;
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

import com.technobrix.tbx.safedoors.AllApiInterface;
import com.technobrix.tbx.safedoors.LoginPOJO.LoginBean;
import com.technobrix.tbx.safedoors.NoticeListPOJO.NoticeBean;
import com.technobrix.tbx.safedoors.R;

import java.sql.Time;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class NoticeBoard1 extends Fragment {
    RecyclerView recyclerView;
    GridLayoutManager manager;
    NoticeAdapter adapter;

    ProgressBar bar;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.notice_board,container,false);
        recyclerView  = (RecyclerView)view.findViewById(R.id.recycler);
        bar = (ProgressBar)view.findViewById(R.id.bar);
        manager = new GridLayoutManager(getContext(),1);
        adapter = new NoticeAdapter(getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);


        android.text.format.Time time = new android.text.format.Time();
        Calendar cc = Calendar.getInstance();
        int year=cc.get(Calendar.YEAR);
        int month=cc.get(Calendar.MONTH);
        int mDay = cc.get(Calendar.DAY_OF_MONTH);
        System.out.println("1");

        bar.setVisibility(View.VISIBLE);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://safedoors.in")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AllApiInterface cr = retrofit.create(AllApiInterface.class);
       /* Call<NoticeBean> call = cr.notice("1");
        call.enqueue(new Callback<NoticeBean>() {
            @Override
            public void onResponse(Call<NoticeBean> call, Response<NoticeBean> response) {

            }

            @Override
            public void onFailure(Call<NoticeBean> call, Throwable t) {

            }
        });*/
        return view;
    }
}
