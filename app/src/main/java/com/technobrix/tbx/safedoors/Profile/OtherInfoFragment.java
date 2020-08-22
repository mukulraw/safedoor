package com.technobrix.tbx.safedoors.Profile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.technobrix.tbx.safedoors.AddFamilyPOJO.AddFamilyBean;
import com.technobrix.tbx.safedoors.AddVehicle;
import com.technobrix.tbx.safedoors.AllApiInterface;
import com.technobrix.tbx.safedoors.ConnectionDetector;
import com.technobrix.tbx.safedoors.GetVehiclePOJO.VehicleBean;
import com.technobrix.tbx.safedoors.GetVehiclePOJO.VehicleList;
import com.technobrix.tbx.safedoors.R;
import com.technobrix.tbx.safedoors.bean;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class OtherInfoFragment extends Fragment {

    RecyclerView recyclerView;
    GridLayoutManager manager;
    OtherAdapter adapter;
    List<VehicleList> list;
    ProgressBar bar;
    FloatingActionButton add;

    ConnectionDetector cd;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.other_info_fragment, container, false);
        cd = new ConnectionDetector(getContext());
        recyclerView = (RecyclerView) view.findViewById(R.id.other);

        bar = (ProgressBar) view.findViewById(R.id.bar);

        add = (FloatingActionButton) view.findViewById(R.id.add);
        manager = new GridLayoutManager(getContext(), 1);

        list = new ArrayList<>();
        adapter = new OtherAdapter(getContext(), list);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(manager);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getContext(), AddVehicle.class);
                startActivity(i);
            }
        });


        if (cd.isConnectingToInternet()) {
            bar.setVisibility(View.VISIBLE);
            bean b = (bean) getContext().getApplicationContext();

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("http://safedoors.in")
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            AllApiInterface cr = retrofit.create(AllApiInterface.class);

            Call<VehicleBean> call = cr.vehiclebean(b.house_id);

            call.enqueue(new Callback<VehicleBean>() {
                @Override
                public void onResponse(Call<VehicleBean> call, Response<VehicleBean> response) {


                    adapter.setgrid(response.body().getVehicleList());
                    bar.setVisibility(View.GONE);

                }

                @Override
                public void onFailure(Call<VehicleBean> call, Throwable t) {


                    bar.setVisibility(View.GONE);
                }


            });
        } else {

            Toast.makeText(getContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();

        }


        return view;
    }


    public class OtherAdapter extends RecyclerView.Adapter<OtherAdapter.myviewholder> {

        Context context;
        List<VehicleList> list = new ArrayList<>();

        public OtherAdapter(Context context, List<VehicleList> list) {

            this.list = list;
            this.context = context;
        }

        @Override
        public myviewholder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(context).inflate(R.layout.other_list_model, parent, false);
            return new myviewholder(view);
        }

        @Override
        public void onBindViewHolder(myviewholder holder, int position) {

            final VehicleList item = list.get(position);

            holder.name.setText(item.getVehicleName());
            holder.vehicleno.setText(item.getVehicleNo());
            holder.rfid.setText(item.getRfId());

            holder.close.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (cd.isConnectingToInternet()) {
                        bean b = (bean) context.getApplicationContext();

                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl("http://safedoors.in")
                                .addConverterFactory(ScalarsConverterFactory.create())
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();

                        AllApiInterface cr = retrofit.create(AllApiInterface.class);

                        Call<AddFamilyBean> call = cr.remove(item.getVehicleId(), b.flat, b.socity);

                        call.enqueue(new Callback<AddFamilyBean>() {
                            @Override
                            public void onResponse(Call<AddFamilyBean> call, Response<AddFamilyBean> response) {

                                Toast.makeText(context, String.valueOf(response.body().getMessage()), Toast.LENGTH_SHORT).show();


                            }

                            @Override
                            public void onFailure(Call<AddFamilyBean> call, Throwable t) {


                            }
                        });


                    } else {
                        Toast.makeText(context, "No Internet Connection", Toast.LENGTH_SHORT).show();
                    }


                }
            });


        }

        public void setgrid(List<VehicleList> list) {

            this.list = list;
            notifyDataSetChanged();
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        public class myviewholder extends RecyclerView.ViewHolder {

            TextView name, vehicleno, rfid;
            ImageButton close;

            public myviewholder(View itemView) {
                super(itemView);

                name = (TextView) itemView.findViewById(R.id.vname);
                vehicleno = (TextView) itemView.findViewById(R.id.vehicleno);
                rfid = (TextView) itemView.findViewById(R.id.rfid);
                close = (ImageButton) itemView.findViewById(R.id.close);
            }
        }
    }

}
