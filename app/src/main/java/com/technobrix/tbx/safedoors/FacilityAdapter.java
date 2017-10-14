package com.technobrix.tbx.safedoors;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.technobrix.tbx.safedoors.FacilityPOJO.FacilityList;

import java.util.ArrayList;
import java.util.List;


public class FacilityAdapter extends RecyclerView.Adapter<FacilityAdapter.MyViewHolder>{

    Context context;
    List<FacilityList> list = new ArrayList<>();


    public FacilityAdapter(Context context , List<FacilityList> list){

        this.context = context;
        this.list =  list;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.facility_list_model , parent , false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        FacilityList item = list.get(position);
        holder.name.setText(item.getName());
        holder.lorem.setText("Price: " + item.getPricePer());
        holder.one.setText(String.valueOf(position + 1) + ".");

    }
    public void setgriddata(List<FacilityList> list){

        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name , lorem , one;
        Button book;


        public MyViewHolder(View itemView) {
            super(itemView);

            name = (TextView)itemView.findViewById(R.id.name);
            lorem = (TextView)itemView.findViewById(R.id.text);
            one = (TextView)itemView.findViewById(R.id.one);
            book = (Button) itemView.findViewById(R.id.book);
        }
    }
}
