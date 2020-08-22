package com.technobrix.tbx.safedoors.Desscusion;

import android.graphics.Color;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.technobrix.tbx.safedoors.AllApiInterface;
import com.technobrix.tbx.safedoors.CommentListPOJO.CommentList;
import com.technobrix.tbx.safedoors.CommentListPOJO.CommentsBean;
import com.technobrix.tbx.safedoors.R;
import com.technobrix.tbx.safedoors.TopicPOJO.TopiBean;
import com.technobrix.tbx.safedoors.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class HelpOne extends AppCompatActivity {

    RecyclerView recyclerView;
    LinearLayoutManager manager;

    TextView subject;
    HelpOneAdapter adapeter;
    Toolbar toolbar;
    ProgressBar bar;
    List<CommentList> list;
    FloatingActionButton arrow;
    EditText type;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_one2);

        bar = (ProgressBar) findViewById(R.id.bar);

        recyclerView = (RecyclerView) findViewById(R.id.helpdesk1);

        type = (EditText) findViewById(R.id.type);

        arrow = (FloatingActionButton) findViewById(R.id.arrow);
        subject = (TextView) findViewById(R.id.subject);

        manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

        list = new ArrayList<>();

        adapeter = new HelpOneAdapter(this, list);

        recyclerView.setAdapter(adapeter);

        recyclerView.setLayoutManager(manager);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        toolbar.setTitleTextColor(Color.WHITE);

        toolbar.setNavigationIcon(R.drawable.arrow);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        toolbar.setTitle(getIntent().getStringExtra("subject"));

        subject.setText(getIntent().getStringExtra("body"));

        loadComments();

        arrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                final String ty = type.getText().toString();

                if (ty.length() > 0) {
                    bar.setVisibility(View.VISIBLE);

                    bean b = (bean) getApplicationContext();

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://safedoors.in")
                            .addConverterFactory(ScalarsConverterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    AllApiInterface cr = retrofit.create(AllApiInterface.class);

                    Call<TopiBean> call = cr.topi(b.socity, getIntent().getStringExtra("topic"), b.userId, ty);

                    call.enqueue(new Callback<TopiBean>() {
                        @Override
                        public void onResponse(Call<TopiBean> call, Response<TopiBean> response) {

                            type.setText("");

                            //loadComments();

                            bar.setVisibility(View.GONE);

                        }

                        @Override
                        public void onFailure(Call<TopiBean> call, Throwable t) {

                            bar.setVisibility(View.GONE);

                        }
                    });

                }


            }
        });


    }

    public void loadComments() {

        Timer t = new Timer();
        t.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                bean b = (bean) getApplicationContext();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://safedoors.in")
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                AllApiInterface cr = retrofit.create(AllApiInterface.class);

                Call<CommentsBean> call = cr.comment(b.socity, getIntent().getStringExtra("topic"), b.userId);

                call.enqueue(new Callback<CommentsBean>() {
                    @Override
                    public void onResponse(Call<CommentsBean> call, Response<CommentsBean> response) {


                        try {
                            if (response.body().getCommentList() != null) {
                                adapeter.set(response.body().getCommentList());


                                if (response.body().getCommentList().size() > count) {
                                    recyclerView.smoothScrollToPosition(adapeter.getItemCount() - 1);
                                    count = response.body().getCommentList().size();
                                }


                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }

                    @Override
                    public void onFailure(Call<CommentsBean> call, Throwable t) {


                    }
                });


            }
        }, 0, 1000);

    }

}
