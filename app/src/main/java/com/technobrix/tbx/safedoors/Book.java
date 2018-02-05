package com.technobrix.tbx.safedoors;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.technobrix.tbx.safedoors.BookPOJO.BookingBean;
import com.technobrix.tbx.safedoors.BookPOJO.FacilityBookList;
import com.technobrix.tbx.safedoors.EventDatePOJO.EventBean;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by tvs on 11/17/2017.
 */

public class Book extends Fragment {

    RecyclerView grid;

    GridLayoutManager manager;

    BookAdpetr  adpetr;

   List<FacilityBookList>list;

   ProgressBar bar;

   ConnectionDetector cd;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.book , container , false);

        cd = new ConnectionDetector(getContext());

        grid = (RecyclerView)view.findViewById(R.id.grid);

        bar = (ProgressBar) view.findViewById(R.id.progress);

        manager = new GridLayoutManager(getContext() , 1);

        list = new ArrayList<>();

        adpetr = new BookAdpetr(getContext() ,list);

        grid.setAdapter(adpetr);

        grid.setLayoutManager(manager);

        if (cd.isConnectingToInternet()){
            bar.setVisibility(View.VISIBLE);

            bean b = (bean)getContext().getApplicationContext();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://safedoors.in")
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            AllApiInterface cr = retrofit.create(AllApiInterface.class);

            Call<BookingBean> call = cr.boo(b.socity , b.house_id);

            call.enqueue(new Callback<BookingBean>() {
                @Override
                public void onResponse(Call<BookingBean> call, Response<BookingBean> response) {

                    adpetr.setgrid(response.body().getFacilityBookList());

                    bar.setVisibility(View.GONE);


                }

                @Override
                public void onFailure(Call<BookingBean> call, Throwable t) {

                    bar.setVisibility(View.GONE);

                }
            });




        }else {

            Toast.makeText(getContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
        }


        return view;
    }
}
