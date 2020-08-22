package com.technobrix.tbx.safedoors;

import android.graphics.Color;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.technobrix.tbx.safedoors.FeedbackPOJO.FeedbackBean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Feedback extends AppCompatActivity {

    Toolbar toolbar;

    EditText name , email , phone , des;

    Button submit;

    ProgressBar bar;

    ConnectionDetector cd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        cd = new ConnectionDetector(Feedback.this);

        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        phone = (EditText) findViewById(R.id.phone);
        des = (EditText) findViewById(R.id.des);
        submit = (Button) findViewById(R.id.submit);
        bar = (ProgressBar) findViewById(R.id.bar);
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
        final bean b = (bean)getApplicationContext();



        name.setText(b.name);
        email.setText(b.email);
        phone.setText(b.phone);




        toolbar.setTitle("FeedBack");


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (cd.isConnectingToInternet()){

                    String n = name.getText().toString();
                    String e = email.getText().toString();
                    String p = phone.getText().toString();
                    String d = des.getText().toString();


                    if (n.length()>0){

                        if (Utils.isValidMail(e)){

                            if (Utils.isValidMobile(p)){

                                if (d.length()>0){

                                    bar.setVisibility(View.VISIBLE);


                                    Retrofit retrofit = new Retrofit.Builder()
                                            .baseUrl("http://safedoors.in")
                                            .addConverterFactory(ScalarsConverterFactory.create())
                                            .addConverterFactory(GsonConverterFactory.create())
                                            .build();

                                    AllApiInterface cr = retrofit.create(AllApiInterface.class);

                                    Call<FeedbackBean> call = cr.feedback(b.socity , n , e , p , d);

                                    call.enqueue(new Callback<FeedbackBean>() {
                                        @Override
                                        public void onResponse(Call<FeedbackBean> call, Response<FeedbackBean> response) {

                                            if (response.body().getStatus() == 1)
                                            {
                                                name.setText("");
                                                email.setText("");
                                                phone.setText("");
                                                des.setText("");
                                            }

                                            Toast.makeText(Feedback.this,response.body().getMessage(), Toast.LENGTH_SHORT).show();

                                            bar.setVisibility(View.GONE);

                                        }

                                        @Override
                                        public void onFailure(Call<FeedbackBean> call, Throwable t) {

                                            bar.setVisibility(View.GONE);

                                        }
                                    });

                                }
                                else {

                                    Toast.makeText(Feedback.this, "Please enter a valid Description", Toast.LENGTH_SHORT).show();
                                }


                            }
                            else{

                                Toast.makeText(Feedback.this, "Please enter a valid Phone", Toast.LENGTH_SHORT).show();
                            }


                        }
                        else {
                            Toast.makeText(Feedback.this, "Invalid Email", Toast.LENGTH_SHORT).show();
                        }


                    }
                    else {
                        Toast.makeText(Feedback.this, "Please enter a valid Name", Toast.LENGTH_SHORT).show();
                    }




                }else {
                    Toast.makeText(Feedback.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
                }



            }
        });

    }
}
