package com.technobrix.tbx.safedoors;

import android.content.Context;
import android.graphics.Color;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.technobrix.tbx.safedoors.DeletePOJO.DeleteBean;
import com.technobrix.tbx.safedoors.StaffListPOJO.StaffList;
import com.technobrix.tbx.safedoors.StaffListPOJO.staffListBean;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class AddStaff extends AppCompatActivity {

    Toolbar toolbar;

    RecyclerView grid;
    GridLayoutManager manager;
    StaffAdapter adapter;


    List<StaffList> list;

    ConnectionDetector cd;

    ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_staff);

        list = new ArrayList<>();


        cd = new ConnectionDetector(this);


        progress = (ProgressBar) findViewById(R.id.progress);

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
        toolbar.setTitle("Add Your Staff");

        grid = (RecyclerView) findViewById(R.id.grid);
        manager = new GridLayoutManager(this, 1);
        adapter = new StaffAdapter(this, list);
        grid.setLayoutManager(manager);
        grid.setAdapter(adapter);

        if (cd.isConnectingToInternet()) {


            progress.setVisibility(View.VISIBLE);

            bean b = (bean) getApplicationContext();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://safedoors.in")
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            AllApiInterface cr = retrofit.create(AllApiInterface.class);

            Call<staffListBean> call = cr.getStaffList(b.socity);
            call.enqueue(new Callback<staffListBean>() {
                @Override
                public void onResponse(Call<staffListBean> call, Response<staffListBean> response) {


                    adapter.setGridData(response.body().getStaffList());


                    progress.setVisibility(View.GONE);

                }

                @Override
                public void onFailure(Call<staffListBean> call, Throwable t) {

                    progress.setVisibility(View.GONE);

                }
            });


        } else {
            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();
        }


    }


    public class StaffAdapter extends RecyclerView.Adapter<StaffAdapter.MyViewHolder> {


        Context context;

        List<StaffList> list = new ArrayList<>();


        public StaffAdapter(Context context, List<StaffList> list) {

            this.context = context;
            this.list = list;
        }

        public void setGridData(List<StaffList> list) {
            this.list = list;
            notifyDataSetChanged();
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


            View view = LayoutInflater.from(context).inflate(R.layout.staff_list_model, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {


            final StaffList item = list.get(position);


            holder.name.setText(item.getStaffName());
            holder.phone.setText(item.getPhone());
            holder.profess.setText(item.getProfesstion());


            holder.add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    final bean b = (bean) context.getApplicationContext();

                    progress.setVisibility(View.VISIBLE);

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://safedoors.in")
                            .addConverterFactory(ScalarsConverterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();


                    final AllApiInterface cr = retrofit.create(AllApiInterface.class);

                    Call<DeleteBean> call = cr.assign(b.socity, item.getStaffId(), b.house_id);

                    call.enqueue(new Callback<DeleteBean>() {
                        @Override
                        public void onResponse(Call<DeleteBean> call, Response<DeleteBean> response) {

                            Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();


                            progress.setVisibility(View.GONE);
                        }

                        @Override
                        public void onFailure(Call<DeleteBean> call, Throwable t) {
                            progress.setVisibility(View.GONE);
                        }
                    });


                }
            });


        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {

            TextView name, profess, phone;

            Button add;


            public MyViewHolder(View itemView) {
                super(itemView);

                name = itemView.findViewById(R.id.name);
                profess = itemView.findViewById(R.id.professional);
                phone = itemView.findViewById(R.id.phone);
                add = itemView.findViewById(R.id.add);

            }
        }
    }

}



