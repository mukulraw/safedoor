package com.technobrix.tbx.safedoors.SplashLogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.technobrix.tbx.safedoors.R;

import java.util.Timer;
import java.util.TimerTask;

public class Splash extends AppCompatActivity {
    Timer time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
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
