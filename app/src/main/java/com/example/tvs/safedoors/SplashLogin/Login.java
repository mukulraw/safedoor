package com.example.tvs.safedoors.SplashLogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.tvs.safedoors.MainActivity;
import com.example.tvs.safedoors.R;

public class Login extends AppCompatActivity {

    EditText mail,pass;
    TextView create,forget,signin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mail = (EditText) findViewById(R.id.mail);
        pass  = (EditText) findViewById(R.id.pass);
        create  = (TextView) findViewById(R.id.create);
        signin  = (TextView) findViewById(R.id.signin);
        forget  = (TextView) findViewById(R.id.forget);

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Login . this , Register.class);
                startActivity(i);
            }
        });
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Login.this , MainActivity.class);
                startActivity(i);
                finish();
            }
        });

    }
}
