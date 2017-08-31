package com.example.tvs.safedoors.SplashLogin;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.example.tvs.safedoors.R;

public class Register extends AppCompatActivity {

    EditText user,email,mobile,house,password,repass;

    TextView ca;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        user = (EditText) findViewById(R.id.user);
        email = (EditText) findViewById(R.id.email);
        mobile = (EditText) findViewById(R.id.mobile);
        house = (EditText) findViewById(R.id.house);
        password = (EditText) findViewById(R.id.password);
        repass = (EditText) findViewById(R.id.repass);
        ca = (TextView) findViewById(R.id.ca);
    }
}
