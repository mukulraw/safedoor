package com.technobrix.tbx.safedoors.SplashLogin;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.technobrix.tbx.safedoors.AllApiInterface;
import com.technobrix.tbx.safedoors.GateKeeper;
import com.technobrix.tbx.safedoors.LoginPOJO.LoginBean;
import com.technobrix.tbx.safedoors.MainActivity;
import com.technobrix.tbx.safedoors.R;
import com.technobrix.tbx.safedoors.bean;

import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Splash extends AppCompatActivity {
    Timer time;

    SharedPreferences pref;

    ProgressBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        pref  = getSharedPreferences("pref" , Context.MODE_PRIVATE);

        bar = (ProgressBar) findViewById(R.id.progress);


        String email = pref.getString("email" , "");
        String pass = pref.getString("pass" , "");



        if (email.length()>0 && pass.length()>0)
        {

            bar.setVisibility(View.VISIBLE);

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://safedoors.in")
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            AllApiInterface cr = retrofit.create(AllApiInterface.class);
            Call<LoginBean> call = cr.login( email , pass);
            call.enqueue(new Callback<LoginBean>() {
                @Override
                public void onResponse(Call<LoginBean> call, Response<LoginBean> response) {


                    if (Objects.equals(response.body().getMessage(), "Login success")){


                        if (Objects.equals(response.body().getType(), "member"))
                        {

                            bean b = (bean)getApplicationContext();

                            b.userId = response.body().getUserid();

                            b.name = response.body().getSocityName();

                            b.socity = response.body().getSocityId();

                            b.house_id = response.body().getHouseNo();




                            Toast.makeText(Splash.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(Splash.this, MainActivity.class);
                            bar.setVisibility(View.GONE);
                            startActivity(i);
                            finish();

                        }
                        else if (Objects.equals(response.body().getType(), "gatekeeper"))
                        {
                            bean b = (bean)getApplicationContext();
                            b.userId = response.body().getUserid();
                            b.name = response.body().getSocityName();
                            b.socity = response.body().getSocityId();
                            Toast.makeText(Splash.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(Splash.this, GateKeeper.class);
                            bar.setVisibility(View.GONE);
                            startActivity(i);
                            finish();

                        }

                    }
                    else {
                        Toast.makeText(Splash.this, "Login detail invalid", Toast.LENGTH_SHORT).show();
                        bar.setVisibility(View.GONE);
                    }

                }

                @Override
                public void onFailure(Call<LoginBean> call, Throwable t) {
                    bar.setVisibility(View.GONE);


                }
            });

        }
        else {

            time = new Timer();
            time.schedule(new TimerTask() {
                @Override
                public void run() {

                    Intent i = new Intent(Splash.this , GetStarted.class);
                    startActivity(i);
                    finish();

                }
            } , 1500);

        }






    }
}
