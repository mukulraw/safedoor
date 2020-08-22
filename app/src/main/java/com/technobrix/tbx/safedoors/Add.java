package com.technobrix.tbx.safedoors;

import android.graphics.Color;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.technobrix.tbx.safedoors.AddFamilyPOJO.AddFamilyBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Add extends AppCompatActivity {

    EditText name, username, age, relation, user, pass, phone, email;

    Spinner gender, spinner;

    Button submit;

    ProgressBar bar;

    Toolbar toolbar;

    List<String> gend, list;

    String g, li;

    ConnectionDetector cd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        cd = new ConnectionDetector(Add.this);

        gend = new ArrayList<>();
        list = new ArrayList<>();


        gend.add("MALE");
        gend.add("FEMALE");

        gender = (Spinner) findViewById(R.id.gender);
        spinner = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(Add.this, android.R.layout.simple_list_item_1, gend);

        gender.setAdapter(adapter);

        gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                g = gend.get(i);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        list.add("Select Relation");
        list.add("Mother");
        list.add("Father");
        list.add("Brother");
        list.add("Sister");
        list.add("Wife");
        list.add("Husband");
        list.add("Niece");
        list.add("Nephew");


        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(Add.this, android.R.layout.simple_list_item_1, list);

        spinner.setAdapter(adapter1);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


                if (i > 0)
                {
                    li = list.get(i);
                }
                else
                {li = "";

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        name = (EditText) findViewById(R.id.name);

        username = (EditText) findViewById(R.id.username);

        age = (EditText) findViewById(R.id.age);

        bar = (ProgressBar) findViewById(R.id.bar);


        relation = (EditText) findViewById(R.id.relation);
        email = (EditText) findViewById(R.id.email);
        phone = (EditText) findViewById(R.id.phone);

        user = (EditText) findViewById(R.id.user);

        pass = (EditText) findViewById(R.id.pass);

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


        toolbar.setTitle("Member");

        submit = (Button) findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (cd.isConnectingToInternet()) {
                    Log.d("hmm", "response");

                    //String u = user.getText().toString();
                    String n = name.getText().toString();
                    String u = username.getText().toString();
                    String a = age.getText().toString();
                    String r = relation.getText().toString();
                    String p = pass.getText().toString();
                    String ph = phone.getText().toString();
                    String e = email.getText().toString();

                    if (n.length() > 0) {

                        if (u.length() > 0) {

                            if (e.length() > 0) {

                                if (a.length() > 0) {

                                    if (li.length() > 0) {

                                        if (Utils.isValidMobile(ph)) {

                                            if (p.length() > 0) {
                                                bar.setVisibility(View.VISIBLE);

                                                bean b = (bean) getApplicationContext();

                                                Retrofit retrofit = new Retrofit.Builder()
                                                        .baseUrl("http://safedoors.in")
                                                        .addConverterFactory(ScalarsConverterFactory.create())
                                                        .addConverterFactory(GsonConverterFactory.create())
                                                        .build();

                                                AllApiInterface cr = retrofit.create(AllApiInterface.class);

                                                Call<AddFamilyBean> call = cr.add(b.userId, n, g, a, li, b.socity, b.house_id, e, p, u, ph);

                                                Log.d("soc", b.socity);
                                                Log.d("house", b.house_id);

                                                call.enqueue(new Callback<AddFamilyBean>() {
                                                    @Override
                                                    public void onResponse(Call<AddFamilyBean> call, Response<AddFamilyBean> response) {

                                                        if (Objects.equals(response.body().getStatus(), "4")) {
                                                            bean b = (bean) getApplicationContext();

                                                            Toast.makeText(Add.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                                                            bar.setVisibility(View.GONE);

                                                            Log.d("gkfsdg", "mukul");
                                                            finish();
                                                        } else {
                                                            bean b = (bean) getApplicationContext();

                                                            Toast.makeText(Add.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                                                            bar.setVisibility(View.GONE);

                                                            Log.d("gkfsdg", "mukul");


                                                        }


                                                    }

                                                    @Override
                                                    public void onFailure(Call<AddFamilyBean> call, Throwable t) {

                                                        bar.setVisibility(View.GONE);

                                                        Log.d("dfhglf", t.toString());

                                                    }
                                                });


                                            } else {

                                                Toast.makeText(Add.this, "Invalid Password", Toast.LENGTH_SHORT).show();
                                            }


                                        } else {

                                            Toast.makeText(Add.this, "Invalid Phone", Toast.LENGTH_SHORT).show();
                                        }

                                    } else {

                                        Toast.makeText(Add.this, "Please Choose a Relation", Toast.LENGTH_SHORT).show();
                                    }


                                } else {

                                    Toast.makeText(Add.this, "Invalid Age", Toast.LENGTH_SHORT).show();
                                }


                            } else {

                                Toast.makeText(Add.this, "Invalid Email", Toast.LENGTH_SHORT).show();
                            }


                        } else {
                            Toast.makeText(Add.this, "Invalid Name", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(Add.this, "Invalid UserName", Toast.LENGTH_SHORT).show();
                    }


                } else {
                    Toast.makeText(Add.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }
}
