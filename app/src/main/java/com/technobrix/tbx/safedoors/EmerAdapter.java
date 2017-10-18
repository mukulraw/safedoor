package com.technobrix.tbx.safedoors;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tvs on 10/16/2017.
 */

public class EmerAdapter extends RecyclerView.Adapter<EmerAdapter.MyViewHolder> {

    Context context;

    List<EmerBean> list = new ArrayList<>();

    public EmerAdapter(Context context , List<EmerBean> list){



        this.list = list;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.emer_list_model , parent , false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        EmerBean item = list.get(position);

        holder.name.setText(item.getNumber());
        holder.num.setText(item.getTitle());




    }

    public void setgrid(List<EmerBean>list){

        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name , num;

        public MyViewHolder(View itemView) {
            super(itemView);


            name = (TextView)itemView.findViewById(R.id.name);
            num = (TextView)itemView.findViewById(R.id.number);
        }
    }
}
