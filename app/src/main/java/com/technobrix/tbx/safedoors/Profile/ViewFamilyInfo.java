package com.technobrix.tbx.safedoors.Profile;

import android.graphics.Color;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.technobrix.tbx.safedoors.AllApiInterface;
import com.technobrix.tbx.safedoors.DeleteFamily.DeleteFamilyBean;
import com.technobrix.tbx.safedoors.R;
import com.technobrix.tbx.safedoors.SetFamilyPOJO.SetFamilyBean;
import com.technobrix.tbx.safedoors.bean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ViewFamilyInfo extends AppCompatActivity {


    Toolbar toolbar;

    EditText username, age , gender , relation , user , pass;

    Button submit;

    ProgressBar bar;

    String id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_family_info);

        Bundle b = getIntent().getExtras();

        id = b.getString("id");


        submit = (Button) findViewById(R.id.submit);



        age = (EditText) findViewById(R.id.age);

        bar = (ProgressBar) findViewById(R.id.bar);

        gender = (EditText) findViewById(R.id.gender);

        relation = (EditText) findViewById(R.id.relation);

        user = (EditText) findViewById(R.id.user);

        username = (EditText) findViewById(R.id.username);

        pass = (EditText) findViewById(R.id.pass);

        bar = (ProgressBar) findViewById(R.id.progress);

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



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                String u = username.getText().toString();
                String g = gender.getText().toString();
                String a = age.getText().toString();
                String r = relation.getText().toString();


                bar.setVisibility(View.VISIBLE);

                bean b = (bean)getApplicationContext();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://safedoors.in")
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                AllApiInterface cr = retrofit.create(AllApiInterface.class);

                Log.d("famil" , b.family_id);

                Call<SetFamilyBean> call = cr.setfamily1(b.userId ,g , a , r , b.house_id  ,id ,b.socity , u);

                call.enqueue(new Callback<SetFamilyBean>() {
                    @Override
                    public void onResponse(Call<SetFamilyBean> call, Response<SetFamilyBean> response) {


                        Toast.makeText(ViewFamilyInfo.this,response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        bar.setVisibility(View.GONE);
                    }

                    @Override
                    public void onFailure(Call<SetFamilyBean> call, Throwable t) {

                        bar.setVisibility(View.GONE);

                    }
                });



            }
        });

        String n = b.getString("username");
        String a = b.getString("age");
        String male = b.getString("male");
        String r = b.getString("son");


        username.setText(n);
        age.setText(a);
        gender.setText(male);
        relation.setText(r);


        toolbar.setTitle("Edit Family Info");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.delete , menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int idd = item.getItemId();

        if (idd == R.id.delete)
        {

            bar.setVisibility(View.VISIBLE);

            bean b = (bean)getApplicationContext();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://safedoors.in")
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            AllApiInterface cr = retrofit.create(AllApiInterface.class);

            Call<DeleteFamilyBean> call = cr.del(b.socity ,b.userId , b.house_id , id);

            Log.d("socity" , b.socity);
            Log.d("userid" , b.userId);
            Log.d("houseId" , b.house_id);
            Log.d("id" , id);

            call.enqueue(new Callback<DeleteFamilyBean>() {
                @Override
                public void onResponse(Call<DeleteFamilyBean> call, Response<DeleteFamilyBean> response) {

                    Toast.makeText(ViewFamilyInfo.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    bar.setVisibility(View.GONE);

                }

                @Override
                public void onFailure(Call<DeleteFamilyBean> call, Throwable t) {

                    bar.setVisibility(View.GONE);

                }
            });

        }

        return super.onOptionsItemSelected(item);
    }



}
