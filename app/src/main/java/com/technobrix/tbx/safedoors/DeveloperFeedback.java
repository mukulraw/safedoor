package com.technobrix.tbx.safedoors;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.technobrix.tbx.safedoors.DeveloperPOJO.DeveloperBean;
import com.technobrix.tbx.safedoors.FeedbackPOJO.FeedbackBean;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class DeveloperFeedback extends AppCompatActivity {

    Toolbar toolbar;

    EditText name , email , phone , comment;

    Spinner spinner;

    List<String>developer;

    String dev = "";

    Button submit;

    ProgressBar bar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer_feeback);

        name = (EditText) findViewById(R.id.name);
        email = (EditText) findViewById(R.id.email);
        phone = (EditText) findViewById(R.id.phone);
        comment = (EditText) findViewById(R.id.comment);
        spinner = (Spinner) findViewById(R.id.spinner);
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

        toolbar.setTitle("Developer Feedback");

        developer = new ArrayList<>();


        developer.add("Notice Board");
        developer.add("Developer FeedBack");
        developer.add("FeedBack");
        developer.add("Accounting");
        developer.add("Event");
        developer.add("Facility");
        developer.add("My Bookings");
        developer.add("Help Desk");
        developer.add("Discussion Form");
        developer.add("Inventory");
        developer.add("Profile");
        developer.add("GateKeeper");
        developer.add("Profile");


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(DeveloperFeedback.this, android.R.layout.simple_list_item_1, developer);

        bar.setVisibility(View.GONE);

        spinner.setAdapter(adapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                dev = developer.get(i);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });





        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String n = name.getText().toString();
                String e = email.getText().toString();
                String p = phone.getText().toString();
                String c = comment.getText().toString();
                String d = dev;


                if (n.length()>0){

                    if (Utils.isValidMail(e)){

                        if (Utils.isValidMobile(p)){

                            if (d.length()>0){

                                if (c.length()>0){
                                    bar.setVisibility(View.VISIBLE);

                                    bean b = (bean)getApplicationContext();

                                    Retrofit retrofit = new Retrofit.Builder()
                                            .baseUrl("http://safedoors.in")
                                            .addConverterFactory(ScalarsConverterFactory.create())
                                            .addConverterFactory(GsonConverterFactory.create())
                                            .build();

                                    AllApiInterface cr = retrofit.create(AllApiInterface.class);

                                    Call<DeveloperBean> call = cr.developer( n , e , p , dev , c);

                                    call.enqueue(new Callback<DeveloperBean>() {
                                        @Override
                                        public void onResponse(Call<DeveloperBean> call, Response<DeveloperBean> response) {

                                            if (response.body().getStatus() == 1)
                                            {
                                                name.setText("");
                                                email.setText("");
                                                phone.setText("");
                                                comment.setText("");

                                            }

                                            Toast.makeText(DeveloperFeedback.this,response.body().getMessage(), Toast.LENGTH_SHORT).show();

                                            bar.setVisibility(View.GONE);

                                        }

                                        @Override
                                        public void onFailure(Call<DeveloperBean> call, Throwable t) {

                                            bar.setVisibility(View.GONE);

                                        }
                                    });

                                }else {
                                    Toast.makeText(DeveloperFeedback.this, "Invalid Comment", Toast.LENGTH_SHORT).show();

                                }



                            }
                            else {

                                Toast.makeText(DeveloperFeedback.this, "Please enter a valid Description", Toast.LENGTH_SHORT).show();
                            }


                        }
                        else{

                            Toast.makeText(DeveloperFeedback.this, "Please enter a valid Phone", Toast.LENGTH_SHORT).show();
                        }


                    }
                    else {
                        Toast.makeText(DeveloperFeedback.this, "Invalid Email", Toast.LENGTH_SHORT).show();
                    }


                }
                else {
                    Toast.makeText(DeveloperFeedback.this, "Please enter a valid Name", Toast.LENGTH_SHORT).show();
                }




            }
        });



    }
}
