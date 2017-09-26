package com.technobrix.tbx.safedoors;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.technobrix.tbx.safedoors.GetNotificationPOJO.NotificationBean;
import com.technobrix.tbx.safedoors.GetNotificationPOJO.NotificationList;
import com.technobrix.tbx.safedoors.ProfilePOJO.SetFamilyBean;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Notification extends AppCompatActivity {


    RecyclerView recyclerView;
    GridLayoutManager manager;
    NotiAdapter adapter;
    List<NotificationList> list;
    ProgressBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        manager = new GridLayoutManager(getApplication() , 1);

        list = new ArrayList<>();
        adapter = new NotiAdapter(this  , list);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);

        bar = (ProgressBar) findViewById(R.id.progress);

        bar.setVisibility(View.VISIBLE);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://safedoors.in")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        bean b = (bean)getApplicationContext();

        AllApiInterface cr = retrofit.create(AllApiInterface.class);
        Call<NotificationBean> call = cr.notify(b.userId, b.socity );

        call.enqueue(new Callback<NotificationBean>() {
            @Override
            public void onResponse(Call<NotificationBean> call, Response<NotificationBean> response) {

                adapter.Setgrid(response.body().getNotificationList());

                bar.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<NotificationBean> call, Throwable t) {

                bar.setVisibility(View.GONE);

            }
        });


    }
}
