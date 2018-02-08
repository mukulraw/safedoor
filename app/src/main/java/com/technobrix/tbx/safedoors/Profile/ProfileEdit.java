package com.technobrix.tbx.safedoors.Profile;

import android.app.Dialog;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DialogTitle;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.technobrix.tbx.safedoors.AllApiInterface;
import com.technobrix.tbx.safedoors.ConnectionDetector;
import com.technobrix.tbx.safedoors.ProfilePOJO.SetProfileBean;
import com.technobrix.tbx.safedoors.R;
import com.technobrix.tbx.safedoors.SplashLogin.Register;
import com.technobrix.tbx.safedoors.bean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ProfileEdit extends AppCompatActivity {

    EditText name , dob  , address;

    Spinner gender , parking;

    Toolbar toolbar;

    String g , p;

    Button create;

    LinearLayout dobb ;

    List<String> gend , park;

    TextView dd , age ;
    EditText fullname;

    String date1 = "";
    String pa = "";

    ProgressBar bar;

    ConnectionDetector cd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_edit);
        park =new ArrayList<>();

        park.add("0");
        park.add("1");
        park.add("2");
        park.add("3");
        park.add("4");
        park.add("5");
        park.add("6");

        cd = new ConnectionDetector(ProfileEdit.this);
        name = (EditText) findViewById(R.id.fn);

        dobb = (LinearLayout) findViewById(R.id.dob);
        fullname = (EditText) findViewById(R.id.fullname);
        dd = (TextView) findViewById(R.id.dd);
        age = (TextView) findViewById(R.id.age);

        bar = (ProgressBar) findViewById(R.id.progress);


        gend = new ArrayList<>();
        gend.add("MALE");
        gend.add("FEMALE");


        gender = (Spinner) findViewById(R.id.male);
        parking = (Spinner) findViewById(R.id.spinner);
        address = (EditText) findViewById(R.id.pa);
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

        toolbar.setTitle("EDIT");



        dd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(ProfileEdit.this);
                dialog.setContentView(R.layout.dobb_dialog);

                dialog.setCancelable(true);
                dialog.show();



                final DatePicker picker = dialog.findViewById(R.id.picker);
                final Button ok = dialog.findViewById(R.id.ok);

                long now = System.currentTimeMillis() - 1000;

                picker.setMaxDate(now);

                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {




                        String day = String.valueOf(picker.getDayOfMonth());
                        String month = String.valueOf(picker.getMonth() + 1);
                        String year = String.valueOf(picker.getYear());

                        Calendar today = Calendar.getInstance();

                        date1 = day + "-" + month + "-" + year;

                        dd.setText(day + "-" + month + "-" + year);


                        int ag = today.get(Calendar.YEAR) - picker.getYear();

                        age.setText(String.valueOf(ag));

                        dialog.dismiss();

                    }
                });
            }
        });


        ArrayAdapter<String> adapter = new ArrayAdapter<String>(ProfileEdit.this, android.R.layout.simple_list_item_1, gend);

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



        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(ProfileEdit.this, android.R.layout.simple_list_item_1, park);

        parking.setAdapter(adapter1);

        parking.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                pa = park.get(i);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        create = (Button) findViewById(R.id.create);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (cd.isConnectingToInternet()){
                    String aa = address.getText().toString();
                    String a = age.getText().toString();
                    String f = fullname.getText().toString();
                    String pa = "";

                    bar.setVisibility(View.VISIBLE);
                    bean b = (bean)getApplicationContext();

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://safedoors.in")
                            .addConverterFactory(ScalarsConverterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();

                    AllApiInterface cr = retrofit.create(AllApiInterface.class);

                    Call<SetProfileBean> call = cr.setprofile(b.userId , b.socity , g , dd.getText().toString() , aa , a ,pa ,b.house_id, f);

                    Log.d("dfjgjkd" , b.userId);
                    Log.d("nfldhb" , b.socity);
                    Log.d("nglkfd" , b.gender);
                    Log.d("nlkfg" , b.dob);
                    Log.d("gvkvfs" , b.address);
                    Log.d("dgvlkfsb" , b.age);

                    call.enqueue(new Callback<SetProfileBean>() {
                        @Override
                        public void onResponse(Call<SetProfileBean> call, Response<SetProfileBean> response) {

                            Toast.makeText(ProfileEdit.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                            finish();

                            bar.setVisibility(View.GONE);

                        }

                        @Override
                        public void onFailure(Call<SetProfileBean> call, Throwable t) {

                            bar.setVisibility(View.GONE);


                        }
                    });



                }else {
                    Toast.makeText(ProfileEdit.this, "No Internet Connection", Toast.LENGTH_SHORT).show();
                }





            }
        });


    }
}
