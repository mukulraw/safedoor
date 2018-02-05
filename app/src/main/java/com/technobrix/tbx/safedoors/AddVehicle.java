package com.technobrix.tbx.safedoors;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
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
import com.technobrix.tbx.safedoors.AddVehiclePOJO.AddBean;
import com.technobrix.tbx.safedoors.CategoryPOJO.CategoryBean;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class AddVehicle extends AppCompatActivity {


    Toolbar toolbar;
    EditText name , vehicle , novehicle;
    Button add;
    ProgressBar bar;
    Spinner spinner;

    List<String>list;
    List<String>id;

    ConnectionDetector cd;
    String spin = "";
    String idd = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vehicle);

        cd = new ConnectionDetector(AddVehicle.this);

        list = new ArrayList<>();
        id = new ArrayList<>();

        toolbar = (Toolbar) findViewById(R.id .toolbar);
        spinner = (Spinner) findViewById(R.id.spinner);

        name = (EditText) findViewById(R.id .name);
        vehicle = (EditText) findViewById(R.id .vehicle);
        novehicle = (EditText) findViewById(R.id .novehicle);
        bar = (ProgressBar) findViewById(R.id.bar);
        add = (Button) findViewById(R.id .add);
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

        toolbar.setTitle("Add Vehicle");



        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {


                    spin = list.get(i);


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {


            }
        });


        if (cd.isConnectingToInternet()){
            bar.setVisibility(View.VISIBLE);

            bean b = (bean)getApplicationContext();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://safedoors.in")
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            AllApiInterface cr = retrofit.create(AllApiInterface.class);


            Log.d("fkjs" , b.userId);
            Call<CategoryBean> call = cr.category(b.socity);

            call.enqueue(new Callback<CategoryBean>() {
                @Override
                public void onResponse(Call<CategoryBean> call, Response<CategoryBean> response) {

                    list.clear();
                    id.clear();

                    for (int i = 0; i<response.body().getCategoryList().size(); i++){
                        list.add(response.body().getCategoryList().get(i).getName());
                        id.add(response.body().getCategoryList().get(i).getId());
                    }



                    ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(AddVehicle.this, android.R.layout.simple_list_item_1, list);

                    dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    spinner.setAdapter(dataAdapter);

                    bar.setVisibility(View.GONE);

                }

                @Override
                public void onFailure(Call<CategoryBean> call, Throwable t) {

                    bar.setVisibility(View.GONE);

                }
            });

        }else {
            Toast.makeText(AddVehicle.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }











        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (cd.isConnectingToInternet()){



                    String n = name.getText().toString();
                    String v = vehicle.getText().toString();
                    String nv = novehicle.getText().toString();


                    if (n.length()>0){


                        if (nv.length()>0){
                            bar.setVisibility(View.VISIBLE);
                            Log.d("hii" , "hello");

                            bean b = (bean)getApplicationContext();

                            Retrofit retrofit = new Retrofit.Builder()
                                    .baseUrl("http://safedoors.in")
                                    .addConverterFactory(ScalarsConverterFactory.create())
                                    .addConverterFactory(GsonConverterFactory.create())
                                    .build();

                            AllApiInterface cr = retrofit.create(AllApiInterface.class);


                            Log.d("fkjs" , b.userId);
                            Call<AddFamilyBean> call = cr.addbean(b.userId , b.house_id , n , "" , b.socity , "" ,nv , spin);
                            call.enqueue(new Callback<AddFamilyBean>() {
                                @Override
                                public void onResponse(Call<AddFamilyBean> call, Response<AddFamilyBean> response) {

                                    Toast.makeText(AddVehicle.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();

                                    bar.setVisibility(View.GONE);
                                    finish();

                                    Log.d("nisha", "response");

                                }

                                @Override
                                public void onFailure(Call<AddFamilyBean> call, Throwable t) {

                                    bar.setVisibility(View.GONE);


                                    Log.d("mukul" , t.toString());
                                }
                            });



                        }
                        else {

                            Toast.makeText(AddVehicle.this, "Invalid Vehicle", Toast.LENGTH_SHORT).show();
                        }




                    }
                    else {
                        Toast.makeText(AddVehicle.this, "Invalid Vehicle Name", Toast.LENGTH_SHORT).show();
                    }


                }else {
                    Toast.makeText(AddVehicle.this, "No Internet Connection", Toast.LENGTH_SHORT).show();

                }




            }
        });
    }
}
