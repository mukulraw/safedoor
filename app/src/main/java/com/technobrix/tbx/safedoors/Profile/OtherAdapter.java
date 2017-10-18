package com.technobrix.tbx.safedoors.Profile;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.technobrix.tbx.safedoors.GetVehiclePOJO.VehicleList;
import com.technobrix.tbx.safedoors.R;

import java.util.ArrayList;
import java.util.List;


public class OtherAdapter extends RecyclerView.Adapter<OtherAdapter.myviewholder> {

    Context context;
    List<VehicleList> list = new ArrayList<>();

    public OtherAdapter(Context context , List<VehicleList> list){

        this.list = list;
        this.context = context;
    }
    @Override
    public OtherAdapter.myviewholder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.other_list_model , parent , false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(OtherAdapter.myviewholder holder, int position) {

        VehicleList item = list.get(position);

        holder.name.setText(item.getVehicleName());
        holder.vehicle.setText(item.getVehicleNo());
        holder.novehicle.setText(item.getNoOfVehicle());

    }

    public void setgrid(List<VehicleList> list){

        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public  class myviewholder extends RecyclerView.ViewHolder {

        TextView name , novehicle , vehicle ;
        ImageButton close;

        public myviewholder(View itemView) {
            super(itemView);

            name = (TextView)itemView.findViewById(R.id.car);
            novehicle = (TextView)itemView.findViewById(R.id.one);
            vehicle = (TextView)itemView.findViewById(R.id.vehicle);
            close = (ImageButton) itemView.findViewById(R.id.close);
        }
    }
}
