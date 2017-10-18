package com.technobrix.tbx.safedoors.Event;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;
import com.technobrix.tbx.safedoors.AllApiInterface;
import com.technobrix.tbx.safedoors.EventDatePOJO.EventBean;
import com.technobrix.tbx.safedoors.EventDatePOJO.MeetingList;
import com.technobrix.tbx.safedoors.FacilityPOJO.Bean;
import com.technobrix.tbx.safedoors.MeetingPOJO.MeetingBean;
import com.technobrix.tbx.safedoors.R;
import com.technobrix.tbx.safedoors.bean;
import com.technobrix.tbx.safedoors.meetingDetailBean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class Calender extends Fragment {



    MaterialCalendarView calendarView;
    ProgressBar progress;
    List<CalendarDay> cl;
    List<String> socIds;
    RecyclerView recyclerView;
    GridLayoutManager manager;
    EventAdapter adapter;

    List<MeetingList> list ;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.calender , container,false);

        progress = (ProgressBar)view.findViewById(R.id.progress);
        calendarView = (MaterialCalendarView) view.findViewById(R.id.calender);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler);


        list = new ArrayList<>();
        adapter = new EventAdapter(getContext() , list);
        manager = new GridLayoutManager(getContext(),1);

        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(manager);

        socIds = new ArrayList<>();

        cl = new ArrayList<>();

        calendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView widget, @NonNull CalendarDay date, boolean selected) {


                Log.d("asdas" , String.valueOf(date.getDay()));



                Calendar cal = Calendar.getInstance();


                int year = date.getYear();
                int month = date.getMonth() + 1;
                int day = date.getDay();


                String dat = String.valueOf(year) + "-" + String.valueOf(month) + "-" + String.valueOf(day);

                progress.setVisibility(View.VISIBLE);

                bean b = (bean)getContext().getApplicationContext();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://safedoors.in")
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                AllApiInterface cr = retrofit.create(AllApiInterface.class);

                Call<EventBean> call = cr.event("1" ,dat);

                Log.d ("nkdf" , dat);

                call.enqueue(new Callback<EventBean>() {
                    @Override
                    public void onResponse(Call<EventBean> call, Response<EventBean> response) {

                        Log.d("jfj" ,"response");

                        Log.d("hfdhsk" , String.valueOf(response.body().getMeetingList().size()));

                        adapter.Setgrid(response.body().getMeetingList());

                        progress.setVisibility(View.GONE);

                    }

                    @Override
                    public void onFailure(Call<EventBean> call, Throwable t) {


                        Log.d("gdg" ,t.toString());
                        progress.setVisibility(View.GONE);

                    }
                });



            }
        });




        return view;
    }


    @Override
    public void onResume() {
        super.onResume();

        updateCalendar();

    }

    public void updateCalendar()
    {

        progress.setVisibility(View.VISIBLE);

        cl.clear();
        final Calendar calendar = Calendar.getInstance();

        socIds.clear();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://safedoors.in")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        AllApiInterface cr = retrofit.create(AllApiInterface.class);


        bean b = (bean)getContext().getApplicationContext();

        Call<MeetingBean> call = cr.getMeetings(b.socity);

        call.enqueue(new Callback<MeetingBean>() {
            @Override
            public void onResponse(Call<MeetingBean> call, Response<MeetingBean> response) {

                for (int i = 0 ; i < response.body().getMeetingList().size() ; i++)
                {



                    socIds.add(response.body().getMeetingList().get(i).getId());


                    String dat = response.body().getMeetingList().get(i).getMeetingDate();

                    Log.d("adsad" , dat);

                    String[] d1 = dat.split("-");


                    int year = Integer.parseInt(d1[0]);
                    int day = Integer.parseInt(d1[2]);
                    int month = Integer.parseInt(d1[1]) - 1;

                    Log.d("asdasd" , String.valueOf(day) + " " + String.valueOf(month) + " " + String.valueOf(year));

                    calendar.set(year , month , day);

                    CalendarDay calendarDay = CalendarDay.from(calendar);
                    cl.add(calendarDay);

                }

                progress.setVisibility(View.GONE);

                calendarView.addDecorators(new EventDecorator(Color.GREEN, cl));

            }

            @Override
            public void onFailure(Call<MeetingBean> call, Throwable t) {
                progress.setVisibility(View.GONE);
            }
        });


    }


    private class EventDecorator implements DayViewDecorator {

        private final int color;
        private final HashSet<CalendarDay> dates;

        public EventDecorator(int color, Collection<CalendarDay> dates) {
            this.color = color;
            this.dates = new HashSet<>(dates);
        }

        @Override
        public boolean shouldDecorate(CalendarDay day) {
            return dates.contains(day);
        }

        @Override
        public void decorate(DayViewFacade view) {
            view.addSpan(new DotSpan(15, color));
        }
    }

}
