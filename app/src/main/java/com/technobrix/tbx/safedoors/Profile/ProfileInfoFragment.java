package com.technobrix.tbx.safedoors.Profile;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.technobrix.tbx.safedoors.AllApiInterface;
import com.technobrix.tbx.safedoors.LoginPOJO.LoginBean;
import com.technobrix.tbx.safedoors.ProfilePOJO.ProfileBean;
import com.technobrix.tbx.safedoors.R;
import com.technobrix.tbx.safedoors.bean;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class ProfileInfoFragment extends Fragment {

    TextView email , dob , fn, pa, male, edit;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.profile_info_fragment , container , false);

        email = (TextView)view.findViewById(R.id.email);
        dob = (TextView)view.findViewById(R.id.dob);
        fn = (TextView)view.findViewById(R.id.fn);
        pa = (TextView)view.findViewById(R.id.pa);
        male = (TextView)view.findViewById(R.id.male);
        edit = (TextView)view.findViewById(R.id.edit);

        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), ProfileEdit.class);
                startActivity(i);
            }
        });

        bean b = (bean)getContext().getApplicationContext();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://safedoors.in")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AllApiInterface cr = retrofit.create(AllApiInterface.class);
        Call<ProfileBean> call = cr.getprofile(b.userId);
        call.enqueue(new Callback<ProfileBean>() {
            @Override
            public void onResponse(Call<ProfileBean> call, Response<ProfileBean> response) {

                email.setText(response.body().getEmail());
                dob.setText(response.body().getDob());
                pa.setText(response.body().getPermanentAddress());
                male.setText(response.body().getGender());


            }

            @Override
            public void onFailure(Call<ProfileBean> call, Throwable t) {

            }
        });
        return  view;
    }
}
