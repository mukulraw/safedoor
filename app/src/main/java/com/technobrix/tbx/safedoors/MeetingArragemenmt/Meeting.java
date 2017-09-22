package com.technobrix.tbx.safedoors.MeetingArragemenmt;

import android.app.Dialog;
import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.technobrix.tbx.safedoors.AllApiInterface;
import com.technobrix.tbx.safedoors.Create_MeetingPOJO.CreateBean;
import com.technobrix.tbx.safedoors.LoginPOJO.LoginBean;
import com.technobrix.tbx.safedoors.MainActivity;
import com.technobrix.tbx.safedoors.MeetingPOJO.MeetingBean;
import com.technobrix.tbx.safedoors.R;

import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class Meeting extends Fragment {

    LinearLayout title,starttime,endtime;
    EditText meeting, meet;
    TextView submit;
    Context context;

    ProgressBar bar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.meeting , container , false);

        meet = (EditText)view.findViewById(R.id.meetingtitle) ;
        meeting = (EditText)view.findViewById(R.id.meeetingd) ;
        bar = (ProgressBar)view.findViewById(R.id.progress);

        title = (LinearLayout)view.findViewById(R.id.meeting);
        starttime = (LinearLayout)view.findViewById(R.id.starttime);
        endtime = (LinearLayout)view.findViewById(R.id.endtime);
        submit = (TextView)view.findViewById(R.id.submit);

        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.meetingdialog);
                dialog.setCancelable(true);
                dialog.show();

                TextView date1 = (TextView)dialog.findViewById(R.id.tex2);


            }
        });

        starttime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.meetingdialog);
                dialog.setCancelable(true);
                dialog.show();

               TextView date2 = (TextView)dialog.findViewById(R.id.text5);


            }
        });

        endtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Dialog dialog = new Dialog(getActivity());
                dialog.setContentView(R.layout.meetingdialog);
                dialog.setCancelable(true);
                dialog.show();

                TextView date3 = (TextView)dialog.findViewById(R.id.text5);


            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String m = meet.getText().toString();
                String m1 = meeting.getText().toString();
                bar.setVisibility(View.VISIBLE);

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://safedoors.in")
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                AllApiInterface cr = retrofit.create(AllApiInterface.class);
                Call<CreateBean> call = cr.create(m , m1);
                call.enqueue(new Callback<CreateBean>() {
                    @Override
                    public void onResponse(Call<CreateBean> call, Response<CreateBean> response) {

                        Toast.makeText(getContext() , response.body().getStatus() , Toast.LENGTH_SHORT).show();
                        bar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onFailure(Call<CreateBean> call, Throwable t) {
                        bar.setVisibility(View.GONE);

                    }
                });
            }
        });

        return view;
    }
}
