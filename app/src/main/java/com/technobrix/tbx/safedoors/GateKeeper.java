package com.technobrix.tbx.safedoors;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.technobrix.tbx.safedoors.ProfilePOJO.SetFamilyBean;

import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class GateKeeper extends AppCompatActivity {


    EditText name , purpuse , member ;

    ImageView image;

    Button submit;

    ProgressBar bar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_gate_keeper);

        name = (EditText) findViewById(R.id.name);
        purpuse = (EditText) findViewById(R.id.purpuse);
        member = (EditText) findViewById(R.id.member);
        image = (ImageView) findViewById(R.id.image);
        submit = (Button) findViewById(R.id.submit);
        bar = (ProgressBar) findViewById(R.id.progress);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String n = name.getText().toString();
                String p = purpuse.getText().toString();
                String m = member.getText().toString();

                Calendar cal = Calendar.getInstance();


                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                Date todayDate = new Date();
                todayDate.getDay();
                todayDate.getHours();
                todayDate.getMinutes();
                todayDate.getMonth();
                todayDate.getTime();



                String date = String.valueOf(year) + "-" + String.valueOf(month) + "-" + String.valueOf(day);

                String time = ""+cal.get(Calendar.HOUR_OF_DAY)+":"+cal.get(Calendar.MINUTE);

                bar.setVisibility(View.VISIBLE);


                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://safedoors.in")
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                bean b = (bean)getApplicationContext();

                AllApiInterface cr = retrofit.create(AllApiInterface.class);
                Call<SetFamilyBean> call = cr.setfamily(b.userId, b.socity , b.house_id , m , n  , p ,date , time ,"1" );

                call.enqueue(new Callback<SetFamilyBean>() {
                    @Override
                    public void onResponse(Call<SetFamilyBean> call, Response<SetFamilyBean> response) {

                        Toast.makeText(GateKeeper.this, "dsfs", Toast.LENGTH_SHORT).show();

                        bar.setVisibility(View.GONE);

                    }

                    @Override
                    public void onFailure(Call<SetFamilyBean> call, Throwable t) {

                        bar.setVisibility(View.GONE);

                    }
                });

            }
        });
    }
}
