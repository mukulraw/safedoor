package com.technobrix.tbx.safedoors.Profile;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.technobrix.tbx.safedoors.ConnectionDetector;
import com.technobrix.tbx.safedoors.R;

public class Edit extends AppCompatActivity {

    EditText name , gender , dob , age , address;

    Button create;

    ProgressBar bar;

    ConnectionDetector cd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        cd = new ConnectionDetector(this);

        name = (EditText) findViewById(R.id.fn);
        gender = (EditText) findViewById(R.id.male);
        dob = (EditText) findViewById(R.id.dob);
        address = (EditText) findViewById(R.id.pa);
        age = (EditText) findViewById(R.id.age);
        create = (Button) findViewById(R.id.create);
        bar = (ProgressBar) findViewById(R.id.progress);

       /* if (cd.isConnectingToInternet()){

            String g = gender.getText().toString();
            String d = dob.getText().toString();
            String aa = address.getText().toString();
            String a = age.getText().toString();

            bar.setVisibility(View.VISIBLE);
            bean b = (bean)getApplicationContext();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://safedoors.in")
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            AllApiInterface cr = retrofit.create(AllApiInterface.class);

            Call<SetProfileBean> call = cr.setprofile(b.userId , b.socity , g , d , aa , a);

            call.enqueue(new Callback<SetProfileBean>() {
                @Override
                public void onResponse(Call<SetProfileBean> call, Response<SetProfileBean> response) {

                    Toast.makeText(Edit.this,response.body().getMessage(), Toast.LENGTH_SHORT).show();

                    bar.setVisibility(View.GONE);

                    finish();

                }

                @Override
                public void onFailure(Call<SetProfileBean> call, Throwable t) {


                    bar.setVisibility(View.GONE);
                }
            });

        }else {
            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }

*/

    }
}
