package com.technobrix.tbx.safedoors.NoticeBoard;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.technobrix.tbx.safedoors.AllApiInterface;
import com.technobrix.tbx.safedoors.ConnectionDetector;
import com.technobrix.tbx.safedoors.MainActivity;
import com.technobrix.tbx.safedoors.NoticeListPOJO.NoticeBean;
import com.technobrix.tbx.safedoors.NoticeListPOJO.NoticeList;
import com.technobrix.tbx.safedoors.R;
import com.technobrix.tbx.safedoors.bean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

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

    Toolbar toolbar;

    List<NoticeList> list;

    ProgressBar bar;

    ConnectionDetector cd;

    SwipeRefreshLayout refresh;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.notice_board, container, false);

        cd = new ConnectionDetector(getContext());

        refresh = (SwipeRefreshLayout) view.findViewById(R.id.refresh);

        refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Load();

            }
        });

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);

        toolbar = (Toolbar) ((MainActivity) getContext()).findViewById(R.id.toolbar);

        bar = (ProgressBar) view.findViewById(R.id.bar);

        manager = new GridLayoutManager(getContext(), 1);

        list = new ArrayList<>();

        adapter = new NoticeAdapter(getContext(), list);

        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(manager);


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

    public void Load() {
        if (cd.isConnectingToInternet()) {
            Calendar cc = Calendar.getInstance();
            int year = cc.get(Calendar.YEAR);
            int month = cc.get(Calendar.MONTH);
            int mDay = cc.get(Calendar.DAY_OF_MONTH);


            String date = String.valueOf(year) + "-" + String.valueOf(month) + "-" + String.valueOf(mDay);


            bar.setVisibility(View.VISIBLE);

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://safedoors.in")
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            AllApiInterface cr = retrofit.create(AllApiInterface.class);

            bean b = (bean) getContext().getApplicationContext();

            Call<NoticeBean> call = cr.notice(b.socity, date);

            Log.d("jai ho", b.socity);

            call.enqueue(new Callback<NoticeBean>() {
                @Override
                public void onResponse(Call<NoticeBean> call, Response<NoticeBean> response) {

                    adapter.setGridData(response.body().getNoticeList());



                    Log.d("jai", "response ");
                    bar.setVisibility(View.GONE);

                    refresh.setRefreshing(false);
                }

                @Override
                public void onFailure(Call<NoticeBean> call, Throwable t) {
                    bar.setVisibility(View.GONE);
                    refresh.setRefreshing(false);


                    Log.d("bdf", t.toString());
                }
            });


        } else {
            Toast.makeText(getContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onResume() {
        super.onResume();


        DrawerLayout drawer = (DrawerLayout) ((MainActivity) getContext()).findViewById(R.id.activity_main);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                getActivity(), drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        Load();

    }
}
