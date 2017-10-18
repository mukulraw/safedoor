package com.technobrix.tbx.safedoors;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.technobrix.tbx.safedoors.ProfilePOJO.SetFamilyBean;
import com.technobrix.tbx.safedoors.SplashLogin.Login;

import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class GateKeeper extends AppCompatActivity {


    EditText name , purpuse  ;

    Spinner member;

    ImageView image , log;

    Button submit;

    ProgressBar bar;

    TextView cap;

    SharedPreferences pref;
    SharedPreferences.Editor edit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gate_keeper);
        pref = getSharedPreferences("hjdf" , MODE_PRIVATE);
        edit = pref.edit();

        name = (EditText) findViewById(R.id.name);
        purpuse = (EditText) findViewById(R.id.purpuse);
        member = (Spinner) findViewById(R.id.member);
        image = (ImageView) findViewById(R.id.image);
        cap = (TextView) findViewById(R.id.capture);
        submit = (Button) findViewById(R.id.submit);
        bar = (ProgressBar) findViewById(R.id.progress);
        log = (ImageView) findViewById(R.id.log);

        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(GateKeeper.this , Login.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);

                edit.remove("user");
                edit.remove("type");
                edit.remove("pass");
                edit.apply();


                bean b = (bean)getApplicationContext();

                b.name = "";
                b.userId = "";
                b.email = "";

                startActivity(i);
                finish();




            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String n = name.getText().toString();
                String p = purpuse.getText().toString();


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
                Call<SetFamilyBean> call = cr.setfamily(b.userId, b.socity , b.house_id ,"1" , n  , p ,date , time ,"1" );

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
